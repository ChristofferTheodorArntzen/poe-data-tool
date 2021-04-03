package com.carnnjoh.poedatatool.api.requestobjects;

import com.carnnjoh.poedatatool.model.Item;

import java.time.LocalDateTime;

public class ValuableItemRequest {

	private String id;
	private Integer subscriptionFk;
	private Item item;
	private int mean;
	private int median;
	private int max;
	private int min;
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

	public int getMean() {
		return mean;
	}

	public int getMedian() {
		return median;
	}

	public int getMax() {
		return max;
	}

	public int getMin() {
		return min;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
}
