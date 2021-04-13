package com.carnnjoh.poedatatool.services;

import com.carnnjoh.poedatatool.db.inMemory.dao.ConstantsDao;
import com.carnnjoh.poedatatool.db.inMemory.dao.StatsDao;
import com.carnnjoh.poedatatool.model.InMemoryItem;
import com.carnnjoh.poedatatool.model.Item;
import com.carnnjoh.poedatatool.model.ItemType;
import com.carnnjoh.poedatatool.model.tradeAPIModels.QueryRequest.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class QueryConstructorService {

	@Autowired
	ConstantsDao constantsDao;

	@Autowired
	StatsDao statsDao;

	public List<QueryRequest> createQueryRequests(Map<String, InMemoryItem> inMemoryItemMap) {

		List<QueryRequest> queryRequests = new ArrayList<>();

		for (InMemoryItem inMemoryItem : inMemoryItemMap.values()) {
			if (inMemoryItem.isSearch()) {
				QueryRequest queryRequest = createQueryRequest(inMemoryItem.getItemType(), inMemoryItem);
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

	private QueryRequest createQueryRequest(ItemType itemType, Item item) {


		Query query = createQuery(item);



		//QueryRequest queryRequest = createTestQueryRequest();

		QueryRequest queryRequest = new QueryRequest(query, new Sort());

		return queryRequest;
	}

	private Query createQuery(Item item) {

		Stats stats = new Stats();
		stats.type = QueryFilterType.AND;
		stats.disabled = false;

		List<Filter> filters = new ArrayList<>();

		for(String explicitModText : item.explicitMods) {
			Filter filter = new Filter();
			filter.modId = getModIdFromExplicitMod(explicitModText);

		}

		TypeFilter typeFilter = new TypeFilter();

		stats.filters = Collections.singletonList(typeFilter);
		return new Query("", "");
	}

	private String getModIdFromExplicitMod(String explicitMods) {
		String modId = statsDao.lookUpIdByModText(explicitMods);
		return modId;
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