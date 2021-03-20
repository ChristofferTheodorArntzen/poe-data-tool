package com.carnnjoh.poedatatool.db.implementation;

import com.carnnjoh.poedatatool.db.dao.ValuableItemDAO;
import com.carnnjoh.poedatatool.db.model.ValuableItem;
import com.carnnjoh.poedatatool.db.utils.*;
import com.carnnjoh.poedatatool.model.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Timestamp;
import java.util.List;

public class ValuableItemDAOImpl implements ValuableItemDAO {

	private ObjectMapper mapper;
	private NamedParameterJdbcTemplate template;

	public ValuableItemDAOImpl(NamedParameterJdbcTemplate template, ObjectMapper mapper) {
		this.template = template;
		this.mapper = mapper;
	}

	@Override
	public Result deleteByPk(int pk) {
		return Utils.tryUpdate(() -> {
			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("pk", pk);
			template.update("delete from ValuableItem where pk = :pk", params);
			return new DeleteSuccessResult();
		});
	}

	@Override
	public Result deleteById(String id) {
		return Utils.tryUpdate(() -> {
			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("id", id);
			template.update("delete from ValuableItem where id = :id", params);
			return new SuccessResult();
		});
	}

	@Override
	public Result save(ValuableItem item) {
		return Utils.tryUpdate(() -> {
			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("id", item.getId())
				.addValue("subscriptionFk", item.getSubscriptionFk())
				.addValue("item", mapper.writeValueAsBytes(item.getItem()))
				.addValue("estimatedPrice", item.getEstimatedPrice())
				.addValue("createdDate", Timestamp.valueOf(item.getCreatedDate()));
			KeyHolder keyHolder = new GeneratedKeyHolder();
			template.update(
				"insert into ValuableItem(id, subscriptionFk, item, estimatedPrice, createdDate) values( :id, :subscriptionFk, :item, :estimatedPrice, :createdDate)",
				params, keyHolder);
			return Utils.getCreateResult(keyHolder);
		});
	}

	@Override
	public ValuableItem fetch(int pk) {
		return Utils.tryGet(() -> {
			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("pk", pk);
			return template.queryForObject("select * from ValuableItem where pk = :pk", params, rowMapper);
		});
	}

	@Override
	public List<ValuableItem> fetchAll() {
		return Utils.tryGet(() -> template.query("Select * from ValuableItem", rowMapper));
	}

	public List<ValuableItem> getAllByDate(boolean ascending){
		return Utils.tryGet( () -> {
			String sqlQuery = "Select * from ValuableItem order by createdDate";
			if(ascending)
				sqlQuery = sqlQuery + " asc";
			else
				sqlQuery = sqlQuery + " desc";

			return template.query(sqlQuery, rowMapper);
		});
	}

	@Override
	public Result update(ValuableItem item) {
		return Utils.tryUpdate(() -> {

			if (item.getPk() == null) {
				return new FailedResult();
			}

			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("pk", item.getPk())
				.addValue("id", item.getId())
				.addValue("subscriptionFk", item.getSubscriptionFk())
				.addValue("item", mapper.writeValueAsBytes(item.getItem()))
				.addValue("estimatedPrice", item.getEstimatedPrice())
				.addValue("createdDate", Timestamp.valueOf(item.getCreatedDate()));

			String updateStatement =
				"update ValuableItem set id = :id," +
					"subscriptionFk = :subscriptionFk," +
					"item = :item," +
					"estimatedPrice = :estimatedPrice, " +
					"createdDate = :createdDate" +
					" where pk = :pk";
			int rowUpdate = template.update(updateStatement, params);
			return (rowUpdate != 0)
				? new UpdateSuccessResult()
				: new FailedResult();
		});
	}

	private final RowMapper<ValuableItem> rowMapper = ((rs, rowNum) ->
		Utils.tryGet(() -> new ValuableItem(
			rs.getInt("pk"),
			rs.getString("id"),
			rs.getInt("subscriptionFk"),
			mapper.readValue(rs.getBytes("item"), Item.class),
			rs.getInt("estimatedPrice"),
			rs.getTimestamp("createdDate").toLocalDateTime()
		)));
}
