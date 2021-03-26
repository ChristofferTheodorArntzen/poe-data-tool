package com.carnnjoh.poedatatool.schedulers;

import com.carnnjoh.poedatatool.db.dao.SubscriptionDAO;
import com.carnnjoh.poedatatool.db.model.Subscription;
import com.carnnjoh.poedatatool.model.Item;
import com.carnnjoh.poedatatool.services.ItemFilterService;
import com.carnnjoh.poedatatool.services.ItemSearcher;
import com.carnnjoh.poedatatool.services.PrivateStashTabFetcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableScheduling
public class ScheduledPriceEstimator {

	private Logger logger = LoggerFactory.getLogger(ScheduledPriceEstimator.class);

	@Autowired
	private PrivateStashTabFetcher privateStashTabFetcher;

	@Autowired
	private SubscriptionDAO subscriptionDAO;

	@Autowired
	private ItemFilterService itemFilterService;

	@Autowired
	private ItemSearcher itemSearcher;

	private static final int SLEEP = 2000;

	public ScheduledPriceEstimator() {
	}

	@Scheduled(initialDelay = 2000, fixedDelay = 20000)
	public void execute() {

		logger.info("Scheduled task " + getClass().getName() + " has began");

		SchedulerUtils.execute(() -> {

			Subscription sub = subscriptionDAO.fetchByStatus(true);

			//TODO: get this list form somewhere
			List<Item> items = new ArrayList<>();

			itemFilterService.filterItems(items, null);




		});
	}

}
