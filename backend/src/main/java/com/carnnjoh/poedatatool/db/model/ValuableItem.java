package com.carnnjoh.poedatatool.db.model;

import com.carnnjoh.poedatatool.model.Item;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ValuableItem {

	private Integer pk;
	private String id;
	private Integer subscriptionFk;
	private Item item;
	private Integer estimatedPrice;
	private LocalDateTime createdDate;


	public ValuableItem() {
		this(null, null, null, null, null, LocalDateTime.now());
	}

	public ValuableItem(String id, Integer subscriptionFk, Item item, Integer estimatedPrice, LocalDateTime createdDate) {
		this(null, id, subscriptionFk, item, estimatedPrice, createdDate);
	}

	public ValuableItem(Integer pk, String id, Integer subscriptionFk, Item item, Integer estimatedPrice, LocalDateTime createdDate) {
		this.pk = pk;
		this.id = id;
		this.subscriptionFk = subscriptionFk;
		this.item = item;
		this.estimatedPrice = estimatedPrice;
		this.createdDate = createdDate;
	}

	public ValuableItem copy() {
		return new ValuableItem(this.id, this.subscriptionFk, this.item, this.estimatedPrice, this.createdDate);
	}

	@Override
	public String toString() {
		return "ValuableItem{" +
			"pk=" + pk +
			", id='" + id + '\'' +
			", subscriptionFk='" + subscriptionFk + '\'' +
			", item=" + item +
			", estimatedPrice=" + estimatedPrice +
			'}';
	}
}
