package com.carnnjoh.poedatatool.db.inMemory.implementation;

import com.carnnjoh.poedatatool.db.inMemory.dao.ConstantsDao;

import java.util.HashMap;
import java.util.Map;

public class ConstantsDaoImpl implements ConstantsDao {

	private Map<String, String> currencyMap = new HashMap<>();
	private Map<String, String> splinterMap = new HashMap<>();
	private Map<String, String> fragmentMap = new HashMap<>();
	private Map<String, String> deliriumOrbMap = new HashMap<>();
	private Map<String, String> catalystMap = new HashMap<>();
	private Map<String, String> oilMap = new HashMap<>();
	private Map<String, String> incubatorMap = new HashMap<>();
	private Map<String, String> scarabMap = new HashMap<>();
	private Map<String, String> delveResonatorMap = new HashMap<>();
	private Map<String, String> delveFossilMap = new HashMap<>();
	private Map<String, String> essenceMap = new HashMap<>();
	private Map<String, String> cardMap = new HashMap<>();

	public Map<String, String> getCurrencyMap() {
		return currencyMap;
	}

	public void setCurrencyMap(Map<String, String> currencyMap) {
		this.currencyMap = currencyMap;
	}

	public Map<String, String> getSplinterMap() {
		return splinterMap;
	}

	public void setSplinterMap(Map<String, String> splinterMap) {
		this.splinterMap = splinterMap;
	}

	public Map<String, String> getFragmentMap() {
		return fragmentMap;
	}

	public void setFragmentMap(Map<String, String> fragmentMap) {
		this.fragmentMap = fragmentMap;
	}

	public Map<String, String> getDeliriumOrbMap() {
		return deliriumOrbMap;
	}

	public void setDeliriumOrbMap(Map<String, String> deliriumOrbMap) {
		this.deliriumOrbMap = deliriumOrbMap;
	}

	public Map<String, String> getCatalystMap() {
		return catalystMap;
	}

	public void setCatalystMap(Map<String, String> catalystMap) {
		this.catalystMap = catalystMap;
	}

	public Map<String, String> getOilMap() {
		return oilMap;
	}

	public void setOilMap(Map<String, String> oilMap) {
		this.oilMap = oilMap;
	}

	public Map<String, String> getIncubatorMap() {
		return incubatorMap;
	}

	public void setIncubatorMap(Map<String, String> incubatorMap) {
		this.incubatorMap = incubatorMap;
	}

	public Map<String, String> getScarabMap() {
		return scarabMap;
	}

	public void setScarabMap(Map<String, String> scarabMap) {
		this.scarabMap = scarabMap;
	}

	public Map<String, String> getDelveResonatorMap() {
		return delveResonatorMap;
	}

	public void setDelveResonatorMap(Map<String, String> delveResonatorMap) {
		this.delveResonatorMap = delveResonatorMap;
	}

	public Map<String, String> getDelveFossilMap() {
		return delveFossilMap;
	}

	public void setDelveFossilMap(Map<String, String> delveFossilMap) {
		this.delveFossilMap = delveFossilMap;
	}

	public Map<String, String> getEssenceMap() {
		return essenceMap;
	}

	public void setEssenceMap(Map<String, String> essenceMap) {
		this.essenceMap = essenceMap;
	}

	public Map<String, String> getCardMap() {
		return cardMap;
	}

	public void setCardMap(Map<String, String> cardMap) {
		this.cardMap = cardMap;
	}
}
