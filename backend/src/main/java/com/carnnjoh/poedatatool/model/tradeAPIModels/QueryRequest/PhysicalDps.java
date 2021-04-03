package com.carnnjoh.poedatatool.model.tradeAPIModels.QueryRequest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PhysicalDps {

	@JsonProperty("min")
	private Double min;

	@JsonProperty("max")
	private Double max;

}
