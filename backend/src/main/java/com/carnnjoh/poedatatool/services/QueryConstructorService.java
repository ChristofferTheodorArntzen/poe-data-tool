package com.carnnjoh.poedatatool.services;

import com.carnnjoh.poedatatool.db.inMemory.dao.ConstantsDao;
import com.carnnjoh.poedatatool.db.inMemory.dao.StatsDao;
import com.carnnjoh.poedatatool.model.InMemoryItem;
import com.carnnjoh.poedatatool.model.InterpretedMod;
import com.carnnjoh.poedatatool.model.Item;
import com.carnnjoh.poedatatool.model.ItemType;
import com.carnnjoh.poedatatool.model.tradeAPIModels.QueryRequest.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QueryConstructorService {

    @Autowired
    private ConstantsDao constantsDao;

    @Autowired
    private ExplicitModExtractorService explicitModExtractorService;

    public List<QueryRequest> createQueryRequests(Map<String, InMemoryItem> inMemoryItemMap) {

        List<QueryRequest> queryRequests = new ArrayList<>();

        for (InMemoryItem inMemoryItem : inMemoryItemMap.values()) {
            if (inMemoryItem.isSearch()) {
                queryRequests.add(createQuery(inMemoryItem));
            }
        }

        return queryRequests;
    }

    public Map<String, QueryRequest> createQueryRequestsItemMap(Map<String, InMemoryItem> inMemoryItemMap) {

        Map<String, QueryRequest> queryRequests = new HashMap<>();

        for (InMemoryItem inMemoryItem : inMemoryItemMap.values()) {
            if (inMemoryItem.isSearch()) {
                queryRequests.put(inMemoryItem.itemId, createQuery(inMemoryItem));
            }
        }

        return queryRequests;
    }

    private QueryRequest createQuery(final InMemoryItem inMemoryItem) {

        Query query = new Query();

        List<Stats> statsList = new ArrayList<>();

        Stats stats = createStatsFilter(inMemoryItem);

        if (stats != null) {
            statsList.add(createStatsFilter(inMemoryItem));
        }

        if (statsList.size() > 0) {
            query.setStats(statsList);
        }

        if (inMemoryItem.name != null && !inMemoryItem.name.isEmpty() && !inMemoryItem.name.isBlank()) {
            query.setName(inMemoryItem.name);
        }

        if (inMemoryItem.baseType != null && !inMemoryItem.baseType.isEmpty() && !inMemoryItem.baseType.isBlank()) {
            query.setType(inMemoryItem.baseType);
        }

        return new QueryRequest(query, new Sort());
    }

    private Stats createStatsFilter(final InMemoryItem inMemoryItem) {

        if (inMemoryItem.getInterpretedMods() == null || inMemoryItem.getInterpretedMods().size() == 0) {
            return null;
        }

        List<Filter> filters = new ArrayList<>();

        for (InterpretedMod interpretedMod : inMemoryItem.getInterpretedMods()) {

            Filter filter = new Filter();

            if (interpretedMod.getRelatedModId() != null) {

                filter.modId = interpretedMod.getRelatedModId();

                Value value = new Value();

                if (interpretedMod.getMinValue() != null) {
                    value.setMin(interpretedMod.getMinValue().intValue());
                }

                if (interpretedMod.getMaxValue() != null) {
                    value.setMax(interpretedMod.getMaxValue().intValue());
                }

                if (value.getMax() != null || value.getMin() != null) {
                    filter.value = value;
                }
            }

            if (filter.modId != null) {
                filters.add(filter);
            }

        }

        return (filters.size() > 0) ? new Stats(QueryFilterType.AND.getText(), filters) : null;
    }

}