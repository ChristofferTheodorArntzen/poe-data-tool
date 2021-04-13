package com.carnnjoh.poedatatool.db.inMemory.model.stats;

import com.carnnjoh.poedatatool.db.inMemory.model.shared.DataEntry;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StatsEntry extends DataEntry {

	public String type;

	@JsonProperty("option")
	public SubValues subValue;


	public String getType() {
		return type;
	}

	public SubValues getSubValue() {
		return subValue;
	}
}
