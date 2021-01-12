package com.carnnjoh.poedatatool.db.dao;

import com.carnnjoh.poedatatool.db.model.ValuableItem;

import java.util.List;

public interface ValuableItemDAO extends DAO<ValuableItem> {

	List<ValuableItem> getAllByDate(boolean isAscending);

}
