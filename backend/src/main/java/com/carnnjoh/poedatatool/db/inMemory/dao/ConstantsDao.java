package com.carnnjoh.poedatatool.db.inMemory.dao;

import java.util.Map;

public interface ConstantsDao {

	//TODO: make it so the set functions cannot be acceded or called more than one time...

	public Map<String, String> getCurrencyMap();
	public Map<String, String> getSplinterMap();
	public Map<String, String> getFragmentMap();
	public Map<String, String> getDeliriumOrbMap();
	public Map<String, String> getCatalystMap();
	public Map<String, String> getOilMap();
	public Map<String, String> getIncubatorMap();
	public Map<String, String> getScarabMap();
	public Map<String, String> getDelveResonatorMap();
	public Map<String, String> getDelveFossilMap();
	public Map<String, String> getEssenceMap();
	public Map<String, String> getCardMap();
	public void setCurrencyMap(Map<String, String> currencyMap);
	public void setSplinterMap(Map<String, String> splinterMap);
	public void setFragmentMap(Map<String, String> fragmentMap);
	public void setDeliriumOrbMap(Map<String, String> deliriumOrbMap);
	public void setCatalystMap(Map<String, String> catalystMap);
	public void setOilMap(Map<String, String> oilMap);
	public void setIncubatorMap(Map<String, String> incubatorMap);
	public void setScarabMap(Map<String, String> scarabMap);
	public void setDelveResonatorMap(Map<String, String> delveResonatorMap);
	public void setDelveFossilMap(Map<String, String> delveFossilMap);
	public void setEssenceMap(Map<String, String> essenceMap);
	public void setCardMap(Map<String, String> cardMap);

}
