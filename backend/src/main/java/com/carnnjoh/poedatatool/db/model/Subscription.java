package com.carnnjoh.poedatatool.db.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Subscription {

	private Integer pk;
	private String name;
	//TODO: should maybe be a relation...?
	private Integer[] tabIds;
	private double currencyThreshold;
	private String currencyType;
	private List<ItemFilterType> itemFilterTypes;

	public Subscription() {
		this("", null, 0.0,"", new ArrayList<>());
	}

	public Subscription(String name,
						Integer[] tabIds,
						Double currencyThreshold,
						String currencyType,
						List<ItemFilterType> itemFilterTypes) {
		this.name = name;
		this.tabIds = tabIds;
		this.currencyThreshold = currencyThreshold;
		this.currencyType = currencyType;
		this.itemFilterTypes = itemFilterTypes;
	}

	public Subscription(Integer pk,
						String name,
						Integer[] tabIds,
						Double currencyThreshold,
						String currencyType,
						List<ItemFilterType> itemFilterTypes) {
		this.pk = pk;
		this.name = name;
		this.tabIds = tabIds;
		this.currencyThreshold = currencyThreshold;
		this.currencyType = currencyType;
		this.itemFilterTypes = itemFilterTypes;
	}

	@Override
	public String toString() {
		return "Subscription{" +
			"pk=" + pk +
			", name='" + name + '\'' +
			", tabIds=" + Arrays.toString(tabIds) +
			", currencyThreshold=" + currencyThreshold +
			", currencyType='" + currencyType + '\'' +
			", itemFilterTypes=" + itemFilterTypes +
			'}';
	}

	public Subscription copy() {
		return new Subscription(
			this.pk,
			this.name,
			this.tabIds,
			this.currencyThreshold,
			this.currencyType,
			this.itemFilterTypes
		);
	}
}
