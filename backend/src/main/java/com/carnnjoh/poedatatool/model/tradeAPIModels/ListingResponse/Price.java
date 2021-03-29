package com.carnnjoh.poedatatool.model.tradeAPIModels.ListingResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Price {

	//TODO: see if it is possible to add Currency and type to enums

	@JsonProperty("type")
	public String type;
	@JsonProperty("amount")
	public int amount;
	@JsonProperty("currency")
	public String currency;

}
