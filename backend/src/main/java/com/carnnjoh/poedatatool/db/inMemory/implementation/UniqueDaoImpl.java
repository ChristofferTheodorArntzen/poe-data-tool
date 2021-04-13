package com.carnnjoh.poedatatool.db.inMemory.implementation;

import com.carnnjoh.poedatatool.db.inMemory.dao.UniqueDao;
import com.carnnjoh.poedatatool.model.InMemoryItem;
import com.carnnjoh.poedatatool.model.ItemType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniqueDaoImpl implements UniqueDao {

	private Map<String, ItemType> accessoriesMap = new HashMap<>();
	private Map<String, ItemType> armourMap = new HashMap<>();
	private Map<String, String> cardsMap = new HashMap<>();
	private Map<String, String> currencyMap = new HashMap<>();
	private Map<String, ItemType> flasksMap = new HashMap<>();
	private Map<String, String> gemsMap = new HashMap<>();
	private Map<String, ItemType> jewelsMap = new HashMap<>();
	private Map<String, ItemType> mapsMap = new HashMap<>();
	private Map<String, ItemType> weaponsMap = new HashMap<>();
	private Map<String, String> leagueStonesMap = new HashMap<>();
	private Map<String, String> propheciesMap = new HashMap<>();
	private Map<String, String> ItemisedMonstersMap = new HashMap<>();

	public Map<String, ItemType> getAccessoriesMap() {
		return accessoriesMap;
	}

	public void setAccessoriesMap(Map<String, ItemType> accessoriesMap) {
		this.accessoriesMap = accessoriesMap;
	}

	public Map<String, ItemType> getArmourMap() {
		return armourMap;
	}

	public void setArmourMap(Map<String, ItemType> armourMap) {
		this.armourMap = armourMap;
	}

	public Map<String, String> getCardsMap() {
		return cardsMap;
	}

	public void setCardsMap(Map<String, String> cardsMap) {
		this.cardsMap = cardsMap;
	}

	public Map<String, String> getCurrencyMap() {
		return currencyMap;
	}

	public void setCurrencyMap(Map<String, String> currencyMap) {
		this.currencyMap = currencyMap;
	}

	public Map<String, ItemType> getFlasksMap() {
		return flasksMap;
	}

	public void setFlasksMap(Map<String, ItemType> flasksMap) {
		this.flasksMap = flasksMap;
	}

	public Map<String, String> getGemsMap() {
		return gemsMap;
	}

	public void setGemsMap(Map<String, String> gemsMap) {
		this.gemsMap = gemsMap;
	}

	public Map<String, ItemType> getJewelsMap() {
		return jewelsMap;
	}

	public void setJewelsMap(Map<String, ItemType> jewelsMap) {
		this.jewelsMap = jewelsMap;
	}

	public Map<String, ItemType> getMapsMap() {
		return mapsMap;
	}

	public void setMapsMap(Map<String, ItemType> mapsMap) {
		this.mapsMap = mapsMap;
	}

	public Map<String, ItemType> getWeaponsMap() {
		return weaponsMap;
	}

	public void setWeaponsMap(Map<String, ItemType> weaponsMap) {
		this.weaponsMap = weaponsMap;
	}

	public Map<String, String> getLeagueStonesMap() {
		return leagueStonesMap;
	}

	public void setLeagueStonesMap(Map<String, String> leagueStonesMap) {
		this.leagueStonesMap = leagueStonesMap;
	}

	public Map<String, String> getPropheciesMap() {
		return propheciesMap;
	}

	public void setPropheciesMap(Map<String, String> propheciesMap) {
		this.propheciesMap = propheciesMap;
	}

	public Map<String, String> getItemisedMonstersMap() {
		return ItemisedMonstersMap;
	}

	public void setItemisedMonstersMap(Map<String, String> itemisedMonstersMap) {
		ItemisedMonstersMap = itemisedMonstersMap;
	}

	public void applyItemTypeIfGem(InMemoryItem item) {

		String gemName = gemsMap.get(item.typeLine);

		if(gemName == null) {
			gemName = gemsMap.get(item.baseType);
		}

		if(gemName != null) {
			item.setItemType(ItemType.GEM);
		}

	}

	public void applyItemTypeIfUnique(InMemoryItem item) {

		List<Map<String, ItemType>> listOfUniqueItemMaps =
			Arrays.asList(weaponsMap, armourMap, accessoriesMap, flasksMap, jewelsMap, mapsMap);

		for(Map<String, ItemType> map : listOfUniqueItemMaps) {
			ItemType itemType = map.get(item.name);
			if(itemType != null) {
				item.setItemType(itemType);
			}
		}

		if (item.getItemType() == null) {
			System.out.println("Could not find the item or the item was not unique, name of item: " + item.name);
		}

	}

	public void applyItemTypeIfProphecy(InMemoryItem item) {

		String prophecyName = propheciesMap.get(item.name);

		if(prophecyName != null) {
			item.setItemType(ItemType.PROPHECY);
		}

	}


}
