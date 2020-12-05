package com.carnnjoh.poedatatool.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Properties {

	@JsonProperty("name")
	String name;

	@JsonProperty("values")
	List<Values> valueTypes;

	@JsonProperty("displayMode")
	Integer displayMode;

	@JsonProperty("type")
	Integer type;

	@JsonProperty("progress")
	String progress;
}
