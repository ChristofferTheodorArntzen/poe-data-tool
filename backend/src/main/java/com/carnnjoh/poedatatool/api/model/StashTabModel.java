package com.carnnjoh.poedatatool.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StashTabModel {

	@JsonProperty("accountName") String accountName;
	@JsonProperty("lastCharacterName") String lastCharacterName;
	@JsonProperty("id") String id;
	@JsonProperty("Stash") String stash;
	@JsonProperty("items") List<ItemModel> items;
	@JsonProperty("public") String isPublic;
	@JsonProperty("league") String league;
}
