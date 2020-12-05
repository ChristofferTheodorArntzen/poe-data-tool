package com.carnnjoh.poedatatool.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StashTabs {

	@JsonProperty("next_change_id") public String changeId;
	@JsonProperty("stashes") public List<StashTab> stashes;
}
