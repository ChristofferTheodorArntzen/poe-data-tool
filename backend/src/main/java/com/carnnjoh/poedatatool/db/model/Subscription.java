package com.carnnjoh.poedatatool.db.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Subscription {

	private Integer pk;
	private String[] tabIds;
	private double threshold;
	private String thresholdCurrencyType;

}
