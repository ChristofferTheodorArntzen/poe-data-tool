package com.carnnjoh.poedatatool.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CategoryModel {

	@JsonProperty("accessories")
	List<String> accessories;

	@JsonProperty("armour")
	List<String> armour;

	@JsonProperty("jewels")
	List<String> jewels;

	@JsonProperty("weapons")
	List<String> weapons;
}
