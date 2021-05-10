package com.carnnjoh.poedatatool.db.implementation;

import com.carnnjoh.poedatatool.db.dao.SubscriptionDAO;
import com.carnnjoh.poedatatool.db.model.Subscription;
import com.carnnjoh.poedatatool.db.utils.*;
import com.carnnjoh.poedatatool.factories.GeneratedKeyHolderFactory;
import com.carnnjoh.poedatatool.model.ItemType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubscriptionDAOImpl implements SubscriptionDAO {

	private NamedParameterJdbcTemplate template;
	private GeneratedKeyHolderFactory keyHolderFactory;

	public SubscriptionDAOImpl() {
	}

	public SubscriptionDAOImpl(NamedParameterJdbcTemplate template, GeneratedKeyHolderFactory keyHolderFactory) {
		this.template = template;
		this.keyHolderFactory = keyHolderFactory;
	}

	@Override
	public Result deleteByPk(int pk) {
		return Utils.tryUpdate(() -> {
			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("pk", pk);
			template.update("DELETE FROM Subscription WHERE pk = :pk", params);
			return new DeleteSuccessResult();
		});
	}

	@Override
	public Result save(Subscription subscription) {
		return Utils.tryUpdate(() -> {

			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("name", subscription.getName())
				.addValue("tabIds", subscription.getTabIds())
				.addValue("currencyThreshold", subscription.getCurrencyThreshold())
				.addValue("currencyType", subscription.getCurrencyType())
				.addValue("itemTypes", subscription.getItemTypes().stream().map(Enum::name).toArray())
				.addValue("isActive", subscription.isActive());

			KeyHolder keyHolder = keyHolderFactory.newKeyHolder();

			String sqlQuery = "INSERT INTO Subscription " +
					"(name, tabIds, currencyThreshold, currencyType, itemTypes, isActive)" +
					" VALUES (:name, :tabIds, :currencyThreshold, :currencyType, :itemTypes, :isActive)";

			template.update(sqlQuery, params, keyHolder);

			return (keyHolder.getKey() != null)
					? new CreateSuccessResult(keyHolder.getKey().intValue())
					: new FailedResult();
		});
	}

	@Override
	public Subscription fetch(int pk) {
		return Utils.tryGet(() -> {
			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("pk", pk);
			return template.query(
				"SELECT * FROM Subscription WHERE pk = :pk",
				params,
				rowMapper)
				.stream()
				.findFirst()
				.orElse(null);
		});
	}

	@Override
	public List<Subscription> fetchAll() {
		return Utils.tryGet(() -> template.query("SELECT * FROM Subscription", rowMapper));
	}

	@Override
	public Result update(Subscription subscription) {
		return Utils.tryUpdate(() -> {

			if (subscription.getPk() == null) {
				return new FailedResult();
			}

			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("pk", subscription.getPk())
				.addValue("name", subscription.getName())
				.addValue("tabIds", subscription.getTabIds())
				.addValue("currencyThreshold", subscription.getCurrencyThreshold())
				.addValue("currencyType", subscription.getCurrencyType())
				.addValue("itemTypes", subscription.getItemTypes().stream().map(Enum::name).toArray())
				.addValue("isActive", subscription.isActive());

			String updateStatement =
					"UPDATE Subscription SET" +
					" name = :name," +
					" tabIds = :tabIds," +
					" currencyThreshold = :currencyThreshold," +
					" currencyType = :currencyType," +
					" itemTypes = :itemTypes," +
					" isActive = :isActive" +
					" WHERE pk = :pk";

			int rowUpdate = template.update(updateStatement, params);
			return (rowUpdate != 0)
				? new UpdateSuccessResult()
				: new FailedResult();
		});
	}

	@Override
	public Subscription fetchFirstActive(boolean isActive) {
		return Utils.tryGet(() -> {
			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("isActive", isActive);
			return template.query(
				"SELECT * FROM Subscription WHERE isActive = :isActive",
				params,
				rowMapper)
				.stream()
				.findFirst()
				.orElse(null);
		});
	}

	public static final RowMapper<Subscription> rowMapper = ((rs, rowNum) ->
		Utils.tryGet(() ->
			new Subscription(
				rs.getInt("pk"),
				rs.getString("name"),
				(Integer[]) rs.getArray("tabIds").getArray(),
				rs.getDouble("currencyThreshold"),
				rs.getString("currencyType"),
				convertEnumArrayToList(rs.getArray("itemTypes").getArray()),
				rs.getBoolean("isActive")
			)
		));
	
	private static List<ItemType> convertEnumArrayToList(Object object) {
		String[] enumStringArray = Arrays.stream((Object[]) object).toArray(String[]::new);
		List<ItemType> itemTypes = new ArrayList<>();
		for (String string : enumStringArray) {
			itemTypes.add(ItemType.valueOf(string));
		}
		return itemTypes;
	}
}