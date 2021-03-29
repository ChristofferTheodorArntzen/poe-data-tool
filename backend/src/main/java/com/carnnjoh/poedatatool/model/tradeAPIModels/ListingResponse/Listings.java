package com.carnnjoh.poedatatool.model.tradeAPIModels.ListingResponse;

import com.carnnjoh.poedatatool.model.Item;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Listings {

	@JsonProperty("id")
	public String id;

	@JsonProperty("listing")
	public Listing listing;

	@JsonProperty("item")
	public Item item;

}
