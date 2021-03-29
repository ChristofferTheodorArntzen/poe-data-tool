package com.carnnjoh.poedatatool.model.tradeAPIModels.QueryRequest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TypeFilter extends Filter {

	@JsonProperty("filters")
	public List<Filter> filters;

}
