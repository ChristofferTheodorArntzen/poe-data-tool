package com.carnnjoh.poedatatool.model.queryRequestModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Damage {

	@JsonProperty("min")
	private Double min;

	@JsonProperty("max")
	private Double max;

}
