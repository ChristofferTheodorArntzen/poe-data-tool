package com.carnnjoh.poedatatool.db.model;

import com.carnnjoh.poedatatool.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ValuableItem {

	public String id;
	public String subscriptionId;
	public Item item;

}
