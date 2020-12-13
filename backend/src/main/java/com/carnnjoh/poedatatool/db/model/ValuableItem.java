package com.carnnjoh.poedatatool.db.model;

import com.carnnjoh.poedatatool.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ValuableItem {

	private int pk;
	private String id;
	private String subscriptionId;
	private Item item;

	@Override
	public String toString() {
		return "ValuableItem{" +
			"pk=" + pk +
			", id='" + id + '\'' +
			", subscriptionId='" + subscriptionId + '\'' +
			", item=" + item +
			'}';
	}
}
