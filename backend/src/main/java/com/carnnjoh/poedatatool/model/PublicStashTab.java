package com.carnnjoh.poedatatool.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PublicStashTab extends StashTab {

	@JsonProperty("accountName") public String accountName;
	@JsonProperty("lastCharacterName") public String lastCharacterName;
	@JsonProperty("id") public String id;
	@JsonProperty("stash") public String stash;
	@JsonProperty("public") public String isPublic;
	@JsonProperty("league") public String league;
}
