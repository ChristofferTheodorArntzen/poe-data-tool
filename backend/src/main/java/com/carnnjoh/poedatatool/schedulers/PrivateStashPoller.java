package com.carnnjoh.poedatatool.schedulers;

import com.carnnjoh.poedatatool.db.dao.ItemFilterTypeSubscriptionDAO;
import com.carnnjoh.poedatatool.db.dao.UserDAO;
import com.carnnjoh.poedatatool.db.model.Subscription;
import com.carnnjoh.poedatatool.db.model.User;
import com.carnnjoh.poedatatool.model.Item;
import com.carnnjoh.poedatatool.model.PrivateStashTab;
import com.carnnjoh.poedatatool.model.PrivateStashTabRequest;
import com.carnnjoh.poedatatool.services.PrivateStashTabFetcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
@EnableScheduling
public class PrivateStashPoller {

	private Logger logger = LoggerFactory.getLogger(PrivateStashPoller.class);

	@Autowired
	private PrivateStashTabFetcher privateStashTabFetcher;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private ItemFilterTypeSubscriptionDAO itemFilterTypeSubscriptionDAO;

	private boolean disabled = false;
	private int disableCounter = 0;
	private static final int SLEEP = 2000;

	public PrivateStashPoller() {
	}

	@Scheduled(initialDelay = 1000, fixedDelay = 20000)
	public void execute() {

		logger.info("Scheduled task " + getClass() + " has began");

		disabledTick();

		SchedulerUtils.execute(() -> {
			disableCounter = 0;

			//TODO: add a search for active user
			User user = userDAO.fetch(1);

			//TODO: add a search for active user
			Subscription sub = itemFilterTypeSubscriptionDAO.getSubscription(1);

			Integer[] activeTabs = sub.getTabIds();

			if (activeTabs.length > 0) {
				List<PrivateStashTab> privateStashTabs = requestStashTabs(activeTabs, user);
				saveItems(privateStashTabs);
			} else {
				logger.debug("stashTab was not present");
			}
		});
	}

	private User fetchUser() {
		return userDAO.fetchAll().get(0);
	}

	private void saveItems(List<PrivateStashTab> privateStashTabs) {
		if (!privateStashTabs.isEmpty()) {
			for (PrivateStashTab privateStashTab : privateStashTabs) {
				for (Item item : privateStashTab.items) {
					if (item.name != null && !item.name.isEmpty())
						//TODO: save items? continue on to filter out items or something.
						//TODO: Keep evaluation separate from fetching items? Keep items in memory or save them?
						logger.debug("found an item");
				}
			}
		}
	}

	private List<PrivateStashTab> requestStashTabs(Integer[] activeTabs, User user) {
		List<PrivateStashTab> fetchedTabs = new ArrayList<>();

		SchedulerUtils.execute(() -> {
			for (Integer activeTab : activeTabs) {
				Optional<PrivateStashTab> currentlyFetchedTab = privateStashTabFetcher
					.fetchStashItems(createPrivateStashTabRequest(user, activeTab));
				if (currentlyFetchedTab.isPresent()) {
					fetchedTabs.add(currentlyFetchedTab.get());
					Thread.sleep(SLEEP);
				}
			}
		});

		return fetchedTabs;
	}

	//TODO: make a better way to disable this.
	private void disabledTick() {
		if (disabled) {
			logger.info("Scheduled task " + getClass() + " is disabled");
			disableCounter++;
			if (disableCounter > 1000) {
				disabled = false;
			}
		}
	}

	private PrivateStashTabRequest createPrivateStashTabRequest(User user, int activeTab) {
		//TODO: make request contain all the tabs from the subscription
		return new PrivateStashTabRequest(
			user.getLeague(),
			user.getRealm(),
			user.getAccountName(),
			false,
			activeTab,
			user.getSessionId()
		);
	}
}