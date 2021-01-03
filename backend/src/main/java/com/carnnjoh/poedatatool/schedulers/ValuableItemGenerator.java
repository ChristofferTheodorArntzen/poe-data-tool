package com.carnnjoh.poedatatool.schedulers;

import com.carnnjoh.poedatatool.db.dao.ValuableItemDAO;
import com.carnnjoh.poedatatool.db.model.ValuableItem;
import com.carnnjoh.poedatatool.model.Item;
import com.carnnjoh.poedatatool.model.StashTab;
import com.carnnjoh.poedatatool.model.StashTabs;
import com.carnnjoh.poedatatool.services.PublicStashTabFetcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * fetches the last public items and saves a subset of the items to the active database.
 */

@Configuration
@EnableScheduling
public class ValuableItemGenerator implements ScheduledTask {

	private final Logger LOGGER = LoggerFactory.getLogger(ValuableItemGenerator.class);

	@Autowired
	private PublicStashTabFetcher fetcher;

	@Autowired
	private ValuableItemDAO valuableItemDAO;

	private String nextChangeId;

	@Override
	@Scheduled(initialDelay = 1000, fixedDelay = 10000)
	public void execute() {

		LOGGER.info("Started fetching stash tabs");

		double startTime = (double) System.currentTimeMillis();

		Optional<StashTabs> metaData = fetcher.fetchPublicStashTabs(nextChangeId);

		List<StashTab> stashTabList = new ArrayList<>();

		if (metaData.isPresent()) {
			nextChangeId = metaData.get().changeId;
			stashTabList.addAll(metaData.get().stashes);
		}

		if (stashTabList.isEmpty()) {
			System.out.println("stash tabs are empty");
		}

		List<Item> itemList = getItems(stashTabList);

		itemList = itemList.stream().filter(item -> item.sockets != null).collect(Collectors.toList());
		if (itemList.size() > 5) {
			itemList = itemList.subList(0, 2);
		}

		Random random = new Random();

		for (Item item : itemList) {

			ValuableItem valuableItem = new ValuableItem(item.itemId, 1, item, random.nextInt(20));
			valuableItemDAO.save(valuableItem);
		}

		LOGGER.info(String.format("Added %d items", itemList.size()));
		LOGGER.info(String.format("Execution took '%.2f' secs", getDuration(startTime, (double) System.currentTimeMillis())));
	}

	private List<Item> getItems(List<StashTab> stashTabs) {
		return stashTabs.stream().flatMap(stashTab -> stashTab.items.stream()).collect(Collectors.toList());
	}

	private double getDuration(double startTime, double endTime) {
		double durationMillis = endTime - startTime;
		return durationMillis / 1000;
	}
}
