package com.carnnjoh.poedatatool.model.tradeAPIModels.QueryRequest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WeaponFilter {

	@JsonProperty("filters")
	List<Filter> filters;

}
