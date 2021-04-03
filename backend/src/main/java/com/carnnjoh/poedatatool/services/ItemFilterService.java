package com.carnnjoh.poedatatool.services;

import com.carnnjoh.poedatatool.model.InMemoryItem;
import com.carnnjoh.poedatatool.model.ItemType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ItemFilterService {

	/**
	 * Decided if an in memory item is to be searched based on the provided ItemTypes and populates the itemtype list of the same object.
	 * @param fetchedItems Map of currently fetched items.
	 * @param itemTypes a list of enums.
	 */
	public Map<String, InMemoryItem> filterItems(Map<String, InMemoryItem> fetchedItems, List<ItemType> itemTypes) {

		for(ItemType itemType : itemTypes) {
			for(InMemoryItem item : fetchedItems.values()) {
				if(itemType.filter.test(item.getItem())) {
					System.out.println("Item was set to true");
					item.setSearch(true);
					//TODO: this should hopefully only be set once per item (rare item vs. a cluster jewel ???)
					//Need to work more on the test functions for the filters.
					item.setItemType(itemType);
				}
			}
		}

		return fetchedItems;
	}
}

