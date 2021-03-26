package com.carnnjoh.poedatatool.services;

import com.carnnjoh.poedatatool.db.model.User;
import com.carnnjoh.poedatatool.model.Item;
import com.carnnjoh.poedatatool.model.PrivateStashTab;
import com.carnnjoh.poedatatool.model.PrivateStashTabRequest;
import com.carnnjoh.poedatatool.schedulers.PrivateStashPoller;
import com.carnnjoh.poedatatool.schedulers.SchedulerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PrivateStashTabService {

	private Logger logger = LoggerFactory.getLogger(PrivateStashPoller.class);

	@Autowired
	private PrivateStashTabFetcher privateStashTabFetcher;

	//TODO: find a better way to set this when testing... ?
	@Value("${services.test.poesessid}")
	private String testSessionId;

	// in memory map, contains all "active" items that the PrivateStashPoller finds.
	private final Map<String, Item> inMemoryItemMap = new HashMap<>();

	public List<PrivateStashTab> requestStashTabs(Integer[] activeTabs, User user) {
		List<PrivateStashTab> fetchedTabs = new ArrayList<>();

		try {
			// need to loop through all "active" tabs from subscription, API call to retrieve private stash tab does not support fetching more than one at a time
			for (Integer activeTab : activeTabs) {
				Optional<PrivateStashTab> currentlyFetchedTab = privateStashTabFetcher.fetchStashItems(createPrivateStashTabRequest(user, activeTab));
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

	public void saveItems(List<PrivateStashTab> privateStashTabs) {
		if (!privateStashTabs.isEmpty()) {

			logger.debug("before adding/removing ItemMap size: " + inMemoryItemMap.size());

			//Used to control the size of the in memory map of items.
			List<String> itemsToBeRemoved = new ArrayList<>(inMemoryItemMap.keySet());

			for (PrivateStashTab privateStashTab : privateStashTabs) {
				logger.debug("Private stash tab name {}", privateStashTab.items.stream().map(item -> item.inventoryId).collect(Collectors.toSet()));

				for (Item item : privateStashTab.items) {
					if (item != null) {
						inMemoryItemMap.putIfAbsent(item.itemId, item);
						itemsToBeRemoved.remove(item.itemId);
					}
				}
			}

			for(String itemId : itemsToBeRemoved) {
				logger.debug("trying to remove {}", itemId);
				inMemoryItemMap.remove(itemId);
			}

			logger.debug("after adding/removing ItemMap size: " + inMemoryItemMap.size());
		}
	}

	public Map<String, Item> getInMemoryItemMapItemMap() {
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
