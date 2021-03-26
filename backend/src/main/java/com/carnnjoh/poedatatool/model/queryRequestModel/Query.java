package com.carnnjoh.poedatatool.model.queryRequestModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Query {

	@JsonProperty("status")
	public Status status;

	@JsonProperty("filters")
	private List<Filter> Filters;

	@JsonProperty("stat")
	public List<Stat> stats;

}


