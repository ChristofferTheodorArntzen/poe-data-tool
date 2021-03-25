package com.carnnjoh.poedatatool.services;

import com.carnnjoh.poedatatool.model.Item;
import com.carnnjoh.poedatatool.model.ItemType;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ItemFilterService {

	public Collection<Item> filterItems(List<Item> fetchedItems, List<ItemType> itemTypes) {

		Map<String, Item> itemMap = new HashMap<>();

		fetchedItems = fetchedItems
			.stream()
			.filter(item -> item.isIdentified)
			.collect(Collectors.toList());

		for(ItemType itemType : itemTypes) {
			for(Item item : fetchedItems) {
				if(itemType.filter.test(item)) {
					itemMap.put(item.itemId, item);
				}
			}
		}
		return itemMap.values();
	}
}

