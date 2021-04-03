package com.carnnjoh.poedatatool.model.tradeAPIModels.QueryRequest;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Rarity {

	enum RarityOption {

		any,
		magic,
		rare,
		unique,
		uniquefoil,
		nonunique

	}

	@JsonProperty("option")
	public RarityOption option;

}
