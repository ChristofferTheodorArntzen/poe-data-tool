package com.carnnjoh.poedatatool.model.queryRequestModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GemLevel {

	@JsonProperty("min")
	private Integer min;

	@JsonProperty("max")
	private Integer max;

}
