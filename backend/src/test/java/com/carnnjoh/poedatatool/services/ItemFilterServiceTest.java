package com.carnnjoh.poedatatool.services;


import com.carnnjoh.poedatatool.TestUtils;
import com.carnnjoh.poedatatool.model.InMemoryItem;
import com.carnnjoh.poedatatool.model.Item;
import com.carnnjoh.poedatatool.model.ItemType;
import com.carnnjoh.poedatatool.model.PrivateStashTab;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ItemFilterServiceTest {

	@Autowired
	private ItemFilterService itemFilterService;

	private Item item;
	private PrivateStashTab privateStashTab;

	@Before
	public void setup() {

		item = TestUtils.getFileFromResourcesAsObject("/item.txt", Item.class);
		privateStashTab = TestUtils.getFileFromResourcesAsObject("/privateStashTab.json", PrivateStashTab.class);
	}

	@Test
	public void testItemFilterService() {
		Map<String, InMemoryItem> inMemoryItemMap = new HashMap<>();
		inMemoryItemMap.put(item.itemId, new InMemoryItem(item, false));

		List<ItemType> itemTypes = Collections.singletonList(ItemType.UNIQUE);

		inMemoryItemMap = itemFilterService.filterItems(inMemoryItemMap, itemTypes);

		Assert.assertTrue(inMemoryItemMap.get(item.itemId).isSearch());
	}

	//TODO: impl tests for all time sorts. see ItemType enum
	@Test
	public void testItemFilterServiceWithFullStashTab() {

		Map<String, InMemoryItem> inMemoryItemMap = new HashMap<>();

		for(Item item : privateStashTab.items) {
			inMemoryItemMap.put(item.itemId, new InMemoryItem(item, false));
		}

		List<ItemType> itemTypes = Collections.singletonList(ItemType.UNIQUE);

		inMemoryItemMap = itemFilterService.filterItems(inMemoryItemMap, itemTypes);

		for(InMemoryItem inMemoryItem : inMemoryItemMap.values()) {
			System.out.println("item with name: " + inMemoryItem.name + "is search: " + inMemoryItem.isSearch());
		}

	}






}
