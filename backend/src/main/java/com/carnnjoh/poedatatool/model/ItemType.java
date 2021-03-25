package com.carnnjoh.poedatatool.model;

import java.util.function.Predicate;

public enum ItemType {

	CORRUPTED("Corrupted", item -> item.isCorrupted),
	ABYSSALJEWEL("Abyssal Jewels", item -> item.isAbyssalJewel),
	SCARAB("Scarab", item -> item.typeLine.contains("Scarab")),
	PROPHECY("Prophecy", item -> !item.prophecyText.isEmpty()),
	FRAGMENT("Fragment", Item::isFragment),
	OIL("Oil", item -> item.typeLine.endsWith("Oil") && item.isCurrency()),
	CURRENCY("Currency", Item::isCurrency),
	UNIQUE("Unique", item -> !item.name.isEmpty() && item.frameType == 3),
	CARD("Card", item -> item.artFilename),
	CLUSTERJEWEL("Cluster jewel", item -> item.typeLine.endsWith("Cluster Jewel")),
	BLIGHTMAP("Blight map", item -> item.typeLine.contains("Blighted") && item.typeLine.endsWith("Map")),
	MAP("Map", item -> item.typeLine.endsWith("Map")),
	DELIRIUMORB("Delirium Orb", item -> item.typeLine.endsWith("Delirium Orb") && item.isCurrency()),
	CATALYST("Catalyst", item -> item.typeLine.endsWith("Catalyst") && item.isCurrency()),
	FOSSIL("Fossil", item -> item.typeLine.endsWith("Fossil") && item.isCurrency()),
	ESSENCE("Essence", item -> item.typeLine.contains("Essence") && item.isCurrency()),
	RESONATOR("Resonators", item -> item.typeLine.contains("Resonator") && item.isCurrency());

	public String text;
	public Predicate<Item> filter;

	ItemType(String text, Predicate<Item> filter) {
		this.text = text;
		this.filter = filter;
	}

}
