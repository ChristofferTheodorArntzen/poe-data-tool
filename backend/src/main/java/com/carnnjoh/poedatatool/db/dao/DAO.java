package com.carnnjoh.poedatatool.db.dao;

import com.carnnjoh.poedatatool.db.utils.Result;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.ReflectionUtils;

import java.util.List;

public interface DAO<T> {

	Result deleteByPk(int pk);

	Result save(T object);

	T fetch(int pk);

	List<T> fetchAll();

	Result update(T Object);
}
