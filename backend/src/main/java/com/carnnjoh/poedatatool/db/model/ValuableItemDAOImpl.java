package com.carnnjoh.poedatatool.db.model;

import com.carnnjoh.poedatatool.model.Properties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValuableItemDAOImpl implements ValuableItemDAO {

	ObjectMapper mapper;
	JdbcTemplate template;

	public ValuableItemDAOImpl(JdbcTemplate template, ObjectMapper mapper) {
		this.template = template;
		this.mapper = mapper;
	}

	@Override
	public void delete(ValuableItem item) {
		try {
			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("id", item.id);
			template.update("remove * from Item where id = :id", params);
		} catch (DataAccessException dae){
			dae.printStackTrace();
		}
	}

	@Override
	public void save(ValuableItem item) {
		try {
			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("id", item.id)
				.addValue("subscriptionId", item.subscriptionId)
				.addValue("item", item.item);
			template.update("insert into Item(id, subscriptionId, item) values(:id, :subscriptionId, :item)", params);
		}catch (DataAccessException dae){
			dae.printStackTrace();
		}
	}

	@Override
	public ValuableItem fetch(String id) {
		try {
			MapSqlParameterSource params =  new MapSqlParameterSource().addValue("id", id);
			return template.queryForObject("select * from Item where id = :id", rowMapper, params);
		} catch (DataAccessException dae){
			dae.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ValuableItem> fetchAll() {
		return template.query("Select * from ValuableItem", rowMapper);
	}

	private final RowMapper<ValuableItem> rowMapper = ((rs, rowNum) ->
		new ValuableItem(
			rs.getString("id"),
			rs.getString("subscriptionId"),
			readObject(rs.getString("item"))
		)
	);

	private <T> T readObject(String json) {
		try {
			return mapper.readValue(json, new TypeReference<T>() {
			});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}
}


