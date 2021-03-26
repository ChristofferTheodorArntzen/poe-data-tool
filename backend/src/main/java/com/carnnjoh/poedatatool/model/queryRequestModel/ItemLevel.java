package com.carnnjoh.poedatatool.model.queryRequestModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemLevel {

	@JsonProperty("min")
	private Integer min;

	@JsonProperty("max")
	private Integer max;

}
