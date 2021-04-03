package com.carnnjoh.poedatatool.model.tradeAPIModels.ListingResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {

	@JsonProperty("name")
	public String name;
	@JsonProperty("lastCharacterName")
	public String lastCharacterName;

}
