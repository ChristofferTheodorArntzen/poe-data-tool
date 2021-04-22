package com.carnnjoh.poedatatool.model.tradeAPIModels.QueryRequest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Query {

	@JsonProperty("status")
	public Status status;

	@JsonProperty("filters")
	private List<Filter> filters;

	@JsonProperty("stats")
	public List<Stats> stats;

	@JsonProperty("name")
	public String name;

	@JsonProperty("type")
	public String type;

	public Query() {
		this(null, null);
	}

	public Query(String name, String type) {
		this(null, name, type);
	}

	public Query(List<Stats> stats, String name, String type) {
		this(null, stats, name, type);
	}

	public Query(List<Filter> filters, List<Stats> stats, String name, String type) {
		this(new Status(), filters, stats, name, type);
	}

	public Query(Status status, List<Filter> filters, List<Stats> stats, String name, String type) {
		this.status = status;
		this.filters = filters;
		this.stats = stats;
		this.name = name;
		this.type = type;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Filter> getFilters() {
		return filters;
	}

	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}

	public List<Stats> getStats() {
		return stats;
	}

	public void setStats(List<Stats> stats) {
		this.stats = stats;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}


