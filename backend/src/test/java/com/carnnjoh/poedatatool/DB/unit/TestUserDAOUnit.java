package com.carnnjoh.poedatatool.DB.unit;


import com.carnnjoh.poedatatool.db.dao.DAO;
import com.carnnjoh.poedatatool.db.dao.UserDAO;
import com.carnnjoh.poedatatool.db.implementation.UserDAOImpl;
import com.carnnjoh.poedatatool.db.model.User;
import com.carnnjoh.poedatatool.db.utils.*;
import com.carnnjoh.poedatatool.factories.GeneratedKeyHolderFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestUserDAOUnit {

    private NamedParameterJdbcTemplate template = Mockito.mock(NamedParameterJdbcTemplate.class);
    private final GeneratedKeyHolderFactory keyHolderFactory = Mockito.mock(GeneratedKeyHolderFactory.class);
    private User user, user1;
    private UserDAO userDAO;

    private final String sqlUpdateStatement =
            "update User set league = :league," +
                    " accountName = :accountName," +
                    " realm = :realm,  sessionId = :sessionId" +
                    " where pk = :pk";

    private final String sqlSaveStatement =
            "INSERT INTO User (league, accountName, realm, sessionId) " +
                    "VALUES( :league, :accountName, :realm, :sessionId)";

    @BeforeAll
    public void setup() {

        user = new User(1,
                "Ultimatum",
                "Testesen",
                "pc",
                "qwerteoråwpero"
        );

        user1 = new User(2,
                "Standard",
                "Testesen",
                "",
                "qwerteoråwpero"
        );

        userDAO = new UserDAOImpl();
        ReflectionTestUtils.setField(userDAO, "template", template);
        ReflectionTestUtils.setField(userDAO, "keyHolderFactory", keyHolderFactory);
    }

    @Test
    public void testFetchAll_Mock() {
        when(template.query("SELECT * FROM User", UserDAOImpl.rowMapper))
                .thenReturn(Arrays.asList(user, user1));

        List<User> userList = userDAO.fetchAll();

        assertThat(userDAO).returns(Arrays.asList(user, user1), DAO::fetchAll);
        assertThat(userList.size() > 0).isTrue();
        assertThat(user).isIn(userList);
        assertThat(user1).isIn(userList);
    }

    @Test
    public void testFetch_Mock() {
        int pk = 1;

        when(template.query(
                eq("SELECT * FROM User WHERE pk = :pk"),
                any(MapSqlParameterSource.class),
                any(RowMapper.class)
        ))
                .thenReturn(Collections.singletonList(user));

        User testUser = userDAO.fetch(pk);

        assertThat(userDAO).returns(user, dao -> dao.fetch(pk));
        assertThat(testUser).isNotNull();
    }

    @Test
    public void testDelete_Mock() {

        int pk = 1;

        when(template.update(
                eq("DELETE FROM User WHERE pk = :pk"),
                any(MapSqlParameterSource.class)))
                .thenReturn(1);

        Result result = userDAO.deleteByPk(pk);

        assertThat(result).isInstanceOf(DeleteSuccessResult.class);
    }

    @Test
    public void testUpdate_Success_Mock() {
        when(template.update(eq(sqlUpdateStatement), any(MapSqlParameterSource.class)))
                .thenReturn(1);

        Result result = userDAO.update(user);

        assertThat(result).isInstanceOf(UpdateSuccessResult.class);
    }

    @Test
    public void testUpdate_Failed_Mock() {
        when(template.update(eq(sqlUpdateStatement), any(MapSqlParameterSource.class)))
                .thenReturn(0);

        Result result = userDAO.update(user);

        assertThat(result).isInstanceOf(FailedResult.class);
    }

    @Test
    public void testSave_Success_Mock() {
        int userPK = 1;

        KeyHolder keyHolder = new GeneratedKeyHolder(Collections.singletonList(Map.of("userPK", userPK)));
        when(keyHolderFactory.newKeyHolder()).thenReturn(keyHolder);

        when(template.update(eq(sqlSaveStatement), any(MapSqlParameterSource.class), any(KeyHolder.class)))
                .thenReturn(1);

        Result result = userDAO.save(user);

        assertThat(result).isInstanceOf(CreateSuccessResult.class);
        assertThat(((CreateSuccessResult)result).getPk()).isEqualTo(userPK);
    }

    @Test
    public void testSave_Failed_Mock() {

        KeyHolder keyHolder = new GeneratedKeyHolder(Collections.singletonList(new HashMap<>()));
        when(keyHolderFactory.newKeyHolder()).thenReturn(keyHolder);

        when(template.update(eq(sqlSaveStatement), any(MapSqlParameterSource.class), any(KeyHolder.class)))
                .thenReturn(0);

        Result result = userDAO.save(user);

        assertThat(result).isInstanceOf(FailedResult.class);
    }

}
