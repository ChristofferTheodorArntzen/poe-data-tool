package com.carnnjoh.poedatatool.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RequirementModel {


	@JsonProperty("name")
	String name;

	@JsonProperty("values")
	List<ValueTypeModel> valueTypes;

	@JsonProperty("displayMode")
	Integer displayMode;

	@JsonProperty("type")
	Integer type;

	@JsonProperty("progress")
	String progress;
}
