package com.carnnjoh.poedatatool.model;

import java.util.List;

public class InMemoryItem extends Item {

	private boolean search;
	private ItemType itemType;
	private boolean alreadySearched;
	private List<InterpretedMod> interpretedMods;

	public InMemoryItem(Item item) {
		super(item);
		this.search = false;
		this.alreadySearched = false;
	}

	public InMemoryItem(Item item, boolean search, boolean alreadySearched) {
		super(item);
		this.search = search;
		this.alreadySearched = alreadySearched;
	}

	public Item getItem(InMemoryItem inMemoryItem) {
		return new Item(inMemoryItem);
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

	public boolean isAlreadySearched() {
		return alreadySearched;
	}

	public void setAlreadySearched(boolean alreadySearched) {
		this.alreadySearched = alreadySearched;
	}

	public List<InterpretedMod> getInterpretedMods() {
		return interpretedMods;
	}

	public void setInterpretedMods(List<InterpretedMod> interpretedMods) {
		this.interpretedMods = interpretedMods;
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
