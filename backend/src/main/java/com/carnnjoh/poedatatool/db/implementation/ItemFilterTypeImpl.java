package com.carnnjoh.poedatatool.db.implementation;

import com.carnnjoh.poedatatool.db.dao.ItemFilterTypeDAO;
import com.carnnjoh.poedatatool.db.model.ItemFilterType;
import com.carnnjoh.poedatatool.db.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.*;

public class ItemFilterTypeImpl implements ItemFilterTypeDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemFilterTypeImpl.class);

	private NamedParameterJdbcTemplate template;

	public ItemFilterTypeImpl(NamedParameterJdbcTemplate template) {
		this.template = template;
	}

	@Override
	public Result deleteByPk(int pk) {
		return Utils.tryUpdate(() -> {
			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("pk", pk);
			template.update("delete from ItemFilterType where pk = :pk", params);
			return new DeleteSuccessResult();
		});
	}

	// unused
	@Override
	public Result deleteById(String id) {
		return null;
	}

	@Override
	public Result save(ItemFilterType itemFilterType) {
		return Utils.tryUpdate(() -> {
			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("name", itemFilterType.getName());
			KeyHolder keyHolder = new GeneratedKeyHolder();
			template.update(
				"Insert into ItemFilterType ( name ) " +
					"values ( :name )",
				params,
				keyHolder);
			return Utils.getCreateResult(keyHolder);
		});
	}

	@Override
	public ItemFilterType fetch(int pk) {
		return Utils.tryGet(() -> {
			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("pk", pk);
			return template.query(
				"Select * from ItemFilterType where pk = :pk",
				params,
				rowMapper)
				.stream()
				.findFirst()
				.orElse(null);
		});
	}

	@Override
	public List<ItemFilterType> fetchAll() {
		return Utils.tryGet(() -> template.query("select * from ItemFilterType", rowMapper));
	}

	@Override
	public Result update(ItemFilterType itemFilterType) {
		return Utils.tryUpdate(() -> {

			if (itemFilterType.getPk() == null) {
				return new FailedResult();
			}

			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("name", itemFilterType.getName())
				.addValue("pk", itemFilterType.getPk());

			int rowUpdate = template.update(
				"update ItemFilterType " +
					"set name = :name " +
					"where pk = :pk",
				params);
			return (rowUpdate != 0) ? new UpdateSuccessResult() : new FailedResult();
		});
	}

	@Override
	public Result batchSaveWithStrings(List<String> itemFilterNames) {
		return Utils.tryUpdate(() -> {

			String sql = "Insert Into ItemFilterType ( name ) values ( :name )";

			List<MapSqlParameterSource> params = new ArrayList<>();

			for (String itemFilterName : itemFilterNames) {
				MapSqlParameterSource source = new MapSqlParameterSource()
					.addValue("name", itemFilterName);
				params.add(source);
			}

			template.batchUpdate(sql, params.toArray(new MapSqlParameterSource[0]));

			return null;
		});
	}

	public List<ItemFilterType> fetch(int[] itemFilterTypePks) {
		return Utils.tryGet(() -> {

			MapSqlParameterSource params = new MapSqlParameterSource("pks", itemFilterTypePks);

			return template.query("select * from ItemFilterType where pk in (:pks)",
				params,
				rowMapper
			);
		});
	}

	@Override
	public List<ItemFilterType> fetchByIds(List<Integer> itemFilterTypePks) {
		return Utils.tryGet(() -> {

			MapSqlParameterSource params = new MapSqlParameterSource("pks", itemFilterTypePks);

			return template.query("select * from ItemFilterType where pk in (:pks)",
				params,
				rowMapper
			);
		});
	}

	@Override
	public List<ItemFilterType> fetchByName(List<String> itemFilterTypeNames) {
		return Utils.tryGet(() -> {

			MapSqlParameterSource params = new MapSqlParameterSource("names", itemFilterTypeNames);

			return template.query("select * from ItemFilterType where name in (:names)",
				params,
				rowMapper
			);
		});
	}

	private final RowMapper<ItemFilterType> rowMapper = ((rs, rowNum) ->
		Utils.tryGet(() -> new ItemFilterType(
			rs.getInt("pk"),
			rs.getString("name")
		)));

}
