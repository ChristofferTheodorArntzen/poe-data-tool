package com.carnnjoh.poedatatool.db.inMemory.dao;

import java.util.Map;

public interface StatsDao {

	Map<String, String> getPseudoModMap();
	Map<String, String> getExplicitModMap();
	Map<String, String> getImplicitModMap();
	Map<String, String> getFracturedModMap();
	Map<String, String> getEnchantModMap();
	Map<String, String> getCraftedModMap();
	Map<String, String> getVeiledModMap();
	Map<String, String> getMonsterModMap();
	Map<String, String> getDelveModMap();
	void setPseudoModMap(Map<String, String> pseudoModMap);
	void setExplicitModMap(Map<String, String> explicitModMap);
	void setImplicitModMap(Map<String, String> implicitModMap);
	void setFracturedModMap(Map<String, String> fracturedModMap);
	void setEnchantModMap(Map<String, String> enchantModMap);
	void setCraftedModMap(Map<String, String> craftedModMap);
	void setVeiledModMap(Map<String, String> veiledModMap);
	void setMonsterModMap(Map<String, String> monsterModMap);
	void setDelveModMap(Map<String, String> delveModMap);

	String lookUpIdByModText(String modText);
}
