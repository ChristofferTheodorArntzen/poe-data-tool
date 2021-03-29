package com.carnnjoh.poedatatool.model;

public class InMemoryItem {

	private Item item;

	private boolean search;

	public InMemoryItem(Item item, boolean search) {
		this.item = item;
		this.search = search;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public boolean isSearch() {
		return search;
	}

	public void setSearch(boolean search) {
		this.search = search;
	}
}
