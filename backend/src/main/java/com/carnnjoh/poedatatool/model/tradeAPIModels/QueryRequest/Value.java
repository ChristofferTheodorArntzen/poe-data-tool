package com.carnnjoh.poedatatool.model.tradeAPIModels.QueryRequest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Value {

	@JsonProperty("min")
	private Integer min = 0;

	@JsonProperty("max")
	private Integer max = 0;

	public Value() {

	}
	public Value(Integer min, Integer max) {
		this.min = min;
		this.max = max;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}
}
