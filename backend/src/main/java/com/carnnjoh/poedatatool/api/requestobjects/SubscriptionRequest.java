package com.carnnjoh.poedatatool.api.requestobjects;

import com.carnnjoh.poedatatool.db.model.ItemFilterType;
import com.carnnjoh.poedatatool.db.model.Subscription;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class SubscriptionRequest extends Subscription {

	@JsonProperty("name")
	private String name;

	@JsonProperty("tabIds")
	private Integer[] tabIds;

	@JsonProperty("currencyThreshold")
	private double currencyThreshold;

	@JsonProperty("itemFilter")
	private List<ItemFilterType> itemFilter;

	@JsonProperty("currencyType")
	private String currencyType;

	public SubscriptionRequest() {
	}

	public SubscriptionRequest(String name,
							   Integer[] tabIds,
							   Double currencyThreshold,
							   List<ItemFilterType> itemFilter,
							   String currencyType
	) {
		this.name = name;
		this.tabIds = tabIds;
		this.currencyThreshold = currencyThreshold;
		this.itemFilter = itemFilter;
		this.currencyType = currencyType;
	}

	@Override
	public String toString() {
		return "SubscriptionRequest{" +
			"name='" + name + '\'' +
			", tabIds=" + Arrays.toString(tabIds) +
			", currencyThreshold=" + currencyThreshold +
			", itemFilter='" + itemFilter + '\'' +
			", currencyType='" + currencyType + '\'' +
			'}';
	}
}
