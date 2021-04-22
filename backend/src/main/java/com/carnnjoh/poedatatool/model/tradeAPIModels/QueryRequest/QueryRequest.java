package com.carnnjoh.poedatatool.model.tradeAPIModels.QueryRequest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class QueryRequest {

    @JsonProperty(value = "query", index = 1)
    public Query query;

    @JsonProperty(value = "sort", index = 2)
    public Sort sort;


    public QueryRequest(Query query, Sort sort) {
        this.query = query;
        this.sort = sort;
    }

    public QueryRequest() {
        this.query = new Query(new Status(), new ArrayList<>(), new ArrayList<>(), null, null);
        this.sort = new Sort();
    }

}