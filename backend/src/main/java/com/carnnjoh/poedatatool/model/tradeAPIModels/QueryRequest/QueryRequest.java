package com.carnnjoh.poedatatool.model.tradeAPIModels.QueryRequest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryRequest {

	@JsonProperty("query")
	public Query query;

	@JsonProperty()
	public Sort sort;


	public QueryRequest(Query query, Sort sort) {
		this.query = query;
		this.sort = sort;
	}
}