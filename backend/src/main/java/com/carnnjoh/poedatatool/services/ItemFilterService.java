package com.carnnjoh.poedatatool.services;

import com.carnnjoh.poedatatool.model.InMemoryItem;
import com.carnnjoh.poedatatool.model.ItemType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ItemFilterService {

	public Map<String, InMemoryItem> filterItems(Map<String, InMemoryItem> fetchedItems, List<ItemType> itemTypes) {

		for(ItemType itemType : itemTypes) {
			for(InMemoryItem item : fetchedItems.values()) {

				if(!item.isSearch()) {
					continue;
				}

				if(itemType.filter.test(item.getItem())) {
					item.setSearch(true);
				}
			}
		}

		return fetchedItems;
	}
}

