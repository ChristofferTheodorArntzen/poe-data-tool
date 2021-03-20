package com.carnnjoh.poedatatool.init;

import com.carnnjoh.poedatatool.db.dao.*;
import com.carnnjoh.poedatatool.db.model.ItemFilterType;
import com.carnnjoh.poedatatool.db.model.Subscription;
import com.carnnjoh.poedatatool.db.model.User;
import com.carnnjoh.poedatatool.db.model.ValuableItem;
import com.carnnjoh.poedatatool.model.Item;
import com.carnnjoh.poedatatool.model.ItemType;
import com.carnnjoh.poedatatool.model.ItemTypes;
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
import java.util.*;

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

	@Autowired
	ItemFilterTypeDAO itemFilterTypeDAO;

	@Autowired
	ItemFilterTypeSubscriptionDAO itemFilterTypeSubscriptionDAO;

	@Value("${initial.data.on.startup:false}")
	boolean initializeData;

	@EventListener
	public void insertInitialData(ApplicationReadyEvent event){

		if(initializeData) {
			LOGGER.info("Application is ready, starting to insert data...");

			createUser();
			LOGGER.info("A user is created");

			createItemFilterTypes();
			LOGGER.info("Inserted all item filter types");

			createSubscription();
			LOGGER.info("A subscription is created");

			createValuableItem();
			LOGGER.info("A valuable item is created");


		} else {
			LOGGER.info("Creation on initial data is turned off...");
		}
	}

	private void createItemFilterTypes() {

		try {
//			String content = new String(Files.readAllBytes(Paths.get(getClass().getResource("/itemFilterTypes.json").toURI())));
//
//			ItemTypes itemTypes = mapper.readValue(content, ItemTypes.class);
//
//			List<String> itemTypeNames = itemTypes.itemTypes.stream().map(ItemType::getName).collect(Collectors.toList());
//			itemFilterTypeDAO.batchSaveWithStrings(itemTypeNames);

			List<ItemFilterType> itemFilterTypes = itemFilterTypeDAO.fetchAll();

			if(itemFilterTypes.isEmpty()){
				System.out.println("it didnt work ...");
			}

		} catch ( Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}

	public void createUser() {
		User user = new User(
				null,
				"Heist",
				"Athzen",
				"pc",
				"qwerty"
		);
		userDAO.save(user);
	}

	public void createSubscription() {

		List<ItemFilterType> itemFilterTypes = itemFilterTypeDAO.fetchAll();
		itemFilterTypes = itemFilterTypes.subList(0, 3);

		Subscription subscription = new Subscription(
				"qweqwe",
				new String[]{"1", "2", "3"},
				20.0,
			"chaos",
				itemFilterTypes
		);

		itemFilterTypeSubscriptionDAO.saveSubscription(subscription);
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
					10,
					LocalDateTime.now()
			);
			valuableItemDAO.save(valuableItem);
		} else {
			LOGGER.info("Item object was null, failed to create valuableItem");
		}
	}
}
