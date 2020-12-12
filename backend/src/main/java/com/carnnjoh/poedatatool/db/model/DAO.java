package com.carnnjoh.poedatatool.db.model;

import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public interface DAO<T> {

	void delete(T item);

	void save(T item);

	T fetch(String id);

	List<T> fetchAll();

}
