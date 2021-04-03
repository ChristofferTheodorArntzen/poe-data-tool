package com.carnnjoh.poedatatool.model.tradeAPIModels.QueryRequest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AttackSpeed {

	@JsonProperty("min")
	private Double min;

	@JsonProperty("max")
	private Double max;

}
