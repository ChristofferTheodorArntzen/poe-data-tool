package com.carnnjoh.poedatatool.db.inMemory.implementation;

import com.carnnjoh.poedatatool.db.inMemory.dao.StatsDao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatsDaoImpl implements StatsDao {

	private Map<String, String> pseudoModMap = new HashMap<>();
	private Map<String, String> explicitModMap = new HashMap<>();
	private Map<String, String> implicitModMap = new HashMap<>();
	private Map<String, String> fracturedModMap = new HashMap<>();
	private Map<String, String> enchantModMap = new HashMap<>();
	private Map<String, String> craftedModMap = new HashMap<>();
	private Map<String, String> veiledModMap = new HashMap<>();
	private Map<String, String> monsterModMap = new HashMap<>();
	private Map<String, String> delveModMap = new HashMap<>();

	public Map<String, String> getPseudoModMap() {
		return pseudoModMap;
	}

	public void setPseudoModMap(Map<String, String> pseudoModMap) {
		this.pseudoModMap = pseudoModMap;
	}

	public Map<String, String> getExplicitModMap() {
		return explicitModMap;
	}

	public void setExplicitModMap(Map<String, String> explicitModMap) {
		this.explicitModMap = explicitModMap;
	}

	public Map<String, String> getImplicitModMap() {
		return implicitModMap;
	}

	public void setImplicitModMap(Map<String, String> implicitModMap) {
		this.implicitModMap = implicitModMap;
	}

	public Map<String, String> getFracturedModMap() {
		return fracturedModMap;
	}

	public void setFracturedModMap(Map<String, String> fracturedModMap) {
		this.fracturedModMap = fracturedModMap;
	}

	public Map<String, String> getEnchantModMap() {
		return enchantModMap;
	}

	public void setEnchantModMap(Map<String, String> enchantModMap) {
		this.enchantModMap = enchantModMap;
	}

	public Map<String, String> getCraftedModMap() {
		return craftedModMap;
	}

	public void setCraftedModMap(Map<String, String> craftedModMap) {
		this.craftedModMap = craftedModMap;
	}

	public Map<String, String> getVeiledModMap() {
		return veiledModMap;
	}

	public void setVeiledModMap(Map<String, String> veiledModMap) {
		this.veiledModMap = veiledModMap;
	}

	public Map<String, String> getMonsterModMap() {
		return monsterModMap;
	}

	public void setMonsterModMap(Map<String, String> monsterModMap) {
		this.monsterModMap = monsterModMap;
	}

	public Map<String, String> getDelveModMap() {
		return delveModMap;
	}

	public void setDelveModMap(Map<String, String> delveModMap) {
		this.delveModMap = delveModMap;
	}

	@Override
	public String lookUpIdByModText(String modText) {
		return lookUpModInAllMaps(modText);
	}

	private String lookUpModInAllMaps(String modText) {

		List<Map> listOfMaps = Arrays.asList(
			explicitModMap,
			implicitModMap,
			fracturedModMap,
			enchantModMap,
			craftedModMap,
			veiledModMap,
			monsterModMap,
			delveModMap
		);

		for (Map map : listOfMaps) {
			String modId = getModId(map, modText);
			if(modId != null) {
				return modId;
			}
		}

		return null;
	}

	private String getModId(Map<String, String> map, String id) {
		return map.get(id);
	}

}
