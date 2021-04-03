package com.carnnjoh.poedatatool.model.tradeAPIModels.ListingResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ListingResponse {

	@JsonProperty("result")
	public List<Listings> result;

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		for(Listings entry : result) {
			sb.append(entry.toString());
		}

		return sb.toString();
	}
}
