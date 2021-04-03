package com.carnnjoh.poedatatool.model.tradeAPIModels.QueryRequest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sort {
	//TODO: se if this is right?
	@JsonProperty("price")
	public String price;

	public Sort() {
		this.price = "asc";
	}
	public Sort(String price) {
		this.price = price;
	}
}
