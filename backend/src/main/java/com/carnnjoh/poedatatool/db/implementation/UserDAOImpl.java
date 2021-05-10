package com.carnnjoh.poedatatool.db.implementation;

import com.carnnjoh.poedatatool.db.dao.UserDAO;
import com.carnnjoh.poedatatool.db.model.User;
import com.carnnjoh.poedatatool.db.utils.*;
import com.carnnjoh.poedatatool.factories.GeneratedKeyHolderFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.util.List;

public class UserDAOImpl implements UserDAO {

	private NamedParameterJdbcTemplate template;
	private GeneratedKeyHolderFactory keyHolderFactory;

	public UserDAOImpl() {
	}

	public UserDAOImpl(NamedParameterJdbcTemplate template, GeneratedKeyHolderFactory keyHolderFactory) {
		this.template = template;
		this.keyHolderFactory = keyHolderFactory;
	}

	@Override
	public Result deleteByPk(int pk) {
		return Utils.tryUpdate(() -> {

			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("pk", pk);

			template.update("DELETE FROM User WHERE pk = :pk", params);

			return new DeleteSuccessResult();
		});
	}

	@Override
	public Result save(User user) {
		return Utils.tryUpdate(() -> {

			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("league", user.getLeague())
				.addValue("accountName", user.getAccountName())
				.addValue("realm", user.getRealm())
				.addValue("sessionId", user.getSessionId());

			KeyHolder keyHolder = keyHolderFactory.newKeyHolder();

			template.update(
					"INSERT INTO User (league, accountName, realm, sessionId) " +
					"VALUES( :league, :accountName, :realm, :sessionId)",
					params,
					keyHolder
			);

			try {
				return new CreateSuccessResult(keyHolder.getKey().intValue());
			} catch (Exception e) {
				e.printStackTrace();
			}

			return new FailedResult();
		});
	}

	@Override
	public User fetch(int pk) {
		return Utils.tryGet(() -> {
			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("pk", pk);
			return template.query("SELECT * FROM User WHERE pk = :pk",
					params,
					rowMapper)
					.stream()
					.findFirst()
					.orElse(null);
		});
	}

	@Override
	public List<User> fetchAll() {
		return Utils.tryGet(() -> template.query("SELECT * FROM User", rowMapper));
	}

	@Override
	public User getLastCreatedUser() {
		return Utils.tryGet(() -> template.query("SELECT * FROM User ORDER BY pk asc",
				rowMapper)
				.stream()
				.findFirst()
				.orElse(null));
	}

	@Override
	public Result update(User user) {
		return Utils.tryUpdate(() -> {

			if(user.getPk() == null){
				return new FailedResult();
			}

			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("pk", user.getPk())
				.addValue("league", user.getLeague())
				.addValue("accountName", user.getAccountName())
				.addValue("realm", user.getRealm())
				.addValue("sessionId", user.getSessionId());

			String sqlStatement =
				"update User set league = :league," +
				" accountName = :accountName," +
				" realm = :realm,  sessionId = :sessionId" +
				" where pk = :pk";

			int rowUpdate = template.update(sqlStatement, params);
			return (rowUpdate != 0)
				? new UpdateSuccessResult()
				: new FailedResult();
		});
	}

	public static final RowMapper<User> rowMapper = ((rs, rowNum) ->
		Utils.tryGet(() -> new User(
			rs.getInt("pk"),
			rs.getString("league"),
			rs.getString("accountName"),
			rs.getString("realm"),
			rs.getString("sessionId")
		)));
}