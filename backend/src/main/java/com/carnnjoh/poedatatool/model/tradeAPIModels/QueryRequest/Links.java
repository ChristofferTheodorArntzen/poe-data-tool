package com.carnnjoh.poedatatool.model.tradeAPIModels.QueryRequest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Links {

	@JsonProperty("g")
	public Integer green;

	@JsonProperty("r")
	public Integer red;

	@JsonProperty("b")
	public Integer blue;

	@JsonProperty("w")
	public Integer white;

	@JsonProperty("min")
	public Integer min;

	@JsonProperty("max")
	public Integer max;

}
