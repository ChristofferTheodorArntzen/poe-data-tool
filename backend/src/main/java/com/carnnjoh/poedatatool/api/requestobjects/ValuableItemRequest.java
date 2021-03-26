package com.carnnjoh.poedatatool.api.requestobjects;

import com.carnnjoh.poedatatool.model.Item;

import java.time.LocalDateTime;

public class ValuableItemRequest {

	private String id;
	private Integer subscriptionFk;
	private Item item;
	private Integer estimatedPrice;
	private LocalDateTime createdDate;

	public String getId() {
		return id;
	}

	public Integer getSubscriptionFk() {
		return subscriptionFk;
	}

	public Item getItem() {
		return item;
	}

	public Integer getEstimatedPrice() {
		return estimatedPrice;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
}
