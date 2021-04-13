package com.carnnjoh.poedatatool.db.inMemory.dao;

import com.carnnjoh.poedatatool.model.InMemoryItem;
import com.carnnjoh.poedatatool.model.ItemType;

import java.util.Map;

public interface UniqueDao {

	Map<String, ItemType> getAccessoriesMap();
	Map<String, ItemType> getArmourMap();
	Map<String, ItemType> getFlasksMap();
	Map<String, ItemType> getJewelsMap();
	Map<String, ItemType> getMapsMap();
	Map<String, ItemType> getWeaponsMap();
	Map<String, String> getCardsMap();
	Map<String, String> getCurrencyMap();
	Map<String, String> getGemsMap();
	Map<String, String> getLeagueStonesMap();
	Map<String, String> getPropheciesMap();
	Map<String, String> getItemisedMonstersMap();

	void setAccessoriesMap(Map<String, ItemType> accessoriesMap);
	void setArmourMap(Map<String, ItemType> armourMap);
	void setFlasksMap(Map<String, ItemType> flasksMap);
	void setJewelsMap(Map<String, ItemType> jewelsMap);
	void setMapsMap(Map<String, ItemType> mapsMap);
	void setWeaponsMap(Map<String, ItemType> weaponsMap);
	void setCardsMap(Map<String, String> cardsMap);
	void setCurrencyMap(Map<String, String> currencyMap);
	void setGemsMap(Map<String, String> gemsMap);
	void setLeagueStonesMap(Map<String, String> leagueStonesMap);
	void setPropheciesMap(Map<String, String> propheciesMap);
	void setItemisedMonstersMap(Map<String, String> itemisedMonstersMap);

	void applyItemTypeIfGem(InMemoryItem item);
	void applyItemTypeIfUnique(InMemoryItem item);
	void applyItemTypeIfProphecy(InMemoryItem item);
}
