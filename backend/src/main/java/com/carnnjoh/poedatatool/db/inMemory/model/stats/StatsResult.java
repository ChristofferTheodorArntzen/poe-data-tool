package com.carnnjoh.poedatatool.db.inMemory.model.stats;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StatsResult {

	@JsonProperty("id")
	public String id;

	@JsonProperty("label")
	public String label;

	@JsonProperty("entries")
	public List<StatsEntry> statsEntries;

}
