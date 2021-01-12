package com.carnnjoh.poedatatool.api.requestobjects;

import com.carnnjoh.poedatatool.model.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ValuableItemRequest {

	private String id;
	private Integer subscriptionFk;
	private Item item;
	private Integer estimatedPrice;
}
