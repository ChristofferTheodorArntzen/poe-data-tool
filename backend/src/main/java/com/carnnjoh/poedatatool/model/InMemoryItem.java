package com.carnnjoh.poedatatool.model;

public class InMemoryItem extends Item {

	private boolean search;
	private ItemType itemType;

	public InMemoryItem(Item item, boolean search) {
		super(item);
		this.search = search;
	}

	public boolean isSearch() {
		return search;
	}

	public void setSearch(boolean search) {
		this.search = search;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

	@Override
	public String toString() {
		return "InMemoryItem{" +
				"item=" + super.toString() +
				", search=" + search +
				", itemTypes=" + itemType +
				'}';
	}
}
