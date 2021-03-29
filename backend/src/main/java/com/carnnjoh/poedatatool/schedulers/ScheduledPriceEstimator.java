package com.carnnjoh.poedatatool.schedulers;

import com.carnnjoh.poedatatool.db.dao.SubscriptionDAO;
import com.carnnjoh.poedatatool.db.dao.UserDAO;
import com.carnnjoh.poedatatool.db.model.Subscription;
import com.carnnjoh.poedatatool.db.model.User;
import com.carnnjoh.poedatatool.model.InMemoryItem;
import com.carnnjoh.poedatatool.model.tradeAPIModels.ListingResponse.ListingResponse;
import com.carnnjoh.poedatatool.model.tradeAPIModels.QueryRequest.QueryRequest;
import com.carnnjoh.poedatatool.model.tradeAPIModels.QueryResponse;
import com.carnnjoh.poedatatool.services.ItemFilterService;
import com.carnnjoh.poedatatool.services.ItemSearcher;
import com.carnnjoh.poedatatool.services.PrivateStashTabService;
import com.carnnjoh.poedatatool.services.TradeAPISearchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Configuration
@EnableScheduling
public class ScheduledPriceEstimator {

	private Logger logger = LoggerFactory.getLogger(ScheduledPriceEstimator.class);

	@Autowired
	private SubscriptionDAO subscriptionDAO;

	@Autowired
	private ItemFilterService itemFilterService;

	//TODO: rename to a more appropriate name
	@Autowired
	private ItemSearcher itemSearcher;

	@Autowired
	private PrivateStashTabService privateStashTabService;

	@Autowired
	private TradeAPISearchService tradeAPISearchService;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private ObjectMapper objectMapper;

	public ScheduledPriceEstimator() {
	}

	@Scheduled(initialDelay = 2000, fixedDelay = 20000)
	public void execute() {

		logger.info("Scheduled task " + getClass().getName() + " has began");

		Subscription sub = subscriptionDAO.fetchByStatus(true);

		User user = userDAO.fetch(1);

		Map<String, InMemoryItem> items = privateStashTabService.getInMemoryItemMap();
		if(items.size() == 0) {
			logger.info("in memory map of items in " + getClass().getName() + " is empty! Returning early from job");
			return;
		}

		items = itemFilterService.filterItems(items, sub.getItemTypes());

		Map<String, InMemoryItem> testShit = new HashMap<>();

		for(InMemoryItem item : items.values()) {

			if(item.getItem().name.equals("Death's Harp")) {

				if(item.isSearch()) {
					System.out.println("we search");
				} else  {
					System.out.println("filter is fucked up");
					item.setSearch(true);
				}

				testShit.put(item.getItem().itemId, item);
				System.out.println(item.getItem().toString());
			}
		}


		List<QueryRequest> queryRequests = itemSearcher.searchForItem(testShit);

		for(QueryRequest queryRequest : queryRequests) {
			try {
				System.out.println(objectMapper.writeValueAsString(queryRequest));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

		for(QueryRequest requests : queryRequests) {

			Optional<QueryResponse> queryResponse = tradeAPISearchService
					.searchForItemIds(user.getSessionId(), requests);

			if (queryResponse.isPresent()) {

				//TODO: get 400 bad request here - fix
				Optional<ListingResponse> listingResponse = tradeAPISearchService
						.fetchListings(user.getSessionId(), queryResponse.get());

				if(listingResponse.isPresent()) {
					try {
						System.out.println(new ObjectMapper().writeValueAsString(listingResponse));
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
