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

    private Logger LOGGER = LoggerFactory.getLogger(ScheduledPriceEstimator.class);

	@Autowired
	private SubscriptionDAO subscriptionDAO;

	@Autowired
	private ItemFilterService itemFilterService;

	@Autowired
	private QueryConstructorService queryConstructorService;

	@Autowired
	private PrivateStashTabService privateStashTabService;

    @Autowired
    private TradeAPIFetcher tradeAPIFetcher;

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

    @Scheduled(initialDelay = 2000, fixedDelay = 20000)
    public void execute() {

        long startTime = System.currentTimeMillis();

        if (disabled) {
            LOGGER.info("Scheduled task " + ScheduledPriceEstimator.class + " is disabled");
            return;
        } else {
            LOGGER.info("Scheduled task " + ScheduledPriceEstimator.class + " has began");
        }

        Subscription sub = subscriptionDAO.fetchFirstActive(true);

        if (sub == null) {
            LOGGER.info("There are no active subscriptions. Returning early!");
            return;
        }

        User user = userDAO.getLastCreatedUser();

        if (user == null) {
            LOGGER.info("There are no users. Returning early!");
            return;
        }

		Map<String, InMemoryItem> inMemoryItemMap = privateStashTabService.getInMemoryItemMap();

        if (inMemoryItemMap.size() == 0) {
			LOGGER.info("in memory map of items in " + privateStashTabService.getClass().getSimpleName() + " is empty! Returning early from job");
			return;
		}

		inMemoryItemMap = itemFilterService.filterItems(inMemoryItemMap, sub.getItemTypes());

        Map<String, QueryRequest> queryRequests = queryConstructorService.createQueryRequestsItemMap(inMemoryItemMap);
        LOGGER.info(String.format("Constructed query requests for all item in the inMemoryMap - queryRequests size: %d", queryRequests.size()));

        //todo: figure out a way to do this a different way? need to sleep in between every call
        makeQueries(queryRequests, user, sub);

        LOGGER.debug(String.format("Executed %s in '%d'ms",	getClass().getSimpleName(), System.currentTimeMillis() - startTime));
    }

    private void makeQueries(Map<String, QueryRequest> queryItemMap, User user, Subscription subscription) {

        try {

            // One per searchable item
            for (Map.Entry<String, QueryRequest> queryItemEntry : queryItemMap.entrySet()) {

                Optional<QueryResponse> queryResponse = getQueryIdAndItemListingIds(queryItemEntry.getValue(), user);

                try {
                    Thread.sleep(SchedulerUtils.HTTP_REQUEST_SLEEP);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }

                if (queryResponse.isEmpty()) {
                    LOGGER.info("Query response is empty for item with id: '%s', continuing to next query.");
                    continue;
                }

                if(queryResponse.get().result.length == 0) {
                    LOGGER.info("Query response didn't contain any result ids, continuing to next query.");
                    continue;
                }

                //TODO: If there are more than 15 listing ids this shortens it down to 15, and drop the last in the queryResponse object
                // need to figure out a way to get the listing response for all ids.
                Optional<ListingResponse> listingResponse = getListings(queryResponse.get(), user);

                try {
                    Thread.sleep(SchedulerUtils.HTTP_REQUEST_SLEEP);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }

                if (listingResponse.isEmpty()) {
                    LOGGER.info("Listing response is empty for item it id: '%s', continuing to next query.");
                    continue;
                }

                Item item = privateStashTabService.getItemFromInMemoryMap(queryItemEntry.getKey());

                //TODO: try to find the item a different way?
                if(item == null) {
                    LOGGER.debug("Item from private stash tab was not present when listings were processed, continuing...");
                    continue;
                }

                ValuableItem valuableItem = createValuableItem(listingResponse.get(), subscription, item);

                webSocketPublishService.publishToFeed(valuableItem);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

	private ValuableItem createValuableItem(ListingResponse listingResponse, Subscription subscription, Item item) {
		List<Integer> listingValues = getConvertedListOfPrices(listingResponse);

		int meanPrice = listingValues.stream().mapToInt(price -> price).sum() / listingValues.size();
		int medianPrice = getMedianPrice(listingValues);
		int max = Collections.max(listingValues);
		int min = Collections.min(listingValues);

		if (meanPrice > (int) subscription.getCurrencyThreshold()) {

			// lookup if an item with the same itemId exists, if it does update that rather than creating a new one
			ValuableItem valuableItem = new ValuableItem(item.itemId, subscription.getPk(), item, meanPrice, medianPrice, max, min, LocalDateTime.now());
			Result result = valuableItemDAO.save(valuableItem);

			if (result instanceof CreateSuccessResult) {
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
        try {
            return tradeAPIFetcher.searchForItemIds(user.getLeague(), user.getSessionId(), request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    private Optional<ListingResponse> getListings(QueryResponse response, User user) {
        try {
            return tradeAPIFetcher.fetchListings(user.getSessionId(), response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
