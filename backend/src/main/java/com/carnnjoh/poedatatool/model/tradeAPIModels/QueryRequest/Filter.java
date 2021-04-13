package com.carnnjoh.poedatatool.model.tradeAPIModels.QueryRequest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Filter {

	@JsonProperty("disabled")
	boolean disabled = false;

	//--- typeFilter
	@JsonProperty("category") //TODO: find out what this is, can it be a enum?
	public Category category;

	@JsonProperty("rarity")
	public Rarity rarity;
	//--- typeFilter

	//--- weaponFilter
	@JsonProperty("damage")
	public Damage damage;

	@JsonProperty("crit")
	public Critical critical;

	@JsonProperty("pdps")
	public PhysicalDps physicalDps;

	@JsonProperty("aps")
	public AttackSpeed attackSpeed;

	@JsonProperty("dps")
	public Dps dps;

	@JsonProperty("edps")
	public ElementalDps elementalDps;
	//--- weaponFilter

	//--- armour_filters
	@JsonProperty("ar")
	public Armour armour;

	@JsonProperty("es")
	public EnergyShield energyShield;

	@JsonProperty("ev")
	public Evasion evasion;

	@JsonProperty("block")
	public Block block;
	//--- armour_filters

	//--- socket_filters
	@JsonProperty("sockets")
	public Sockets sockets;

	@JsonProperty("links")
	public Links links;
	//--- socket_filters

	//--- req_filters
	@JsonProperty("lvl")
	public Level level;

	@JsonProperty("dex")
	public Dexterity dexterity;

	@JsonProperty("str")
	public Strength strength;

	@JsonProperty("int")
	public Intelligence intelligence;
	//--- req_filters

	//--- misc_filters
	@JsonProperty("quality")
	public Quality quality;

	@JsonProperty("gem_level")
	public GemLevel gemLevel;

	@JsonProperty("ilvl")
	public ItemLevel itemLevel;

	@JsonProperty("gem_level_progress")
	public GemLevelProgress gemLevelProgress;

	@JsonProperty("gem_alternate_quality")
	public GemAlternateQuality gemAlternateQuality;

	@JsonProperty("shaper_item")
	public ShaperItem shaperItem;

	@JsonProperty("crusader_item")
	public CrusaderItem crusaderItem;

	@JsonProperty("hunter_item")
	public HunterItem hunterItem;

	@JsonProperty("fractured_item")
	public FracturedItem fracturedItem;

	@JsonProperty("alternate_art")
	public AlternateArt alternateArt;

	@JsonProperty("corrupted")
	public Corrupted corrupted;

	@JsonProperty("crafted")
	public Crafted crafted;

	@JsonProperty("enchanted")
	public Enchanted enchanted;

	@JsonProperty("elder_item")
	public ElderItem elderItem;

	@JsonProperty("redeemer_item")
	public RedeemerItem redeemerItem;

	@JsonProperty("warlord_item")
	public WarlordItem warlordItem;

	@JsonProperty("synthesised_item")
	public SynthesisedItem synthesisedItem;

	@JsonProperty("identified")
	public Identified identified;

	@JsonProperty("mirrored")
	public Mirrored mirrored;

	@JsonProperty("veiled")
	public Veiled veiled;

	@JsonProperty("talisman_tier")
	public TalismanTier talismanTier;

	@JsonProperty("stack_size")
	public StackSize stackSize;

	@JsonProperty("stored_experience")
	public StoredExperience storedExperience;
	//--- misc_filters

	//--- misc_filters
	@JsonProperty("id")
	public String modId;

	public Value value;
	//--- misc_filters

	@JsonProperty("weapon_filters")
	public WeaponFilter weaponFilter;

	@JsonProperty("armour_filters")
	public ArmourFilter armourFiler;

	@JsonProperty("socket_filters")
	public SocketFilter socketFilter;

	@JsonProperty("req_filters")
	public RequirementFilter req_filters;

	@JsonProperty("map_filters")
	public MapFilter map_filters;

	@JsonProperty("misc_filters")
	public MiscFilter misc_filters;

	@JsonProperty("type_filters")
	public TypeFilter typeFilter;

	public Filter() {
	}

	public Filter(String modId, Value value) {
		this.modId = modId;
		this.value = value;
	};

}
