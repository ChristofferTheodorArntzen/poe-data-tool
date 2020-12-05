package com.carnnjoh.poedatatool.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Requirements {


	@JsonProperty("name")
	String name;

	@JsonProperty("values")
	Object[] valueTypes;

	@JsonProperty("displayMode")
	Integer displayMode;

	@JsonProperty("type")
	Integer type;

	@JsonProperty("progress")
	String progress;
}
