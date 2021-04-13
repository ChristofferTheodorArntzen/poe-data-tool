package com.carnnjoh.poedatatool.db.inMemory.model.stats;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StatsRoot {

	@JsonProperty("result")
	public List<StatsResult> statsResults;

}
