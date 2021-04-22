package com.carnnjoh.poedatatool.model.tradeAPIModels.QueryRequest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Stats {

	@JsonProperty("type")
	public String type;

	@JsonProperty("filters")
	public List<Filter> filters;

	@JsonProperty("disabled")
	public boolean disabled;

	public Stats() {
	}
	public Stats(String type, List<Filter> filters) {
		this.type = type;
		this.filters = filters;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Filter> getFilters() {
		return filters;
	}

	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
}
