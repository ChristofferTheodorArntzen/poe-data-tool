package com.carnnjoh.poedatatool.DB.unit;

import com.carnnjoh.poedatatool.db.dao.DAO;
import com.carnnjoh.poedatatool.db.dao.SubscriptionDAO;
import com.carnnjoh.poedatatool.db.implementation.SubscriptionDAOImpl;
import com.carnnjoh.poedatatool.db.model.Subscription;
import com.carnnjoh.poedatatool.db.utils.*;
import com.carnnjoh.poedatatool.factories.GeneratedKeyHolderFactory;
import com.carnnjoh.poedatatool.model.ItemType;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestSubscriptionDAOUnit {

    NamedParameterJdbcTemplate template = Mockito.mock(NamedParameterJdbcTemplate.class);
    GeneratedKeyHolderFactory keyHolderFactory = Mockito.mock(GeneratedKeyHolderFactory.class);
    Subscription subscription, subscription1;
    SubscriptionDAO subscriptionDAO;

    private final String updateStatement = "UPDATE Subscription SET" +
            " name = :name," +
            " tabIds = :tabIds," +
            " currencyThreshold = :currencyThreshold," +
            " currencyType = :currencyType," +
            " itemTypes = :itemTypes," +
            " isActive = :isActive" +
            " WHERE pk = :pk";

    @BeforeAll
    public void setup() {

        subscription = new Subscription(
                1,
                "Test",
                new Integer[]{1, 2, 3},
                200.0,
                "chaos",
                Arrays.asList(ItemType.CARD, ItemType.GEM),
                false);

        subscription1 = new Subscription(
                1,
                "pqwoekpqwoekpqowek",
                new Integer[]{5, 6, 10},
                200.0,
                "exalted",
                Arrays.asList(ItemType.RARE_ACCESSORY, ItemType.RARE_ARMOUR),
                false);

        template = Mockito.mock(NamedParameterJdbcTemplate.class);

        subscriptionDAO = new SubscriptionDAOImpl();
        ReflectionTestUtils.setField(subscriptionDAO, "template", template);
        ReflectionTestUtils.setField(subscriptionDAO, "keyHolderFactory", keyHolderFactory);
    }

    @Test
    public void testFetchAll_Mock() {
        when(template.query("SELECT * FROM Subscription",
                SubscriptionDAOImpl.rowMapper))
                .thenReturn(Arrays.asList(subscription, subscription1));

        assertThat(subscriptionDAO)
                .returns(Arrays.asList(subscription, subscription1), DAO::fetchAll);
    }

    @Test
    public void testFetch_Mock() {
        int pk = 1;

        when(template.query(
                eq("SELECT * FROM Subscription WHERE pk = :pk"),
                any(MapSqlParameterSource.class),
                any(RowMapper.class)))
                .thenReturn(Collections.singletonList(subscription));

        assertThat(subscriptionDAO).returns(subscription, dao -> dao.fetch(pk));
    }

    @Test
    public void testDelete_Mock() {
        when(template.update(
                eq("DELETE FROM Subscription WHERE pk = :pk"),
                any(MapSqlParameterSource.class)))
                .thenReturn(1);

        Result result = subscriptionDAO.deleteByPk(subscription.getPk());

        assertThat(result).isInstanceOf(DeleteSuccessResult.class);
    }

    @Test
    public void testUpdate_Success_Mock() {
        when(template.update(
                anyString(), any(MapSqlParameterSource.class))
        ).thenReturn(1);

        Result result = subscriptionDAO.update(subscription);

        assertThat(result).isInstanceOf(UpdateSuccessResult.class);
    }

    @Test
    public void testUpdate_Failed_Mock() {
        when(template.update(
                anyString(), any(MapSqlParameterSource.class))
        ).thenReturn(0);

        Result result = subscriptionDAO.update(subscription);

        assertThat(result).isInstanceOf(FailedResult.class);
    }

    @Test
    public void testSave_Success_Mock() {

        int subscriptionPK = 1;

        KeyHolder keyHolder = new GeneratedKeyHolder(
                Collections.singletonList(
                        Map.of("subscriptionId", subscriptionPK)
                )
        );

        when(keyHolderFactory.newKeyHolder()).thenReturn(keyHolder);

        when(template.update(eq(updateStatement), any(MapSqlParameterSource.class), eq(keyHolder))
        ).thenReturn(1);

        Result saveResult = subscriptionDAO.save(subscription);

        assertThat(saveResult).isInstanceOf(CreateSuccessResult.class);
    }

    @Test
    public void testSave_Failed_Mock() {
        KeyHolder keyHolder = new GeneratedKeyHolder(Collections.singletonList(new HashMap<>()));
        when(keyHolderFactory.newKeyHolder()).thenReturn(keyHolder);

        when(template.update(eq(updateStatement), any(MapSqlParameterSource.class), eq(keyHolder)))
            .thenReturn(0);

        Result saveResult = subscriptionDAO.save(subscription);

        assertThat(saveResult).isInstanceOf(FailedResult.class);
    }

}
