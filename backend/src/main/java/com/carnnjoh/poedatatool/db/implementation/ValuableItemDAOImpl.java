package com.carnnjoh.poedatatool.db.implementation;

import com.carnnjoh.poedatatool.db.dao.ValuableItemDAO;
import com.carnnjoh.poedatatool.db.model.ValuableItem;
import com.carnnjoh.poedatatool.db.utils.*;
import com.carnnjoh.poedatatool.factories.GeneratedKeyHolderFactory;
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

	private ObjectMapper mapper = new ObjectMapper();
	private NamedParameterJdbcTemplate template;
	private GeneratedKeyHolderFactory keyHolderFactory;

	public ValuableItemDAOImpl() {
	}

	public ValuableItemDAOImpl(NamedParameterJdbcTemplate template, GeneratedKeyHolderFactory keyHolderFactory) {
		this.template = template;
		this.keyHolderFactory = keyHolderFactory;
	}

	@Override
	public Result deleteByPk(int pk) {
		return Utils.tryUpdate(() -> {
			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("pk", pk);
			template.update("DELETE FROM ValuableItem WHERE pk = :pk", params);
			return new DeleteSuccessResult();
		});
	}

	@Override
	public Result deleteById(String id) {
		return Utils.tryUpdate(() -> {
			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("id", id);
			template.update("DELETE FROM ValuableItem WHERE id = :id", params);
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
				.addValue("mean", item.getMean())
				.addValue("median", item.getMedian())
				.addValue("max", item.getMax())
				.addValue("min", item.getMin())
				.addValue("createdDate", Timestamp.valueOf(item.getCreatedDate()));

			KeyHolder keyHolder = keyHolderFactory.newKeyHolder();

			String sqlQuery = "INSERT INTO ValuableItem(id, subscriptionFk, item, mean, median, max, min, createdDate) " +
					"VALUES ( :id, :subscriptionFk, :item, :mean, :median, :max, :min, :createdDate)";

			template.update(sqlQuery, params, keyHolder);

			return (keyHolder.getKey() != null)
					? new CreateSuccessResult(keyHolder.getKey().intValue())
					: new FailedResult();
		});
	}

	@Override
	public ValuableItem fetch(int pk) {
		return Utils.tryGet(() -> {
			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("pk", pk);
			return template.queryForObject("SELECT * FROM ValuableItem WHERE pk = :pk", params, rowMapper);
		});
	}

	@Override
	public List<ValuableItem> fetchAll() {
		return Utils.tryGet(() -> template.query("SELECT * FROM ValuableItem", rowMapper));
	}

	public List<ValuableItem> getAllByDate(boolean ascending) {
		return Utils.tryGet( () -> {
			String sqlQuery = "SELECT * FROM ValuableItem ORDER BY createdDate";
			sqlQuery = (ascending) ? sqlQuery + " asc" : sqlQuery + " desc";

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
				.addValue("mean", item.getMean())
				.addValue("median", item.getMedian())
				.addValue("max", item.getMax())
				.addValue("min", item.getMin())
				.addValue("createdDate", Timestamp.valueOf(item.getCreatedDate()));

			String updateStatement =
				"UPDATE ValuableItem SET id = :id," +
					"subscriptionFk = :subscriptionFk," +
					"item = :item," +
					"mean = :mean, " +
					"median = :median, " +
					"max = :max, " +
					"min = :min, " +
					"createdDate = :createdDate" +
					" WHERE pk = :pk";
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
			rs.getInt("mean"),
			rs.getInt("median"),
			rs.getInt("max"),
			rs.getInt("min"),
			rs.getTimestamp("createdDate").toLocalDateTime()
		)));
}
