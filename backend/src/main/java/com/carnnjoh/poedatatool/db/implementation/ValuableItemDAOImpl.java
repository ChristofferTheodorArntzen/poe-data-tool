package com.carnnjoh.poedatatool.db.implementation;

import com.carnnjoh.poedatatool.db.dao.ValuableItemDAO;
import com.carnnjoh.poedatatool.db.utils.*;
import com.carnnjoh.poedatatool.db.model.ValuableItem;

import com.carnnjoh.poedatatool.model.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

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
			return new SuccessResult();
		});
	}

	@Override
	public Result save(ValuableItem item) {
		return Utils.tryUpdate(() -> {
			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("id", item.getId())
				.addValue("subscriptionFk", item.getSubscriptionFk())
				.addValue("item",  mapper.writeValueAsBytes(item.getItem()));
			KeyHolder keyHolder = new GeneratedKeyHolder();
			template.update("insert into ValuableItem(id, subscriptionFk, item) values( :id, :subscriptionFk, :item)", params, keyHolder);
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

	@Override
	public Result update(ValuableItem item) {
		return Utils.tryUpdate(() -> {

			if(item.getPk() == null){
				return new FailedResult();
			}

			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("pk", item.getPk())
				.addValue("id", item.getId())
				.addValue("subscriptionId", item.getSubscriptionFk())
				.addValue("item",  mapper.writeValueAsBytes(item.getItem()));
			int rowUpdate = template.update("update ValuableItem set id = :id, set subscriptionId = :subscriptionId, set item = :item where pk = :pk", params);
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
			mapper.readValue(rs.getBytes("item"), Item.class)
		)));
}
