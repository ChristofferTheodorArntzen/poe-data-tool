package com.carnnjoh.poedatatool.db.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Subscription {

	private Integer pk;
	private String[] tabIds;
	private double threshold;
	private String thresholdCurrencyType;

	public Subscription() {
		this(null, null, 0.0, null);
	}

	public Subscription(String[] tabIds, String thresholdCurrencyType) {
		this(null, tabIds, 0.0, thresholdCurrencyType);
	}

	public Subscription(String[] tabIds, Double threshold, String thresholdCurrencyType) {
		this.tabIds = tabIds;
		this.threshold = threshold;
		this.thresholdCurrencyType = thresholdCurrencyType;
	}

	public Subscription(Integer pk, String[] tabIds, Double threshold, String thresholdCurrencyType) {
		this.pk = pk;
		this.tabIds = tabIds;
		this.threshold = threshold;
		this.thresholdCurrencyType = thresholdCurrencyType;
	}
}
