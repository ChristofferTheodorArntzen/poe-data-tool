package com.carnnjoh.poedatatool.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrivateStashTab extends StashTab {

	@JsonProperty("numTabs")
	public int numberOfTabs;

	@JsonProperty("currencyLayout")
	public Map<String, Cell> currencyLayout;

	@JsonProperty("fragmentLayout")
	public Map<String, Cell> fragmentLayout;

	@JsonProperty("divinationLayout")
	public DivinationLayout divinationLayout;

	@JsonProperty("delveLayout")
	public Map<String, Cell> delveLayout;

	@JsonProperty("essenceLayout")
	public Map<String, Cell> essenceLayout;

	@JsonProperty(value = "quadLayout", required = false)
	public Boolean isQuadLayout = null;
}
