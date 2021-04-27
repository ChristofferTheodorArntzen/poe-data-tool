package com.carnnjoh.poedatatool.init;

import com.carnnjoh.poedatatool.db.dao.SubscriptionDAO;
import com.carnnjoh.poedatatool.db.dao.UserDAO;
import com.carnnjoh.poedatatool.db.dao.ValuableItemDAO;
import com.carnnjoh.poedatatool.db.inMemory.dao.ConstantsDao;
import com.carnnjoh.poedatatool.db.inMemory.dao.StatsDao;
import com.carnnjoh.poedatatool.db.inMemory.dao.UniqueDao;
import com.carnnjoh.poedatatool.db.inMemory.model.constant.Constant;
import com.carnnjoh.poedatatool.db.inMemory.model.constant.ConstantsRoot;
import com.carnnjoh.poedatatool.db.inMemory.model.shared.DataEntry;
import com.carnnjoh.poedatatool.db.inMemory.model.stats.StatsResult;
import com.carnnjoh.poedatatool.db.inMemory.model.stats.StatsRoot;
import com.carnnjoh.poedatatool.db.inMemory.model.unique.UniqueEntry;
import com.carnnjoh.poedatatool.db.inMemory.model.unique.UniqueResult;
import com.carnnjoh.poedatatool.db.inMemory.model.unique.UniqueRoot;
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
import java.util.*;
import java.util.stream.Collectors;

import static com.carnnjoh.poedatatool.model.ItemType.*;


@Component
public class InitialDataLoader {

	private static final Logger LOGGER = LoggerFactory.getLogger(InitialDataLoader.class);

	@Autowired
	ObjectMapper mapper;

	@Autowired
	UserDAO userDAO;

	@Autowired
	SubscriptionDAO subscriptionDAO;

	@Autowired
	ValuableItemDAO valuableItemDAO;

	@Autowired
	ConstantsDao constantsDao;

	@Autowired
	StatsDao statsDao;

	@Autowired
	UniqueDao uniqueDao;

	@Value("${initial.data.on.startup:false}")
	boolean testData;

	// static texts for Unique Static import
	private static final String CURRENCY = "Currency";
	private static final String SPLINTERS = "Splinters";
	private static final String FRAGMENTS = "Fragments";
	private static final String DELIRIUM_ORBS = "DeliriumOrbs";
	private static final String CATALYSTS = "Catalysts";
	private static final String OILS = "Oils";
	private static final String INCUBATORS = "Incubators";
	private static final String SCARABS = "Scarabs";
	private static final String DELVE_RESONATORS = "DelveResonators";
	private static final String DELVE_FOSSILS = "DelveFossils";
	private static final String ESSENCES = "Essences";
	private static final String CARDS = "Cards";

	// static texts for stats import
	private static final String PSEUDO = "Pseudo";
	private static final String EXPLICIT = "Explicit";
	private static final String IMPLICIT = "Implicit";
	private static final String FRACTURED = "Fractured";
	private static final String ENCHANT = "Enchant";
	private static final String CRAFTED = "Crafted";
	private static final String VEILED = "Veiled";
	private static final String MONSTER = "Monster";
	private static final String DELVE = "Delve";

	// static texts for unique import
	private static final String ACCESSORIES = "Accessories";
	private static final String ARMOUR = "Armour";
	private static final String FLASKS = "Flasks";
	private static final String GEMS = "Gems";
	private static final String JEWELS = "Jewels";
	private static final String MAPS = "Maps";
	private static final String WEAPONS = "Weapons";
	private static final String LEAGUE_STONES = "Leaguestones";
	private static final String PROPHECIES = "Prophecies";
	private static final String ITEMISED_MONSTERS = "Itemised Monsters";
	//	private static final String CARDS = "Cards";
	//	private static final String CURRENCY = "Currency";

	@EventListener
	public void insertInitialData(ApplicationReadyEvent event) {

		LOGGER.info("Application is ready, starting to insert data...");

		long startTime = System.currentTimeMillis();
		createConstantsImport();
		LOGGER.info("Importing 'static' took '{}'ms", System.currentTimeMillis() - startTime);

		startTime = System.currentTimeMillis();
		createStatsImport();
		LOGGER.info("Importing 'stats' took '{}'ms", System.currentTimeMillis() - startTime);

		startTime = System.currentTimeMillis();
		createUniqueImport();
		LOGGER.info("Importing 'unique' took '{}'ms", System.currentTimeMillis() - startTime);

		LOGGER.info("Done importing essential data.");

		if (testData) {

			LOGGER.info("Application is run in test mode, starting to create test data...");

			createUser();
			LOGGER.info("user(s) is created");

			createSubscription();
			LOGGER.info("subscription(s) is created");

			createValuableItem();
			LOGGER.info("valuable item(s) is created");

			LOGGER.info("Done importing test data.");
		}
	}

	//TODO: move insertion of constants, stats and unqiues to each of its own beans. Try to make the code footprint smaller in this class atleast
	private void createUniqueImport() {
		UniqueRoot uniqueRoot = InitUtils.getFileFromResourcesAsObject("/unique-items.json", UniqueRoot.class);

		//TODO add support for getting this from the API
		if (uniqueRoot == null || uniqueRoot.uniqueResults == null) {
			System.exit(-1);
		}

		for (UniqueResult unique : uniqueRoot.uniqueResults) {

			switch (unique.label) {
				case GEMS:
					uniqueDao.setGemsMap(convertGemEntryToMap(unique.uniqueEntries));
					break;
				case ACCESSORIES:
					uniqueDao.setAccessoriesMap(convertUniqueItemEntryToUniqueEquipmentMap(unique.uniqueEntries, UNIQUE_ACCESSORY));
					break;
				case ARMOUR:
					uniqueDao.setArmourMap(convertUniqueItemEntryToUniqueEquipmentMap(unique.uniqueEntries, UNIQUE_ARMOUR));
					break;
				case FLASKS:
					uniqueDao.setFlasksMap(convertUniqueItemEntryToUniqueEquipmentMap(unique.uniqueEntries, UNIQUE_FLASK));
					break;
				case JEWELS:
					uniqueDao.setJewelsMap(convertUniqueItemEntryToUniqueEquipmentMap(unique.uniqueEntries, UNIQUE_JEWEL));
					break;
				case MAPS:
					uniqueDao.setMapsMap(convertUniqueItemEntryToUniqueEquipmentMap(unique.uniqueEntries, UNIQUE_MAP));
					break;
				case WEAPONS:
					uniqueDao.setWeaponsMap(convertUniqueItemEntryToUniqueEquipmentMap(unique.uniqueEntries, UNIQUE_WEAPON));
					break;
				case PROPHECIES:
					uniqueDao.setPropheciesMap(convertPropheciesEntryToMap(unique.uniqueEntries));
					break;
			}
		}
	}

	private Map<String, String> convertPropheciesEntryToMap(List<UniqueEntry> uniqueEntries) {

		Map<String, String> prophecyMap = new HashMap<>();

		for (UniqueEntry entry : uniqueEntries) {
			prophecyMap.put(entry.name, entry.getText());
		}

		return prophecyMap;
	}

	private Map<String, ItemType> convertUniqueItemEntryToUniqueEquipmentMap(List<UniqueEntry> uniqueEntries, ItemType itemType) {

		Map<String, ItemType> uniqueItemMap = new HashMap<>();

		for (UniqueEntry entry : uniqueEntries) {
			uniqueItemMap.put(entry.name, itemType);
		}

		return uniqueItemMap;
	}

	private Map<String, String> convertGemEntryToMap(List<UniqueEntry> uniqueEntries) {
		return uniqueEntries.stream().collect(Collectors.toMap(UniqueEntry::getText, UniqueEntry::getType));
	}

	private void createStatsImport() {

		StatsRoot statsRoot = InitUtils.getFileFromResourcesAsObject("/stats-import.json", StatsRoot.class);

		//TODO: add support for getting this from the API
		if (statsRoot == null || statsRoot.statsResults == null) {
			System.exit(-1);
		}

		for (StatsResult stats : statsRoot.statsResults) {
			// TODO: add support for 'SubValue'
			switch (stats.label) {
				case PSEUDO:
					statsDao.setPseudoModMap(convertEntryToMap(stats.statsEntries));
					break;
				case EXPLICIT:
					statsDao.setExplicitModMap(convertEntryToMap(stats.statsEntries));
					break;
				case IMPLICIT:
					statsDao.setImplicitModMap(convertEntryToMap(stats.statsEntries));
					break;
				case FRACTURED:
					statsDao.setFracturedModMap(convertEntryToMap(stats.statsEntries));
					break;
				case ENCHANT:
					statsDao.setEnchantModMap(convertEntryToMap(stats.statsEntries));
					break;
				case CRAFTED:
					statsDao.setCraftedModMap(convertEntryToMap(stats.statsEntries));
					break;
				case VEILED:
					statsDao.setVeiledModMap(convertEntryToMap(stats.statsEntries));
					break;
				case MONSTER:
					statsDao.setMonsterModMap(convertEntryToMap(stats.statsEntries));
					break;
				case DELVE:
					statsDao.setDelveModMap(convertEntryToMap(stats.statsEntries));
					break;
			}
		}
	}

	private void createConstantsImport() {
		ConstantsRoot constantsRoot = InitUtils.getFileFromResourcesAsObject("/static-import.json", ConstantsRoot.class);

		//TODO: add support for getting this from the API
		if (constantsRoot == null || constantsRoot.constants == null) {
			System.exit(-1);
		}

		for (Constant constant : constantsRoot.constants) {
			switch (constant.id) {
				case CURRENCY:
					constantsDao.setCurrencyMap(convertEntryToMap(constant.entries));
					break;
				case SPLINTERS:
					constantsDao.setSplinterMap(convertEntryToMap(constant.entries));
					break;
				case FRAGMENTS:
					constantsDao.setFragmentMap(convertEntryToMap(constant.entries));
					break;
				case DELIRIUM_ORBS:
					constantsDao.setDeliriumOrbMap(convertEntryToMap(constant.entries));
					break;
				case CATALYSTS:
					constantsDao.setCatalystMap(convertEntryToMap(constant.entries));
					break;
				case OILS:
					constantsDao.setOilMap(convertEntryToMap(constant.entries));
					break;
				case INCUBATORS:
					constantsDao.setIncubatorMap(convertEntryToMap(constant.entries));
					break;
				case SCARABS:
					constantsDao.setScarabMap(convertEntryToMap(constant.entries));
					break;
				case DELVE_RESONATORS:
					constantsDao.setDelveResonatorMap(convertEntryToMap(constant.entries));
					break;
				case DELVE_FOSSILS:
					constantsDao.setDelveFossilMap(convertEntryToMap(constant.entries));
					break;
				case ESSENCES:
					constantsDao.setEssenceMap(convertEntryToMap(constant.entries));
					break;
				case CARDS:
					constantsDao.setCardMap(convertEntryToMap(constant.entries));
					break;
			}
		}
	}

	private <T extends DataEntry> Map<String, String> convertEntryToMap(List<T> dataEntries) {

		Map<String, String> entryMap = new HashMap<>();

		for (DataEntry entry : dataEntries) {
			entryMap.put(entry.text, entry.id);
		}

		return entryMap;
	}

	public void createUser() {
		User user = new User(
			"Ultimatum",
			"Athzen",
			"pc",
			"qwerty"
		);
		userDAO.save(user);
	}

	public void createSubscription() {


		Subscription subscription = new Subscription(
				"High value subscription",
				new Integer[]{1, 2, 3},
				1.0,
				"Exalted",
				Arrays.asList(UNIQUE, RARE_ACCESSORY, RARE_WEAPON),
				true
		);
		subscriptionDAO.save(subscription);

		Subscription subscription1 = new Subscription(
				"Dump tab",
				new Integer[]{0, 1, 2},
				1.0,
				"Chaos",
				Arrays.asList(UNIQUE_ACCESSORY,
						UNIQUE_ARMOUR,
						UNIQUE_WEAPON,
						UNIQUE_MAP,
						UNIQUE_FLASK,
						UNIQUE_JEWEL,
						MAP,
						SCARAB,
						GEM,
						ABYSSAL_JEWEL,
						CARD,
						FRAGMENT,
						OIL,
						PROPHECY,
						FOSSIL,
						DELIRIUM_ORB
						),
				false);
		subscriptionDAO.save(subscription1);
	}

	public void createValuableItem() {

		Subscription subscription = subscriptionDAO.fetch(1);

		Scanner scanner = new Scanner(getClass().getResourceAsStream("/item.txt"));
		StringBuilder sb = new StringBuilder();

		while (scanner.hasNext()) {
			sb.append(scanner.next());
		}

		String itemJson = sb.toString();

		Item item = null;

		try {
			item = mapper.readValue(itemJson, Item.class);
		} catch (JsonProcessingException jpe) {
			LOGGER.info("Initially created ValuableItem entity failed to parse the json in 'item.txt'");
			jpe.printStackTrace();
		}

		if (item != null) {
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
