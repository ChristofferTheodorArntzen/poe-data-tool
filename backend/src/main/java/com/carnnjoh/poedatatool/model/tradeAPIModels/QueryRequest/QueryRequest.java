package com.carnnjoh.poedatatool.model.tradeAPIModels.QueryRequest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class QueryRequest {

	@JsonProperty("query")
	public Query query;

	@JsonProperty()
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