package com.carnnjoh.poedatatool.model.tradeAPIModels.QueryRequest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Query {

	@JsonProperty("status")
	public Status status;

	@JsonProperty("filters")
	private List<Filter> Filters;

	@JsonProperty("stats")
	public List<Stat> stats;

	@JsonProperty("name")
	public String name;

	@JsonProperty("type")
	public String type;

	public Query(Status status, List<Filter> filters, List<Stat> stats, String name, String type) {
		this.status = status;
		Filters = filters;
		this.stats = stats;
		this.name = name;
		this.type = type;
	}
}


