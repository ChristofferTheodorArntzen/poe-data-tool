package com.carnnjoh.poedatatool.db.model;

import com.carnnjoh.poedatatool.api.requestobjects.SubscriptionRequest;
import com.carnnjoh.poedatatool.model.ItemType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Entity to save, update and delete Subscriptions
 */
public class Subscription {

	@JsonProperty("pk")
	private Integer pk;

	@JsonProperty("name")
	private String name;

	//TODO: should maybe be a relation...? -- do with array until it is necessary
	@JsonProperty("tabIds")
	private Integer[] tabIds;

	@JsonProperty("currencyThreshold")
	private double currencyThreshold;

	@JsonProperty("currencyType")
	private String currencyType;

	@JsonProperty("itemTypes")
	private List<ItemType> itemTypes;

	@JsonProperty("isActive")
	private boolean isActive;

	public Subscription() {
		this("", null, 0.0,"", new ArrayList<>(), true);
	}

	public Subscription(String name,
						Integer[] tabIds,
						Double currencyThreshold,
						String currencyType,
						List<ItemType> itemTypes,
						boolean isActive
	) {
		this.name = name;
		this.tabIds = tabIds;
		this.currencyThreshold = currencyThreshold;
		this.currencyType = currencyType;
		this.itemTypes = itemTypes;
		this.isActive = isActive;
	}

	public Subscription(Integer pk,
						String name,
						Integer[] tabIds,
						Double currencyThreshold,
						String currencyType,
						List<ItemType> itemTypes,
						boolean isActive
	) {
		this.pk = pk;
		this.name = name;
		this.tabIds = tabIds;
		this.currencyThreshold = currencyThreshold;
		this.currencyType = currencyType;
		this.itemTypes = itemTypes;
		this.isActive = isActive;
	}

	public Subscription(SubscriptionRequest subscriptionRequest) {
		this(subscriptionRequest.getName(),
			subscriptionRequest.getTabIds(),
			subscriptionRequest.getCurrencyThreshold(),
			subscriptionRequest.getCurrencyType(),
			subscriptionRequest.getItemTypes(),
			subscriptionRequest.isActive()
		);
	}

	public void setPk(Integer pk) {
		this.pk = pk;
	}

	public Integer getPk() {
		return pk;
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

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public List<ItemType> getItemTypes() {
		return itemTypes;
	}

	public void setItemTypes(List<ItemType> itemTypes) {
		this.itemTypes = itemTypes;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	@Override
	public String toString() {
		return "Subscription{" +
			"pk=" + pk +
			", name='" + name + '\'' +
			", tabIds=" + Arrays.toString(tabIds) +
			", currencyThreshold=" + currencyThreshold +
			", currencyType='" + currencyType + '\'' +
			", itemTypes=" + itemTypes +
			'}';
	}

	public Subscription copy() {
		return new Subscription(
			this.pk,
			this.name,
			this.tabIds,
			this.currencyThreshold,
			this.currencyType,
			this.itemTypes,
			this.isActive
		);
	}
}
