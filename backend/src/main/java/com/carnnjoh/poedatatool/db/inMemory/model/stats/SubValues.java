package com.carnnjoh.poedatatool.db.inMemory.model.stats;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SubValues {

	@JsonProperty("options")
	public List<SubValue> subValueList;

}
