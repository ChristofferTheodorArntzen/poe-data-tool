package com.carnnjoh.poedatatool.model.tradeAPIModels;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueryResponse {

	@JsonProperty("id")
	public String id;

	@JsonProperty("result")
	public String[] result;

	@JsonProperty("total")
	public int total;

}
