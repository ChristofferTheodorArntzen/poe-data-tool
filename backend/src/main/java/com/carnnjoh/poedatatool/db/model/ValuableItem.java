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

	private Integer pk;
	private String id;
	private Integer subscriptionFk;
	private Item item;

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
