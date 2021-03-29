package com.carnnjoh.poedatatool.model.tradeAPIModels.ListingResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Listing {

	@JsonProperty("method")
	public String method;

	@JsonProperty("indexed")
	public LocalDateTime indexed;

	@JsonProperty("stash")
	public Stash stash;

	@JsonProperty("whisper")
	public String whisper;

	@JsonProperty("account")
	public Account account;

	@JsonProperty("price")
	public Price price;

}
