package com.carnnjoh.poedatatool.services;

import com.carnnjoh.poedatatool.db.inMemory.dao.UniqueDao;
import com.carnnjoh.poedatatool.db.model.User;
import com.carnnjoh.poedatatool.model.*;
import com.carnnjoh.poedatatool.schedulers.SchedulerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.carnnjoh.poedatatool.model.ItemType.*;

@Service
public class PrivateStashTabService {

	private Logger logger = LoggerFactory.getLogger(PrivateStashTabService.class);

	@Autowired
	private PrivateStashTabFetcher privateStashTabFetcher;

	@Autowired
	private UniqueDao uniqueDao;

	@Autowired
	private ExplicitModExtractorService explicitModExtractor;

	//TODO: find a better way to set this when testing... ?
	@Value("${services.test.poesessid}")
	private String testSessionId;

	//TODO: find a better way of achieving the same thing
	private final List<ItemType> listOfItemTypes = Arrays.asList(
		ABYSSALJEWEL,
		SCARAB,
		PROPHECY,
		FRAGMENT,
		OIL,
		CURRENCY,
		UNIQUE,
		CARD,
		CLUSTERJEWEL,
		BLIGHTMAP,
		MAP,
		DELIRIUMORB,
		CATALYST,
		FOSSIL,
		ESSENCE,
		RESONATOR,
		GEM,
		UNIQUE_ARMOUR,
		UNIQUE_WEAPON,
		UNIQUE_ACCESSORY,
		UNIQUE_FLASK,
		RARE_WEAPON,
		RARE_ARMOUR,
		RARE_ACCESSORIES);

	private final Map<String, InMemoryItem> inMemoryItemMap = new HashMap<>();

	public List<PrivateStashTab> requestStashTabs(Integer[] activeTabs, User user) {
		List<PrivateStashTab> fetchedTabs = new ArrayList<>();

		try {
			// need to loop through all "active" tabs from subscription, API call to retrieve private stash tab does not support fetching more than one at a time
			for (Integer activeTab : activeTabs) {
				Optional<PrivateStashTab> currentlyFetchedTab = privateStashTabFetcher
						.fetchStashItems(createPrivateStashTabRequest(user, activeTab));
				currentlyFetchedTab.ifPresent(fetchedTabs::add);

				// Need to sleep between calls, to not get rate limited.
				Thread.sleep(SchedulerUtils.HTTP_REQUEST_SLEEP);
			}

		} catch (Exception e) {
			logger.warn(e.getMessage());
			throw new RuntimeException(e);
		}

		return fetchedTabs;
	}

	public <T extends StashTab> void saveItems(List<T> stashTabs) {
		if (!stashTabs.isEmpty()) {

			logger.debug("before adding/removing ItemMap size: " + inMemoryItemMap.size());

			//Used to control the size of the in memory map of items.
			List<String> itemsToBeRemoved = new ArrayList<>(inMemoryItemMap.keySet());

			for (StashTab stashTab : stashTabs) {

				if(stashTab.items == null || stashTab.items.isEmpty()) {
					continue;
				}

				for (Item item : stashTab.items) {
					if (item != null && item.isIdentified) {

						InMemoryItem inMemoryItem = new InMemoryItem(item);

						applyItemType(inMemoryItem);
						applyExplicitMods(inMemoryItem);

						inMemoryItemMap.putIfAbsent(item.itemId, inMemoryItem);
						itemsToBeRemoved.remove(item.itemId);
					}
				}
			}

			for(String itemId : itemsToBeRemoved) {
				inMemoryItemMap.remove(itemId);
			}

			logger.debug("after adding/removing ItemMap size: " + inMemoryItemMap.size());
		}
	}

	private void applyItemType(InMemoryItem inMemoryItem) {

		lookUpItemIfSpecialItem(inMemoryItem);

		if(inMemoryItem.getItemType() != null) {
			return;
		}

		for(ItemType itemType : listOfItemTypes) {
			if(itemType.filter != null  && itemType.filter.test(inMemoryItem)) {
				inMemoryItem.setItemType(itemType);
				break;
			}
		}
	}

	private void lookUpItemIfSpecialItem(InMemoryItem item) {
		uniqueDao.applyItemTypeIfGem(item);
		uniqueDao.applyItemTypeIfUnique(item);
	}

	private void applyExplicitMods(InMemoryItem inMemoryItem) {
		explicitModExtractor.extractAndApplyMods(inMemoryItem);
	}

	public Item getItemFromInMemoryMap(String itemId) {
		return inMemoryItemMap.get(itemId);
	}

	public Map<String, InMemoryItem> getInMemoryItemMap() {
		return inMemoryItemMap;
	}

	private PrivateStashTabRequest createPrivateStashTabRequest(User user, int activeTab) {

		String sessionId = (testSessionId == null || testSessionId.isEmpty()) ? user.getSessionId() : testSessionId;

		logger.debug("Current session id: " + sessionId);

		return new PrivateStashTabRequest(
			user.getLeague(),
			user.getRealm(),
			user.getAccountName(),
			false,
			activeTab,
			sessionId
		);
	}

}
