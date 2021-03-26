package com.carnnjoh.poedatatool.model.queryRequestModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WeaponFilter {

	@JsonProperty("filters")
	List<Filter> filters;

}
