package com.carnnjoh.poedatatool.db.inMemory.model.unique;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UniqueRoot {

	@JsonProperty("result")
	public List<UniqueResult> uniqueResults;

	public List<UniqueResult> getUniqueResults() {
		return uniqueResults;
	}
}
