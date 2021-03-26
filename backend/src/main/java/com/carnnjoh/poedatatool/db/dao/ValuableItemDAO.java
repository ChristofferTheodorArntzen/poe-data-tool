package com.carnnjoh.poedatatool.db.dao;

import com.carnnjoh.poedatatool.db.model.ValuableItem;
import com.carnnjoh.poedatatool.db.utils.Result;

import java.util.List;

public interface ValuableItemDAO extends DAO<ValuableItem> {

	List<ValuableItem> getAllByDate(boolean isAscending);

	Result deleteById(String id);

}
