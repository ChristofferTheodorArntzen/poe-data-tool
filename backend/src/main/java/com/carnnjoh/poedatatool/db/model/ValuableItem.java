package com.carnnjoh.poedatatool.db.model;

import com.carnnjoh.poedatatool.model.Item;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValuableItem {

	private Integer pk;
	private String id;
	private Integer subscriptionFk;
	private Item item;

	public ValuableItem(){
		this(null, null, null, null);
	}

	public ValuableItem(String id, Integer subscriptionFk, Item item) {
		this(null, id, subscriptionFk, item);
	}

	public ValuableItem(Integer pk, String id, Integer subscriptionFk, Item item) {
		this.id = id;
		this.subscriptionFk = subscriptionFk;
		this.item = item;
	}

	@Override
	public String toString() {
		return "ValuableItem{" +
			"pk=" + pk +
			", id='" + id + '\'' +
			", subscriptionFk='" + subscriptionFk + '\'' +
			", item=" + item +
			'}';
	}
}
