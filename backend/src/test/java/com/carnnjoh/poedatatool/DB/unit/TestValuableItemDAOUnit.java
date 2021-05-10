package com.carnnjoh.poedatatool.DB.unit;

import com.carnnjoh.poedatatool.db.dao.DAO;
import com.carnnjoh.poedatatool.db.dao.ValuableItemDAO;
import com.carnnjoh.poedatatool.db.implementation.ValuableItemDAOImpl;
import com.carnnjoh.poedatatool.db.model.ValuableItem;
import com.carnnjoh.poedatatool.factories.GeneratedKeyHolderFactory;
import com.carnnjoh.poedatatool.model.Item;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestValuableItemDAOUnit {

    NamedParameterJdbcTemplate template = Mockito.mock(NamedParameterJdbcTemplate.class);
    GeneratedKeyHolderFactory keyHolderFactory = Mockito.mock(GeneratedKeyHolderFactory.class);
    ValuableItem valuableItem, valuableItem2;

    ValuableItemDAO valuableItemDAO;

    private final String deleteByPkSqlStatement = "DELETE FROM ValuableItem WHERE pk = :pk";
    private final String deleteByIdSqlStatement = "DELETE FROM ValuableItem WHERE id = :id";
    private final String saveSqlStatement = "INSERT INTO " +
            "ValuableItem(id, subscriptionFk, item, mean, median, max, min, createdDate) " +
            "VALUES ( :id, :subscriptionFk, :item, :mean, :median, :max, :min, :createdDate)";

    private final String fetchByPKSqlStatement = "SELECT * FROM ValuableItem WHERE pk = :pk";
    private final String fetchAllSqlStatement = "SELECT * FROM ValuableItem";
    private final String getAllByDateAscendingSqlStatement = "SELECT * FROM ValuableItem ORDER BY createdDate asc";
    private final String getAllByDateDescendingSqlStatement = "Select * FROM ValuableItem ORDER BY createdDate desc";

    @BeforeAll
    public void setup() {

        LocalDateTime createdDate = LocalDateTime.of(2020, 2, 2, 2, 2);

        valuableItem = new ValuableItem(
                1,
                "pwrkpwoeqwk3k2350ii3r0E",
                1,
                new Item(),
                2,
                3,
                4,
                5,
                createdDate
        );

        valuableItem2 = new ValuableItem(
                2,
                "qpwek043120offwepfokepfok",
                1,
                new Item(),
                2,
                3,
                4,
                5,
                createdDate
        );

        valuableItemDAO = new ValuableItemDAOImpl();
        ReflectionTestUtils.setField(valuableItemDAO, "template", template);
        ReflectionTestUtils.setField(valuableItemDAO, "keyHolderFactory", keyHolderFactory);
    }

    @Test
    public void testFetchAll_Mock() {
        when(template.query(eq(fetchAllSqlStatement), any(RowMapper.class)))
                .thenReturn(Arrays.asList(valuableItem, valuableItem2));

        assertThat(valuableItemDAO).returns(Arrays.asList(valuableItem, valuableItem2), DAO::fetchAll);
    }

    @Test
    public void testFetch_Mock() {

    }


}
