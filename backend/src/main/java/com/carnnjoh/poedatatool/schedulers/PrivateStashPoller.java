package com.carnnjoh.poedatatool.schedulers;

import com.carnnjoh.poedatatool.db.dao.SubscriptionDAO;
import com.carnnjoh.poedatatool.db.dao.UserDAO;
import com.carnnjoh.poedatatool.db.model.Subscription;
import com.carnnjoh.poedatatool.db.model.User;
import com.carnnjoh.poedatatool.model.PrivateStashTab;
import com.carnnjoh.poedatatool.services.PrivateStashTabFetcher;
import com.carnnjoh.poedatatool.services.PrivateStashTabService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
public class PrivateStashPoller {

	private Logger LOGGER = LoggerFactory.getLogger(PrivateStashPoller.class);

	@Autowired
	private PrivateStashTabFetcher privateStashTabFetcher;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private SubscriptionDAO subscriptionDAO;

	@Autowired
	private PrivateStashTabService privateStashTabService;

	@Value("${schedulers.privateStashPoller.disabled:false}")
	private boolean disabled;

	public static final int SLEEP = 2000;

	public PrivateStashPoller() {
	}

	@Scheduled(initialDelay = 1000, fixedDelay = 60000)
	public void execute() {
		long startTime = System.currentTimeMillis();

		if(disabled) {
			LOGGER.info("Scheduled task " + PrivateStashPoller.class + " is disabled");
			return;
		} else {
			LOGGER.info("Scheduled task " + PrivateStashPoller.class + " has began");
		}

		SchedulerUtils.execute(() -> {

			User user = userDAO.getLastCreatedUser();

			if (user == null) {
				LOGGER.info("No user were found. Returning early!");
				return;
			}

			Subscription sub = subscriptionDAO.fetchFirstActive(true);

			if (sub == null) {
				LOGGER.info("No active subscriptions were found. Returning early!");
				return;
			}

			Integer[] activeTabs = sub.getTabIds();

			if (activeTabs.length > 0) {
				List<PrivateStashTab> privateStashTabs = privateStashTabService.requestStashTabs(activeTabs, user);
				privateStashTabService.saveItems(privateStashTabs);

				LOGGER.debug(String.format("Executed %s in '%d'ms",	getClass().getSimpleName(), System.currentTimeMillis() - startTime));
			} else {
				LOGGER.debug("stashTab was not present");
			}
		});
	}
}