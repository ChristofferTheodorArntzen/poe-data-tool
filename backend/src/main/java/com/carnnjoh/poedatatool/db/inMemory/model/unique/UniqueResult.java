package com.carnnjoh.poedatatool.db.inMemory.model.unique;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UniqueResult {

	@JsonProperty("label")
	public String label;

	@JsonProperty("entries")
	public List<UniqueEntry> uniqueEntries;

	public String getLabel() {
		return label;
	}

	public List<UniqueEntry> getUniqueEntries() {
		return uniqueEntries;
	}
}
