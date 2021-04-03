package com.carnnjoh.poedatatool.model.tradeAPIModels.QueryRequest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Stats {

	@JsonProperty("type")
	public QueryFilterType type;

	@JsonProperty("filters")
	public List<Filter> filters;

	@JsonProperty("disabled")
	public boolean disabled;

}
