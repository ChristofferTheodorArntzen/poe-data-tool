package com.carnnjoh.poedatatool.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Properties {

	@JsonProperty("name")
	public String name;

	@JsonProperty("values")
	public List<ValueTypes> valueTypes;

	@JsonProperty("displayMode")
	public Integer displayMode;

	@JsonProperty("type")
	public Integer type;

	@JsonProperty("progress")
	public String progress;
}
