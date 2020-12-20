package com.carnnjoh.poedatatool.api.requestobjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionRequest {

	private String[] tabIds;
	private double threshold;
	private String thresholdCurrencyType;

}
