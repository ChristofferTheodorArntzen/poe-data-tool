package com.carnnjoh.poedatatool.api.requestobjects;

import com.carnnjoh.poedatatool.model.ItemType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;

public class SubscriptionRequest {

	@JsonProperty("name")
	private String name;

	@JsonProperty("tabIds")
	private Integer[] tabIds;

	@JsonProperty("currencyThreshold")
	private double currencyThreshold;

	@JsonProperty("itemTypes")
	private List<ItemType> itemTypes;

	@JsonProperty("currencyType")
	private String currencyType;

	@JsonProperty("isActive")
	private boolean isActive;

	public SubscriptionRequest() {
	}

	public SubscriptionRequest(String name,
							   Integer[] tabIds,
							   Double currencyThreshold,
							   List<ItemType> itemTypes,
							   String currencyType,
							   boolean isActive
	) {
		this.name = name;
		this.tabIds = tabIds;
		this.currencyThreshold = currencyThreshold;
		this.itemTypes = itemTypes;
		this.currencyType = currencyType;
		this.isActive = isActive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer[] getTabIds() {
		return tabIds;
	}

	public void setTabIds(Integer[] tabIds) {
		this.tabIds = tabIds;
	}

	public double getCurrencyThreshold() {
		return currencyThreshold;
	}

	public void setCurrencyThreshold(double currencyThreshold) {
		this.currencyThreshold = currencyThreshold;
	}

	public List<ItemType> getItemTypes() {
		return itemTypes;
	}

	public void setItemTypes(List<ItemType> itemTypes) {
		this.itemTypes = itemTypes;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	@Override
	public String toString() {
		return "SubscriptionRequest{" +
			"name='" + name + '\'' +
			", tabIds=" + Arrays.toString(tabIds) +
			", currencyThreshold=" + currencyThreshold +
			", itemFilter='" + itemTypes + '\'' +
			", currencyType='" + currencyType + '\'' +
			'}';
	}
}
