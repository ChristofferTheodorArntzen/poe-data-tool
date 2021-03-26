package com.carnnjoh.poedatatool.model.queryRequestModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Stat {

	@JsonProperty("type")
	public QueryFilterType type;

	@JsonProperty("filters")
	public List<Filter> filters;

	@JsonProperty("disabled")
	public boolean disabled;

}
