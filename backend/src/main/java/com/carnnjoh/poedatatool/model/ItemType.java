package com.carnnjoh.poedatatool.model;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public enum ItemType {

	//CORRUPTED("Corrupted", item -> item.isCorrupted, true),
	// static items whose mods isn't important (atleast not for now)
	SCARAB("Scarab", item -> item.typeLine.contains("Scarab"), false),
	PROPHECY("Prophecy", item -> item.prophecyText != null && !item.prophecyText.isEmpty(), false),
	FRAGMENT("Fragment", Item::isFragment, false),
	OIL("Oil", item -> item.typeLine.endsWith("Oil") && item.isCurrency(), false),
	CURRENCY("Currency", Item::isCurrency, false),
	UNIQUE("Unique", item -> !item.name.isEmpty() && item.frameType == 3, false),
	CARD("Card", item -> item.artFilename != null && !item.artFilename.isEmpty(), false),
	BLIGHTMAP("Blight map", item -> item.typeLine.contains("Blighted") && item.typeLine.endsWith("Map"), false),
	MAP("Map", item -> item.typeLine.endsWith("Map"), false),
	DELIRIUMORB("Delirium Orb", item -> item.typeLine.endsWith("Delirium Orb") && item.isCurrency(), false),
	CATALYST("Catalyst", item -> item.typeLine.endsWith("Catalyst") && item.isCurrency(), false),
	FOSSIL("Fossil", item -> item.typeLine.endsWith("Fossil") && item.isCurrency(), false),
	ESSENCE("Essence", item -> item.typeLine.contains("Essence") && item.isCurrency(), false),
	RESONATOR("Resonators", item -> item.typeLine.contains("Resonator") && item.isCurrency(), false),

	// Item types whose mods can make the item vary in price heavily
	ABYSSALJEWEL("Abyssal Jewels", item -> item.isAbyssalJewel, true),
	CLUSTERJEWEL("Cluster jewel", item -> item.typeLine.endsWith("Cluster Jewel"), true),
	GEM("Gem", false),
	UNIQUE_ARMOUR("Unique Armour", true),
	UNIQUE_WEAPON("Unique Weapon", true),
	UNIQUE_ACCESSORY("Unique accessory", true),
	UNIQUE_FLASK("Unique flasks", true),
	UNIQUE_JEWEL("Unique jewel", true),
	UNIQUE_MAP("Unique map", true),
	RARE_WEAPON("Rare weapon", true),
	RARE_ARMOUR("Rare weapon", true),
	RARE_ACCESSORIES("Rare accessories", true),
	;

	public String text;
	public Predicate<Item> filter;
	public boolean convertMods;

	ItemType(String text) {
		this(text, null, false);
	}

	ItemType(String text, boolean convertMods) {
		this(text, null, convertMods);
	}

	ItemType(String text, Predicate<Item> filter, boolean convertMods) {
		this.text = text;
		this.filter = filter;
		this.convertMods = convertMods;
	}

}
