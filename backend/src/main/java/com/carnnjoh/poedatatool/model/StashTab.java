package com.carnnjoh.poedatatool.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StashTab {

	@JsonProperty("accountName") public String accountName;
	@JsonProperty("lastCharacterName") public String lastCharacterName;
	@JsonProperty("id") public String id;
	@JsonProperty("stash") public String stash;
	@JsonProperty("items") public List<Item> items;
	@JsonProperty("public") public String isPublic;
	@JsonProperty("league") public String league;
}
