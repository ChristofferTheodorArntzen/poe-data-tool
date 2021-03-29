package com.carnnjoh.poedatatool.model.tradeAPIModels.ListingResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stash {

	@JsonProperty("name")
	public String name;

	@JsonProperty("x")
	public int xPos;

	@JsonProperty("y")
	public int yPos;


}
