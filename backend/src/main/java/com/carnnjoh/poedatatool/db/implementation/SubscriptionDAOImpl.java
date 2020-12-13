package com.carnnjoh.poedatatool.db.implementation;

import com.carnnjoh.poedatatool.db.dao.SubscriptionDAO;
import com.carnnjoh.poedatatool.db.utils.*;
import com.carnnjoh.poedatatool.db.model.Subscription;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

public class SubscriptionDAOImpl implements SubscriptionDAO {

	private NamedParameterJdbcTemplate template;

	public SubscriptionDAOImpl(NamedParameterJdbcTemplate template) {
		this.template = template;
	}

	@Override
	public Result deleteByPk(int pk) {
		return Utils.tryUpdate(() -> {
			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("pk", pk);
			template.update("delete from Subscription where id = :id", params);
			return new SuccessResult();
		});
	}

	@Override
	public Result save(Subscription subscription) {
		return Utils.tryUpdate(() -> {
			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("tabIds", subscription.getTabIds())
				.addValue("threshold", subscription.getThreshold());
			KeyHolder keyHolder = new GeneratedKeyHolder();
			template.update("Insert into Subscription(id, tabIds, threshold) values(:id, :tabId, :threshold)", params, keyHolder);
			return Utils.getCreateResult(keyHolder);
		});
	}

	@Override
	public Subscription fetch(int pk) {
		return Utils.tryGet(() -> {
			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("pk", pk);
			return template.queryForObject("Select * from Subscription where pk = :pk", params, rowMapper);
		});
	}

	@Override
	public List<Subscription> fetchAll() {
		return Utils.tryGet(() -> template.query("Select * from Subscription", rowMapper));
	}

	private final RowMapper<Subscription> rowMapper = ((rs, rowNum) ->
		Utils.tryGet(() -> new Subscription(
			rs.getInt("pk"),
			rs.getArray("tabIds"),
			rs.getDouble("threshold")
		)));

}