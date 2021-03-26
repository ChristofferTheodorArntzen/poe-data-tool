package com.carnnjoh.poedatatool.model.queryRequestModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Level {

	@JsonProperty("min")
	private Integer min;

	@JsonProperty("max")
	private Integer max;

}
