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

	private Logger logger = LoggerFactory.getLogger(PrivateStashPoller.class);

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

	@Scheduled(initialDelay = 1000, fixedDelay = 20000)
	public void execute() {
		long startTime = System.currentTimeMillis();

		if(disabled) {
			logger.info("Scheduled task " + getClass().getSimpleName() + " is disabled");
			return;
		} else {
			logger.info("Scheduled task " + getClass().getSimpleName() + " has began");
		}

		SchedulerUtils.execute(() -> {
			//TODO: add a search for active user
			User user = userDAO.fetch(1);

			Subscription sub = subscriptionDAO.fetchByStatus(true);

			Integer[] activeTabs = sub.getTabIds();

			if (activeTabs.length > 0) {
				List<PrivateStashTab> privateStashTabs = privateStashTabService.requestStashTabs(activeTabs, user);
				privateStashTabService.saveItems(privateStashTabs);

				logger.debug(String.format("Executed %s in '%d'ms",	getClass().getSimpleName(), System.currentTimeMillis() - startTime));
			} else {
				logger.debug("stashTab was not present");
			}
		});
	}
}