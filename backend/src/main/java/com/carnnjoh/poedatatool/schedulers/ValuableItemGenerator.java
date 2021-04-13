package com.carnnjoh.poedatatool.schedulers;

import com.carnnjoh.poedatatool.db.dao.ValuableItemDAO;
import com.carnnjoh.poedatatool.db.inMemory.dao.StatsDao;
import com.carnnjoh.poedatatool.db.model.ValuableItem;
import com.carnnjoh.poedatatool.model.InMemoryItem;
import com.carnnjoh.poedatatool.model.Item;
import com.carnnjoh.poedatatool.model.PublicStashTab;
import com.carnnjoh.poedatatool.model.StashTabs;
import com.carnnjoh.poedatatool.services.ExplicitModExtractorService;
import com.carnnjoh.poedatatool.services.PrivateStashTabService;
import com.carnnjoh.poedatatool.services.PublicStashTabFetcher;
import com.carnnjoh.poedatatool.services.WebSocketPublishService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * fetches the last public items and saves a subset of the items to the active database.
 * used for prototyping / testing / extracting data. Not meant to run while the program is in production
 */

@Configuration
@EnableScheduling
public class ValuableItemGenerator {

	private final Logger LOGGER = LoggerFactory.getLogger(ValuableItemGenerator.class);

	@Autowired
	private PublicStashTabFetcher fetcher;

	@Autowired
	private ValuableItemDAO valuableItemDAO;

	private String nextChangeId;

	@Autowired
	WebSocketPublishService webSocketPublishService;

	@Autowired
	ExplicitModExtractorService explicitModExtractorService;

	@Autowired
	private PrivateStashTabService privateStashTabService;

	@Autowired
	StatsDao statsDao;

	@Value("${schedulers.valuableItemGenerator.disabled:true}")
	boolean disabled;

	@Scheduled(initialDelay = 1000, fixedDelay = 10000)
	public void execute() {

		if(disabled) {
			LOGGER.info("ValuableItemGenerator is disabled");
			return;
		}

		LOGGER.info("Started fetching stash tabs");

		long startTime = System.currentTimeMillis();

		Optional<StashTabs> metaData = fetcher.fetchPublicStashTabs(nextChangeId);
		LOGGER.info(String.format("Http call '%.2f' secs", getDuration(startTime, System.currentTimeMillis())));

		List<PublicStashTab> publicStashTabList = new ArrayList<>();

		if (metaData.isPresent()) {
			nextChangeId = metaData.get().changeId;
			publicStashTabList.addAll(metaData.get().stashes);
		}

		privateStashTabService.saveItems(publicStashTabList);

		Map<String, InMemoryItem> inMemoryItemMap = privateStashTabService.getInMemoryItemMap();

		List<Item> itemList = getItems(publicStashTabList);
//		for (InMemoryItem item : inMemoryItemMap.values()) {
//
//			List<InterpretedMod> interpretedMods = new ArrayList<>();
//
//			if(item.getItemType() != null && item.getItemType().convertMods && item.explicitMods != null) {
//				 interpretedMods.addAll(explicitModExtractorService.convertExplicitMods(item.explicitMods));
//			} else {
//
//				if(item.getItemType() != null) {
//					//System.out.println(item.getItemType());
//				} else {
//					//System.out.println(item.toString());
//				}
//
//			}
//
//			for (InterpretedMod interpretedMod : interpretedMods) {
//
//				String modId = statsDao.lookUpIdByModText(interpretedMod.getGenericText());
//
//				if(modId == null)  {
//					System.out.println(String.format("Interpreted mod, text: %s, minValue: %.2f, maxValue: %.2f",
//						interpretedMod.getGenericText(),
//						interpretedMod.getMinValue(),
//						interpretedMod.getMaxValue()
//					));
//				} else {
//					//System.out.println(modId);
//				}
//			}
//		}

		itemList = itemList.stream().filter(item -> item.sockets != null).collect(Collectors.toList());
		if (itemList.size() > 5) {
			itemList = itemList.subList(0, 1);
		}

		Random random = new Random();

		for (Item item : itemList) {

			ValuableItem valuableItem = new ValuableItem(
					item.itemId,
					1,
					item,
					random.nextInt(20),
					random.nextInt(20),
					random.nextInt(20),
					random.nextInt(20),
					LocalDateTime.now()
			);

			valuableItemDAO.save(valuableItem);

			webSocketPublishService.publishToWebSocket("/topic/greetings", valuableItem);
		}

		LOGGER.info(String.format("Added %d items", itemList.size()));
		LOGGER.info(String.format("Execution took '%.2f' secs", getDuration(startTime, (double) System.currentTimeMillis())));
	}

	private List<Item> getItems(List<PublicStashTab> publicStashTabs) {
		return publicStashTabs.stream().flatMap(stashTab -> stashTab.items.stream()).collect(Collectors.toList());
	}

	private double getDuration(double startTime, double endTime) {
		double durationMillis = endTime - startTime;
		return durationMillis / 1000;
	}
}
