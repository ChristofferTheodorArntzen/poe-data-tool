package com.carnnjoh.poedatatool.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Array;
import java.util.List;

public class ItemModel {

	@JsonProperty("abyssJewel")
	boolean isAbyssalJewel;

	@JsonProperty("additionalProperties")
	List<PropertyModel> additionalProperties;

	@JsonProperty("artFileName")
	boolean artFilename;

	@JsonProperty("category")
	List<CategoryModel> categories;

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
	List<FrameTypeModel> frameTypes;

	@JsonProperty("h")
	Integer height;

	@JsonProperty("icon")
	String icon;

	@JsonProperty("id")
	String itemId;

	@JsonProperty("identified")
	boolean isIdentified;

	@JsonProperty("ilvl")
	Integer itemLevel;

	@JsonProperty("implicitMods")
	List<String> implicitMods;

	@JsonProperty("inventoryId")
	boolean inventoryId;

	@JsonProperty("isRelic")
	boolean isRelic;

	@JsonProperty("league")
	String league;

	@JsonProperty("lockedToCharacter")
	boolean isLockedToCharacter;

	@JsonProperty("maxStackSize")
	String maxStackSize;

	@JsonProperty("name")
	String name;

	//@JsonProperty("nextLevelRequirements") TODO: maybe add

	@JsonProperty("note")
	String note;

	@JsonProperty("properties")
	List<PropertyModel> properties;

	@JsonProperty("prophecyDiffText")
	String prophecyDiffText;

	@JsonProperty("prophecyText")
	String prophecyText;

	@JsonProperty("requirements")
	List<RequirementModel> requirements;

	@JsonProperty("secDescrText")
	String secDescText;

	@JsonProperty("shaper")
	boolean isShaper;

	@JsonProperty("sockets")
	List<SocketModel> sockets;

	@JsonProperty("stackSize")
	Integer StackSize;

	@JsonProperty("support")
	boolean isSupport;

	@JsonProperty("talismanTier")
	Integer talismanTier;

	@JsonProperty("typeLine")
	String typeLine;

	@JsonProperty("utilityMods")
	List<String> utilityMods;

	@JsonProperty("verified")
	boolean isVerified;

	@JsonProperty("w")
	Integer width;

	@JsonProperty("x")
	Integer xPos;

	@JsonProperty("y")
	Integer yPos;

	@JsonProperty("'^[\\\\s]RaceReward$'")
	boolean isRaceReward;




}
