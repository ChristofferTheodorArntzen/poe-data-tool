package com.carnnjoh.poedatatool.services;

import com.carnnjoh.poedatatool.model.InMemoryItem;
import com.carnnjoh.poedatatool.model.Item;
import com.carnnjoh.poedatatool.model.ItemType;
import com.carnnjoh.poedatatool.model.tradeAPIModels.QueryRequest.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//TODO: rename with a more appropriate name

@Service
public class ItemSearcher {

	//Henter alle stats i spillet?
	//https://www.pathofexile.com/api/trade/data/stats

	// Henter statiske props
	// https://www.pathofexile.com/api/trade/data/static

	// Bygge query component

	// Sende søke på dette apiet
	// https://www.pathofexile.com/api/trade/search/{league}

	// få resultatsetet
	// https://www.pathofexile.com/api/trade/fetch/{items}

	public List<QueryRequest> createQueryRequests(Map<String, InMemoryItem> inMemoryItemMap) {

		List<QueryRequest> queryRequests = new ArrayList<>();

		for (InMemoryItem inMemoryItem : inMemoryItemMap.values()) {
			if (inMemoryItem.isSearch()) {
				QueryRequest queryRequest = bigAssSwitchCase(inMemoryItem.getItemType(), inMemoryItem.getItem());
				if (queryRequest != null) {
					queryRequests.add(queryRequest);
				}
			}
		}
		return queryRequests;
	}

	//TODO: this needs to be fleshed out
	// want specify how to construct the request query per itemType.
	// how to impl without switch case for each itemType here.

	private QueryRequest constructQueryRequest(InMemoryItem inMemoryItem) {

		List<Filter> filters = new ArrayList<>();
		List<Stats> stats = new ArrayList<>();
		String name = inMemoryItem.getItem().name;
		String type = inMemoryItem.getItem().typeLine;
		Status status = new Status();
		Query query = new Query(status, filters, stats, name, type);
		Sort sort = new Sort();

		if (name == null || name.isEmpty()) {
			return null;
		}

		return new QueryRequest(query, sort);
	}

	private QueryRequest bigAssSwitchCase(ItemType itemType, Item item) {

		switch(itemType) {
			case UNIQUE: return constructDefaultUniqueQuery(itemType, item);
			case CARD: return constructCardQuery(itemType, item);
			default: return null;
		}

	}

	private QueryRequest constructCardQuery(ItemType itemtype, Item item) {

		return null;
	}

	private QueryRequest constructDefaultUniqueQuery(ItemType itemType, Item item) {
		Query query = new Query(item.name, item.typeLine);
		Sort sort = new Sort();
		return new QueryRequest(query, sort);
	}

	private QueryRequest constructDefaultQuery(Item item) {

		List<Filter> filters = new ArrayList<>();
		Filter filter = new TypeFilter();
		filters.add(filter);

		List<Stats> stats = new ArrayList<>();
		Stats stat = new Stats();
		stat.type = QueryFilterType.AND;
		stat.disabled = false;
		stat.filters = new ArrayList<>();
		stats.add(stat);

		Query query =  new Query(filters, stats, item.name, item.typeLine);

		return new QueryRequest();
	}

	private QueryRequest createTestQueryRequest() {
		List<Filter> filters = new ArrayList<>();
		List<Stats> stats = new ArrayList<>();
		String name = "Tabula Rasa";
		String type = "Simple Robe";
		Status status = new Status();
		Query query = new Query(status, filters, stats, name, type);
		Sort sort = new Sort();

		return new QueryRequest(query, sort);
	}
}