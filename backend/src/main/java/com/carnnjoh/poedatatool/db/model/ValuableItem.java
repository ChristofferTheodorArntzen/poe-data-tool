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
	private Integer estimatedPrice;
	private LocalDateTime createdDate;


	public ValuableItem() {
		this(null, null, null, null, LocalDateTime.now());
	}

	public ValuableItem(String id, Integer subscriptionFk, Item item, Integer estimatedPrice, LocalDateTime createdDate) {
		this.id = id;
		this.subscriptionFk = subscriptionFk;
		this.item = item;
		this.estimatedPrice = estimatedPrice;
		this.createdDate = createdDate;
	}

	public ValuableItem(Integer pk, String id, Integer subscriptionFk, Item item, Integer estimatedPrice, LocalDateTime createdDate) {
		this.pk = pk;
		this.id = id;
		this.subscriptionFk = subscriptionFk;
		this.item = item;
		this.estimatedPrice = estimatedPrice;
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

	public Integer getEstimatedPrice() {
		return estimatedPrice;
	}

	public void setEstimatedPrice(Integer estimatedPrice) {
		this.estimatedPrice = estimatedPrice;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public ValuableItem copy() {
		return new ValuableItem(this.id, this.subscriptionFk, this.item, this.estimatedPrice, this.createdDate);
	}

	@Override
	public String toString() {
		return "ValuableItem{" +
			"pk=" + pk +
			", id='" + id + '\'' +
			", subscriptionFk='" + subscriptionFk + '\'' +
			", item=" + item +
			", estimatedPrice=" + estimatedPrice +
			'}';
	}
}
