package com.carnnjoh.poedatatool.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StashTabsModel {

	@JsonProperty("next_change_id") String changeId;
	@JsonProperty("stashes") List<StashTabModel> stashes;
}
