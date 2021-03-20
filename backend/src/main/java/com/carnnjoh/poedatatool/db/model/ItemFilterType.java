package com.carnnjoh.poedatatool.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemFilterType {

	@JsonIgnore
	private Integer pk;
	@JsonProperty("name")
	private String name;

	public ItemFilterType() {
	}

	public ItemFilterType(String name) {
		this.name = name;
	}

	public ItemFilterType(Integer pk, String name) {
		this.pk = pk;
		this.name = name;
	}

	public ItemFilterType copy() {
		return new ItemFilterType(this.name);
	}

	public Integer getPk() {
		return pk;
	}

	public void setPk(Integer pk) {
		this.pk = pk;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
