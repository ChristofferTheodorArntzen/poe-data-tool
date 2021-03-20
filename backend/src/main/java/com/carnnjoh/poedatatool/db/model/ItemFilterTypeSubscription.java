package com.carnnjoh.poedatatool.db.model;

public class ItemFilterTypeSubscription {

	private Integer pk;
	private Integer subscriptionFk;
	private Integer itemFilterTypeFk;

	public ItemFilterTypeSubscription(Integer pk, Integer subscriptionFk, Integer itemFilterTypeFk) {
		this.pk = pk;
		this.subscriptionFk = subscriptionFk;
		this.itemFilterTypeFk = itemFilterTypeFk;
	}

	public ItemFilterTypeSubscription(Integer subscriptionFk, Integer itemFilterTypeFk) {
		this.subscriptionFk = subscriptionFk;
		this.itemFilterTypeFk = itemFilterTypeFk;
	}

	public Integer getSubscriptionFk() {
		return subscriptionFk;
	}

	public void setSubscriptionFk(Integer subscriptionFk) {
		this.subscriptionFk = subscriptionFk;
	}

	public Integer getItemFilterTypeFk() {
		return itemFilterTypeFk;
	}

	public void setItemFilterTypeFk(Integer itemFilterTypeFk) {
		this.itemFilterTypeFk = itemFilterTypeFk;
	}
}
