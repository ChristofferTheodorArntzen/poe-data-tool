package com.carnnjoh.poedatatool.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Item {

	@JsonProperty("abyssJewel")
	boolean isAbyssalJewel;

	@JsonProperty("additionalProperties")
	List<Properties> additionalProperties;

	@JsonProperty("artFileName")
	boolean artFilename;

	@JsonProperty("category")
	List<Category> categories;

	@JsonProperty("corrupted")
	boolean isCorrupted;

	@JsonProperty("cosmeticMods")
	List<String> cosmeticMods;

	@JsonProperty("craftedMods")
	List<String> craftedMods;

	@JsonProperty("descrText")
	String descText;

	@JsonProperty("duplicated")
	boolean isDuplicated;

	@JsonProperty("elder")
	boolean isElder;

	@JsonProperty("enchantMods")
	List<String> enchantedMods;

	@JsonProperty("explicitMods")
	List<String> explicitMods;

	@JsonProperty("flavourText")
	List<String> flavourText;

	@JsonProperty("frameType")
	int frameTypes;

	@JsonProperty("h")
	int height;

	@JsonProperty("icon")
	String icon;

	@JsonProperty("id")
	String itemId;

	@JsonProperty("identified")
	boolean isIdentified;

	@JsonProperty("ilvl")
	int itemLevel;

	@JsonProperty("implicitMods")
	List<String> implicitMods;

	@JsonProperty("inventoryId")
	String inventoryId;

	@JsonProperty("isRelic")
	boolean isRelic;

	@JsonProperty("league")
	String league;

	@JsonProperty("lockedToCharacter")
	boolean isLockedToCharacter;

	@JsonProperty("maxStackSize")
	Integer maxStackSize;

	@JsonProperty("name")
	String name;

	//@JsonProperty("nextLevelRequirements") TODO: maybe add

	@JsonProperty("note")
	String note;

	@JsonProperty("properties")
	List<Properties> properties;

	@JsonProperty("prophecyDiffText")
	String prophecyDiffText;

	@JsonProperty("prophecyText")
	String prophecyText;

	@JsonProperty("requirements")
	List<Requirements> requirements;

	@JsonProperty("secDescrText")
	String secDescText;

	@JsonProperty("shaper")
	boolean isShaper;

	@JsonProperty("sockets")
	List<Sockets> sockets;

	@JsonProperty("stackSize")
	Integer StackSize;

	@JsonProperty("support")
	boolean isSupport;

	@JsonProperty("talismanTier")
	int talismanTier;

	@JsonProperty("typeLine")
	String typeLine;

	@JsonProperty("utilityMods")
	List<String> utilityMods;

	@JsonProperty("verified")
	boolean isVerified;

	@JsonProperty("w")
	int width;

	@JsonProperty("x")
	int xPos;

	@JsonProperty("y")
	int yPos;

	@JsonProperty("'^[\\\\s]RaceReward$'")
	boolean isRaceReward;
}
