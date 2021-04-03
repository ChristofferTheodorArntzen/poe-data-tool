package com.carnnjoh.poedatatool.db.model;

import com.carnnjoh.poedatatool.model.Item;

import java.time.LocalDateTime;

/**
 * Entity to save, update and delete Subscriptions
 */
public class ValuableItem {

	private Integer pk;
	private String id;
	private Integer subscriptionFk;
	private Item item;
	private int mean;
	private int median;
	private int max;
	private int min;
	private LocalDateTime createdDate;


	public ValuableItem() {
		this(null, null, null, 0, 0, 0, 0, LocalDateTime.now());
	}

	public ValuableItem(Integer pk, String id, Integer subscriptionFk, Item item, int mean, int median, int max, int min, LocalDateTime createdDate) {
		this.pk = pk;
		this.id = id;
		this.subscriptionFk = subscriptionFk;
		this.item = item;
		this.mean = mean;
		this.median = median;
		this.max = max;
		this.min = min;
		this.createdDate = createdDate;
	}

	public ValuableItem(String id, Integer subscriptionFk, Item item, int mean, int median, int max, int min, LocalDateTime createdDate) {
		this.id = id;
		this.subscriptionFk = subscriptionFk;
		this.item = item;
		this.mean = mean;
		this.median = median;
		this.max = max;
		this.min = min;
		this.createdDate = createdDate;
	}

	public Integer getPk() {
		return pk;
	}

	public void setPk(Integer pk) {
		this.pk = pk;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getSubscriptionFk() {
		return subscriptionFk;
	}

	public void setSubscriptionFk(Integer subscriptionFk) {
		this.subscriptionFk = subscriptionFk;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public int getMean() {
		return mean;
	}

	public void setMean(int mean) {
		this.mean = mean;
	}

	public int getMedian() {
		return median;
	}

	public void setMedian(int median) {
		this.median = median;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	@Override
	public String toString() {
		return "ValuableItem{" +
				"pk=" + pk +
				", id='" + id + '\'' +
				", subscriptionFk=" + subscriptionFk +
				", item=" + item +
				", mean=" + mean +
				", median=" + median +
				", max=" + max +
				", min=" + min +
				", createdDate=" + createdDate +
				'}';
	}
}
