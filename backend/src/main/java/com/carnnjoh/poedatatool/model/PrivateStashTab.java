package com.carnnjoh.poedatatool.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrivateStashTab {

	@JsonProperty("numTabs")
	int numberOfTabs;

	@JsonProperty("currencyLayout")
	List<Object> currencyLayout;

	@JsonProperty("fragmentLayout")
	List<Object> fragmentLayout;

	@JsonProperty("divinationLayout")
	List<Object> divinationLayout;

	@JsonProperty("delveLayout")
	List<Object> delveLayout;

	@JsonProperty("essenceLayout")
	List<Object> essenceLayout;


	@JsonProperty(value = "quadLayout", required = false)
	Boolean isQuadLayout = null;

	@JsonProperty("items")
	List<Item> items;

}
