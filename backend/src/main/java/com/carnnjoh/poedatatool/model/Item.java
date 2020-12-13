package com.carnnjoh.poedatatool.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

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
	public int frameTypes;

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
}
