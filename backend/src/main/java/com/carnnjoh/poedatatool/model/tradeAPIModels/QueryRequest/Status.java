package com.carnnjoh.poedatatool.model.tradeAPIModels.QueryRequest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Status {

	@JsonProperty("option")
	public String option = "online";

	public Status() {
	}

	public Status(String option) {
		this.option = option;
	}

}
