package com.carnnjoh.poedatatool.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public abstract class StashTab {

	@JsonProperty("items") public List<Item> items;

}
