package com.carnnjoh.poedatatool.model.tradeAPIModels.QueryRequest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Value {

	@JsonProperty("min")
	private Integer min;

	@JsonProperty("max")
	private Integer max;

}
