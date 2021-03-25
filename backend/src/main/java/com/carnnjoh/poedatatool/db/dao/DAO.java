package com.carnnjoh.poedatatool.db.dao;

import com.carnnjoh.poedatatool.db.utils.Result;

import java.util.List;

public interface DAO<T> {

	Result deleteByPk(int pk);

	Result save(T object);

	T fetch(int pk);

	List<T> fetchAll();

	Result update(T Object);
}
