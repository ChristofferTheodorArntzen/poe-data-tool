package com.carnnjoh.poedatatool.schedulers;

import com.carnnjoh.poedatatool.db.dao.SubscriptionDAO;
import com.carnnjoh.poedatatool.db.dao.UserDAO;
import com.carnnjoh.poedatatool.db.dao.ValuableItemDAO;
import com.carnnjoh.poedatatool.db.model.Subscription;
import com.carnnjoh.poedatatool.db.model.User;
import com.carnnjoh.poedatatool.db.model.ValuableItem;
import com.carnnjoh.poedatatool.db.utils.CreateSuccessResult;
import com.carnnjoh.poedatatool.db.utils.Result;
import com.carnnjoh.poedatatool.model.InMemoryItem;
import com.carnnjoh.poedatatool.model.Item;
import com.carnnjoh.poedatatool.model.tradeAPIModels.ListingResponse.ListingResponse;
import com.carnnjoh.poedatatool.model.tradeAPIModels.QueryRequest.QueryRequest;
import com.carnnjoh.poedatatool.model.tradeAPIModels.QueryResponse;
import com.carnnjoh.poedatatool.services.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Configuration
@EnableScheduling
public class ScheduledPriceEstimator {

	private Logger logger = LoggerFactory.getLogger(ScheduledPriceEstimator.class);

	@Autowired
	private SubscriptionDAO subscriptionDAO;

	@Autowired
	private ItemFilterService itemFilterService;

	@Autowired
	private QueryConstructorService queryConstructorService;

	@Autowired
	private PrivateStashTabService privateStashTabService;

	@Autowired
	private TradeAPIService tradeAPIService;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private ValuableItemDAO valuableItemDAO;

	@Autowired
	private WebSocketPublishService webSocketPublishService;

	@Autowired
	private ObjectMapper objectMapper;

	@Value("${schedulers.ScheduledPriceEstimator.disabled:false}")
	private boolean disabled;

	public ScheduledPriceEstimator() {
	}

	@Scheduled(initialDelay = 2000, fixedDelay = 20000)
	public void execute() {

		long startTime = System.currentTimeMillis();

		if(disabled) {
			logger.info("Scheduled task " + getClass().getSimpleName() + " is disabled");
			return;
		} else {
			logger.info("Scheduled task " + getClass().getSimpleName() + " has began");
		}

		Subscription sub = subscriptionDAO.fetchByStatus(true);

		User user = userDAO.fetch(1);

		Map<String, InMemoryItem> inMemoryItemMap = privateStashTabService.getInMemoryItemMap();
		if (inMemoryItemMap.size() == 0) {
			logger.info("in memory map of items in " + privateStashTabService.getClass().getSimpleName() + " is empty! Returning early from job");
			return;
		}

		inMemoryItemMap = itemFilterService.filterItems(inMemoryItemMap, sub.getItemTypes());

		//todo: build this out -- attach original item to the object
		List<QueryRequest> queryRequests = queryConstructorService.createQueryRequests(inMemoryItemMap);

		//todo: figure out a way to do this a different way? need to sleep in between every call
		makeQueries(queryRequests, user, sub);
	}

	private void makeQueries(List<QueryRequest> queryRequests, User user, Subscription subscription) {

		// One per searchable item
		for (QueryRequest request : queryRequests) {

			Optional<QueryResponse> queryResponse = getQueryIdAndItemListingIds(request, user);

			if(!queryResponse.isPresent()) {
				continue;
			}

			Optional<ListingResponse> listingResponse = getListings(queryResponse.get(), user);

			if(!listingResponse.isPresent()) {
				continue;
			}

			ValuableItem valuableItem = createValuableItem(listingResponse.get(), subscription);

			webSocketPublishService.publishToWebSocket(valuableItem);
		}
	}

	private ValuableItem createValuableItem(ListingResponse listingResponse, Subscription subscription) {
		List<Integer> listingValues = getConvertedListOfPrices(listingResponse);

		//TODO: use the original item that made the query, not the first of the response.
		Item item = listingResponse.result.get(0).item;

		int meanPrice = listingValues.stream().mapToInt(price -> price).sum() / listingValues.size();
		int medianPrice = getMedianPrice(listingValues);
		int max = Collections.max(listingValues);
		int min = Collections.min(listingValues);

		if(meanPrice > (int) subscription.getCurrencyThreshold()) {

			// lookup if an item with the same itemId exists, if it does update that rather than creating a new one
			ValuableItem valuableItem = new ValuableItem(item.itemId, subscription.getPk(), item, meanPrice, medianPrice, max, min, LocalDateTime.now());
			Result result = valuableItemDAO.save(valuableItem);

			if(result instanceof CreateSuccessResult) {
				valuableItem.setPk(((CreateSuccessResult) result).getPk());
				return valuableItem;
			}
		}

		return null;
	}

	// TODO: this can contain more than 1 currency type. Convert all amount nums to chaos
	private List<Integer> getConvertedListOfPrices(ListingResponse listingResponse) {
		return listingResponse.result.stream().map(response -> response.listing.price.amount).collect(Collectors.toList());
	}

	private int getMedianPrice(List<Integer> listingValues) {
		return (listingValues.size() % 2 == 0)
				? (listingValues.get(listingValues.size() / 2) + listingValues.get(listingValues.size() / 2 - 1) /2)
				: listingValues.get(listingValues.size() / 2);
	}

	private Optional<QueryResponse> getQueryIdAndItemListingIds(QueryRequest request, User user) {
		Optional<QueryResponse> queryResponse = tradeAPIService
				.searchForItemIds(user.getSessionId(), request);

		SchedulerUtils.trySleep();

		return queryResponse;
	}

	private Optional<ListingResponse> getListings(QueryResponse response, User user) {
		Optional<ListingResponse> listingResponse = tradeAPIService
				.fetchListings(user.getSessionId(), response);

		SchedulerUtils.trySleep();

		return listingResponse;
	}
}
