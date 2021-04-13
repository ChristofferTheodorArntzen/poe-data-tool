package com.carnnjoh.poedatatool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Item {

	@JsonProperty("abyssJewel")
	public boolean isAbyssalJewel;

	@JsonProperty("additionalProperties")
	public List<Properties> additionalProperties;

	@JsonProperty("artFileName")
	public boolean artFilename;

	@JsonProperty("category")
	public List<Category> categories;

	@JsonProperty("corrupted")
	public boolean isCorrupted;

	@JsonProperty("cosmeticMods")
	public List<String> cosmeticMods;

	@JsonProperty("craftedMods")
	public List<String> craftedMods;

	@JsonProperty("descrText")
	public String descText;

	@JsonProperty("duplicated")
	public boolean isDuplicated;

	@JsonProperty("elder")
	public boolean isElder;

	@JsonProperty("enchantMods")
	public List<String> enchantedMods;

	@JsonProperty("explicitMods")
	public List<String> explicitMods;

	@JsonProperty("flavourText")
	public List<String> flavourText;

	@JsonProperty("frameType")
	public int frameType;

	@JsonProperty("h")
	public int height;

	@JsonProperty("icon")
	public String icon;

	@JsonProperty("id")
	public String itemId;

	@JsonProperty("identified")
	public boolean isIdentified;

	@JsonProperty("ilvl")
	public int itemLevel;

	@JsonProperty("implicitMods")
	public List<String> implicitMods;

	@JsonProperty("inventoryId")
	public String inventoryId;

	@JsonProperty("isRelic")
	public boolean isRelic;

	@JsonProperty("league")
	public String league;

	@JsonProperty("lockedToCharacter")
	public boolean isLockedToCharacter;

	@JsonProperty("maxStackSize")
	public Integer maxStackSize;

	@JsonProperty("name")
	public String name;

	//@JsonProperty("nextLevelRequirements") TODO: maybe add

	@JsonProperty("note")
	public String note;

	@JsonProperty("properties")
	public List<Properties> properties;

	@JsonProperty("prophecyDiffText")
	public String prophecyDiffText;

	@JsonProperty("prophecyText")
	public String prophecyText;

	@JsonProperty("requirements")
	public List<Requirements> requirements;

	@JsonProperty("secDescrText")
	public String secDescText;

	@JsonProperty("shaper")
	public boolean isShaper;

	@JsonProperty("sockets")
	public List<Sockets> sockets;

	@JsonProperty("stackSize")
	public Integer StackSize;

	@JsonProperty("support")
	public boolean isSupport;

	@JsonProperty("talismanTier")
	public int talismanTier;

	@JsonProperty("typeLine")
	public String typeLine;

	@JsonProperty(required = false, defaultValue = "baseType")
	public String baseType;

	@JsonProperty("utilityMods")
	public List<String> utilityMods;

	@JsonProperty("verified")
	public boolean isVerified;

	@JsonProperty("w")
	public int width;

	@JsonProperty("x")
	public int xPos;

	@JsonProperty("y")
	public int yPos;

	@JsonProperty("'^[\\\\s]RaceReward$'")
	public boolean isRaceReward;

	public Item() {
	}

	public Item(Item item) {
		this.isAbyssalJewel = item.isAbyssalJewel;
		this.additionalProperties = item.additionalProperties;
		this.artFilename = item.artFilename;
		this.categories = item.categories;
		this.isCorrupted = item.isCorrupted;
		this.cosmeticMods = item.cosmeticMods;
		this.craftedMods = item.craftedMods;
		this.descText = item.descText;
		this.isDuplicated = item.isDuplicated;
		this.isElder = item.isElder;
		this.enchantedMods = item.enchantedMods;
		this.explicitMods = item.explicitMods;
		this.flavourText = item.flavourText;
		this.frameType = item.frameType;
		this.height = item.height;
		this.icon = item.icon;
		this.itemId = item.itemId;
		this.isIdentified = item.isIdentified;
		this.itemLevel = item.itemLevel;
		this.implicitMods = item.implicitMods;
		this.inventoryId = item.inventoryId;
		this.isRelic = item.isRelic;
		this.league = item.league;
		this.isLockedToCharacter = item.isLockedToCharacter;
		this.maxStackSize = item.maxStackSize;
		this.name = item.name;
		this.note = item.note;
		this.properties = item.properties;
		this.prophecyDiffText = item.prophecyDiffText;
		this.prophecyText = item.prophecyText;
		this.requirements = item.requirements;
		this.secDescText = item.secDescText;
		this.isShaper = item.isShaper;
		this.sockets = item.sockets;
		this.StackSize = item.StackSize;
		this.isSupport = item.isSupport;
		this.talismanTier = item.talismanTier;
		this.typeLine = item.typeLine;
		this.baseType = item.baseType;
		this.utilityMods = item.utilityMods;
		this.isVerified = item.isVerified;
		this.width = item.width;
		this.xPos = item.xPos;
		this.yPos = item.yPos;
		this.isRaceReward = item.isRaceReward;
	}

	@JsonIgnore
	public boolean isCurrency() {
		return frameType == 5 && maxStackSize != null;
	}

	@JsonIgnore
	public boolean isFragment() {
		return typeLine.endsWith("'s Key") ||
			typeLine.endsWith("Breachstone") ||
			typeLine.startsWith("Mortal")||
			typeLine.endsWith("to the Goddess") ||
			typeLine.equals("Simulacrum") ||
			typeLine.startsWith("Fragment of") ||
			typeLine.equals("The Maven's Writ") ||
			(typeLine.startsWith("Timeless") && typeLine.endsWith("Emblem")) ||
			typeLine.equals("Divine Vessel") ||
			typeLine.startsWith("Sacrifice at") ||
			typeLine.endsWith("Splinter");
	}

	@JsonIgnore
	@Override
	public String toString() {
		return "Item{" +
			"isAbyssalJewel=" + isAbyssalJewel +
			", additionalProperties=" + additionalProperties +
			", artFilename=" + artFilename +
			", categories=" + categories +
			", isCorrupted=" + isCorrupted +
			", cosmeticMods=" + cosmeticMods +
			", craftedMods=" + craftedMods +
			", descText='" + descText + '\'' +
			", isDuplicated=" + isDuplicated +
			", isElder=" + isElder +
			", enchantedMods=" + enchantedMods +
			", explicitMods=" + explicitMods +
			", flavourText=" + flavourText +
			", frameTypes=" + frameType +
			", height=" + height +
			", icon='" + icon + '\'' +
			", itemId='" + itemId + '\'' +
			", isIdentified=" + isIdentified +
			", itemLevel=" + itemLevel +
			", implicitMods=" + implicitMods +
			", inventoryId='" + inventoryId + '\'' +
			", isRelic=" + isRelic +
			", league='" + league + '\'' +
			", isLockedToCharacter=" + isLockedToCharacter +
			", maxStackSize=" + maxStackSize +
			", name='" + name + '\'' +
			", note='" + note + '\'' +
			", properties=" + properties +
			", prophecyDiffText='" + prophecyDiffText + '\'' +
			", prophecyText='" + prophecyText + '\'' +
			", requirements=" + requirements +
			", secDescText='" + secDescText + '\'' +
			", isShaper=" + isShaper +
			", sockets=" + sockets +
			", StackSize=" + StackSize +
			", isSupport=" + isSupport +
			", talismanTier=" + talismanTier +
			", typeLine='" + typeLine + '\'' +
			", baseType='" + baseType + '\'' +
			", utilityMods=" + utilityMods +
			", isVerified=" + isVerified +
			", width=" + width +
			", xPos=" + xPos +
			", yPos=" + yPos +
			", isRaceReward=" + isRaceReward +
			'}';
	}
}
