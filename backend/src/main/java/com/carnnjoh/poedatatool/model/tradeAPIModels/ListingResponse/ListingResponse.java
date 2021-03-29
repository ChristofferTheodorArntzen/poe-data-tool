package com.carnnjoh.poedatatool.model.tradeAPIModels.ListingResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ListingResponse {

	@JsonProperty("result")
	public List<Listings> result;

}
