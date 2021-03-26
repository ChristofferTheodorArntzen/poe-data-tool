package com.carnnjoh.poedatatool.model.queryRequestModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TypeFilter extends Filter {

	@JsonProperty("filters")
	public List<Filter> filters;

}
