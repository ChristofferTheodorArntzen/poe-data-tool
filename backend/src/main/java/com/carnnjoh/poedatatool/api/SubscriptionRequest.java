package com.carnnjoh.poedatatool.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
public class SubscriptionRequest {

	@NotNull
	private String[] tabIds;

	@NotNull
	private double threshold;

	@NotNull
	private String thresholdCurrencyType;

}
