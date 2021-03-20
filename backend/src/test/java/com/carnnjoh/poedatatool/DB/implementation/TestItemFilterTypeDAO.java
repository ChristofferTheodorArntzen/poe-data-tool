package com.carnnjoh.poedatatool.DB.implementation;

import com.carnnjoh.poedatatool.db.dao.ItemFilterTypeDAO;
import com.carnnjoh.poedatatool.db.model.ItemFilterType;
import com.carnnjoh.poedatatool.db.utils.CreateSuccessResult;
import com.carnnjoh.poedatatool.db.utils.DeleteSuccessResult;
import com.carnnjoh.poedatatool.db.utils.Result;
import com.carnnjoh.poedatatool.db.utils.UpdateSuccessResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestItemFilterTypeDAO {

	@Autowired
	ItemFilterTypeDAO itemFilterTypeDAO;

	ItemFilterType testItemFilterType;

	@Before
	public void setup() {
		 testItemFilterType = new ItemFilterType("name1");
	}

	@Test
	public void testSave() {
		Result saveResult = itemFilterTypeDAO.save(testItemFilterType);
		assertTrue(saveResult instanceof CreateSuccessResult);
		ItemFilterType fetchedItemFilterType = itemFilterTypeDAO.fetch(((CreateSuccessResult) saveResult).getPk());
		assertEquals(fetchedItemFilterType.getName(), testItemFilterType.getName());
	}

	@Test
	public void testDelete() {
		ItemFilterType initialItemFilterType = saveInitialData();

		ItemFilterType fetchedItemFilterType = itemFilterTypeDAO.fetch(initialItemFilterType.getPk());

		Result deleteResult = itemFilterTypeDAO.deleteByPk(fetchedItemFilterType.getPk());

		assertTrue(deleteResult instanceof DeleteSuccessResult);



	}

	@Test
	public void testUpdate() {
		ItemFilterType initialItemFilterType = saveInitialData();
		initialItemFilterType.setName("test");

		Result updateResult = itemFilterTypeDAO.update(initialItemFilterType);
		assertTrue(updateResult instanceof UpdateSuccessResult);

		ItemFilterType fetchedItemFilterType = itemFilterTypeDAO.fetch(initialItemFilterType.getPk());
		assertNotEquals(testItemFilterType.getName(),fetchedItemFilterType.getName());
	}

	@Test
	public void testFetch() {
		ItemFilterType itemFilterType = itemFilterTypeDAO.fetch(1);
		assertNotNull(itemFilterType);
	}

	@Test
	public void testFetchAll() {
		List<ItemFilterType> itemFilterTypes = itemFilterTypeDAO.fetchAll();
		assertFalse(itemFilterTypes.isEmpty());
	}

	private ItemFilterType saveInitialData() {
		Result saveResult = itemFilterTypeDAO.save(testItemFilterType);
		assertTrue(saveResult instanceof CreateSuccessResult);
		return itemFilterTypeDAO.fetch(((CreateSuccessResult) saveResult).getPk());
	}

}
