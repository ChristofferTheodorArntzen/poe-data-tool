package com.carnnjoh.poedatatool.db.implementation;

import com.carnnjoh.poedatatool.db.dao.UserDAO;
import com.carnnjoh.poedatatool.db.model.User;
import com.carnnjoh.poedatatool.db.utils.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

public class UserDAOImpl implements UserDAO {

	private NamedParameterJdbcTemplate template;

	public UserDAOImpl(NamedParameterJdbcTemplate template) {
		this.template = template;
	}

	@Override
	public Result deleteByPk(int pk) {
		return Utils.tryUpdate(() -> {

			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("pk", pk);

			template.update("delete from User where pk = :pk", params);

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

			KeyHolder keyHolder = new GeneratedKeyHolder();

			template.update("insert into User(league, accountName, realm, sessionId) values( :league, :accountName, :realm, :sessionId)", params, keyHolder);
			return Utils.getCreateResult(keyHolder);
		});
	}

	@Override
	public User fetch(int pk) {
		return Utils.tryGet(() -> {
			MapSqlParameterSource params = new MapSqlParameterSource()
				.addValue("pk", pk);
			return template.query("select * from User where pk = :pk", params, rowMapper).stream().findFirst().orElse(null);
		});
	}

	@Override
	public List<User> fetchAll() {
		return Utils.tryGet(() -> template.query("Select * from User", rowMapper));
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

	private final RowMapper<User> rowMapper = ((rs, rowNum) ->
		Utils.tryGet(() -> new User(
			rs.getInt("pk"),
			rs.getString("league"),
			rs.getString("accountName"),
			rs.getString("realm"),
			rs.getString("sessionId")
		)));
}