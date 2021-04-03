package com.carnnjoh.poedatatool.model.tradeAPIModels.QueryRequest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Dps {

	@JsonProperty("min")
	private Double min;

	@JsonProperty("max")
	private Double max;

}
