package com.carnnjoh.poedatatool.scheduler;

import com.carnnjoh.poedatatool.api.PrivateStashTabController;
import com.carnnjoh.poedatatool.model.*;
import com.carnnjoh.poedatatool.services.PrivateStashTabFetcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
@EnableScheduling
public class PrivateStashPoller implements ScheduledTask {

	private Logger logger = LoggerFactory.getLogger(PrivateStashPoller.class);

	@Autowired
	private PrivateStashTabFetcher privateStashTabFetcher;

	public PrivateStashPoller() {
	}

	@Override
	@Scheduled(initialDelay = 1000, fixedDelay = 20000)
	public void execute() {
		try{
			System.out.println("Scheduled task has began");

			int numberOfTabs =
				privateStashTabFetcher.fetchNumberOfTabs(fetchRequest(null));
			System.out.println("got first Fetched tab");

			if(numberOfTabs > 0) {
				System.out.println("number of Tabs: " + numberOfTabs);

				List<PrivateStashTab> fetchedTabList = new ArrayList<>();
				for (int i = 0; i < numberOfTabs - 1; i++) {
					Optional<PrivateStashTab> currentlyFetchedTab = privateStashTabFetcher.fetchStashItems(fetchRequest(i));
					if(currentlyFetchedTab.isPresent()) {
						fetchedTabList.add(currentlyFetchedTab.get());
						System.out.println("added currently fetched tab: " + i);
						System.out.println("sleeping for: " + 1000);
						Thread.sleep(1000);
					}
				}

				if (fetchedTabList.size() > 0) {
					for(PrivateStashTab privateStashTab : fetchedTabList) {
						for (Item item : privateStashTab.items) {
							if(item.name != null && !item.name.isEmpty())
							System.out.println(item.name);
						}
					}
				}
			} else {
				System.out.println("stashTab was not present");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public PrivateStashTabRequest fetchRequest(Integer tabIndex) {

		if(tabIndex!=null){
			return new PrivateStashTabRequest(
				"Heist",
				"pc",
				"Athzen",
				true,
				tabIndex,
				""
			);
		}
		return new PrivateStashTabRequest(
			"Heist",
			"pc",
			"Athzen",
			true,
			null,
			""
		);
	}
}