package com.carnnjoh.poedatatool.init;

import com.carnnjoh.poedatatool.db.dao.SubscriptionDAO;
import com.carnnjoh.poedatatool.db.dao.UserDAO;
import com.carnnjoh.poedatatool.db.dao.ValuableItemDAO;
import com.carnnjoh.poedatatool.db.model.Subscription;
import com.carnnjoh.poedatatool.db.model.User;
import com.carnnjoh.poedatatool.db.model.ValuableItem;
import com.carnnjoh.poedatatool.model.Item;
import com.carnnjoh.poedatatool.model.ItemType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.carnnjoh.poedatatool.model.ItemType.UNIQUE;


@Component
public class InitialDataLoaderUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(InitialDataLoaderBean.class);
	
	@Autowired
	ObjectMapper mapper;

	@Autowired
	UserDAO userDAO;

	@Autowired
	SubscriptionDAO subscriptionDAO;

	@Autowired
	ValuableItemDAO valuableItemDAO;

	@Value("${initial.data.on.startup:false}")
	boolean initializeData;

	@EventListener
	public void insertInitialData(ApplicationReadyEvent event){

		if(initializeData) {
			LOGGER.info("Application is ready, starting to insert data...");

			createUser();
			LOGGER.info("A user is created");

			createSubscription();
			LOGGER.info("A subscription is created");

			createValuableItem();
			LOGGER.info("A valuable item is created");


		} else {
			LOGGER.info("Creation on initial data is turned off...");
		}
	}

	public void createUser() {
		User user = new User(
				"Ritual",
				"Athzen",
				"pc",
				"qwerty"
		);
		userDAO.save(user);
	}

	public void createSubscription() {

		List<ItemType> itemTypes = Arrays.asList(UNIQUE);
		Subscription subscription = new Subscription("qweqwe", new Integer[]{0}, 20.0, "chaos", itemTypes, true);
		subscriptionDAO.save(subscription);
	}

	public void createValuableItem() {

		Subscription subscription = subscriptionDAO.fetch(1);

		Scanner scanner = new Scanner(getClass().getResourceAsStream("/item.txt"));
		StringBuilder sb = new StringBuilder();

		while(scanner.hasNext()) {
			sb.append(scanner.next());
		}

		String itemJson = sb.toString();

		Item item = null;

		try {
			item = mapper.readValue(itemJson, Item.class);
		} catch (JsonProcessingException jpe){
			LOGGER.info("Initially created ValuableItem entity failed to parse the json in 'item.txt'");
			jpe.printStackTrace();
		}

		if(item != null){
			ValuableItem valuableItem = new ValuableItem(
					null,
					"1",
					subscription.getPk(),
					item,
					10, 20, 30, 40,
					LocalDateTime.now()
			);
			valuableItemDAO.save(valuableItem);
		} else {
			LOGGER.info("Item object was null, failed to create valuableItem");
		}
	}
}
