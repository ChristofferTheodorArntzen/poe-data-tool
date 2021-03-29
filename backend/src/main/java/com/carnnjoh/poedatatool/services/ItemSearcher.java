package com.carnnjoh.poedatatool.services;

import com.carnnjoh.poedatatool.model.InMemoryItem;
import com.carnnjoh.poedatatool.model.Item;
import com.carnnjoh.poedatatool.model.tradeAPIModels.QueryRequest.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

	private final RestTemplate template = new RestTemplate();

	public List<QueryRequest> searchForItem(Map<String, InMemoryItem> inMemoryItemMap) {

		List<QueryRequest> queryRequests = new ArrayList<>();

		for(InMemoryItem inMemoryItem : inMemoryItemMap.values()) {
			if(inMemoryItem.isSearch()) {

				QueryRequest queryRequest = constructQueryRequest(inMemoryItem.getItem());
				if(queryRequest != null) {
					queryRequests.add(queryRequest);
				}
			}
		}
		return queryRequests;
	}

	//TODO: this needs to be fleshed out
	private QueryRequest constructQueryRequest(Item item) {



		List<Filter> filters = new ArrayList<>();
		List<Stat> stats = new ArrayList<>();
		String name = item.name;
		String type = item.typeLine;

		Status status = new Status();

		Query query = new Query(status, filters ,stats, name, type);
		Sort sort = new Sort();

		if(name == null || name.isEmpty()) {
			return null;
		}

		return new QueryRequest(query, sort);
	}

}
