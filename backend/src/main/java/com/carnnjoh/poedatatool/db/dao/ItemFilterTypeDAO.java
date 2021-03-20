package com.carnnjoh.poedatatool.db.dao;

import com.carnnjoh.poedatatool.db.model.ItemFilterType;
import com.carnnjoh.poedatatool.db.utils.Result;

import java.util.List;

public interface ItemFilterTypeDAO extends DAO<ItemFilterType> {

	Result batchSaveWithStrings(List<String> itemFilterNames);

	List<ItemFilterType> fetchByIds(List<Integer> itemFilterTypePks);

	List<ItemFilterType> fetchByName(List<String> itemFilterTypeNames);

}
