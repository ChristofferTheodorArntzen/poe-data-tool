package com.carnnjoh.poedatatool.DB.implementation;

import com.carnnjoh.poedatatool.db.dao.ValuableItemDAO;
import com.carnnjoh.poedatatool.db.model.ValuableItem;
import com.carnnjoh.poedatatool.db.utils.CreateSuccessResult;
import com.carnnjoh.poedatatool.db.utils.DeleteSuccessResult;
import com.carnnjoh.poedatatool.db.utils.Result;
import com.carnnjoh.poedatatool.db.utils.UpdateSuccessResult;
import com.carnnjoh.poedatatool.model.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestValuableItemDAO {

	@Autowired
	ValuableItemDAO valuableItemDAO;

	ValuableItem testValuableItem;

	@Before
	public void setup() {
		testValuableItem = new ValuableItem("123123", 1, new Item(), 20, LocalDateTime.now());
	}

	@Test
	public void testSaveValuableItem() {
		Result saveResult  = valuableItemDAO.save(testValuableItem);

		Assert.assertTrue(saveResult instanceof CreateSuccessResult);

		ValuableItem fetchedValuableItem = valuableItemDAO.fetch(((CreateSuccessResult) saveResult).getPk());

		Assert.assertEquals(((CreateSuccessResult) saveResult).getPk(), fetchedValuableItem.getPk());
		Assert.assertEquals(testValuableItem.getEstimatedPrice(), fetchedValuableItem.getEstimatedPrice());
		Assert.assertEquals(testValuableItem.getId(), fetchedValuableItem.getId());
		Assert.assertEquals(testValuableItem.getItem().toString(), fetchedValuableItem.getItem().toString());
		Assert.assertEquals(testValuableItem.getCreatedDate(), fetchedValuableItem.getCreatedDate());
	}

	@Test
	public void testDeleteValuableItem() {
		ValuableItem initialValuableItem = saveInitialValuableItem();

		ValuableItem fetchedValuableItem = valuableItemDAO.fetch(initialValuableItem.getPk());

		Result deleteResult = valuableItemDAO.deleteByPk(fetchedValuableItem.getPk());

		Assert.assertTrue(deleteResult instanceof DeleteSuccessResult);

		fetchedValuableItem = valuableItemDAO.fetch(fetchedValuableItem.getPk());

		Assert.assertNull(fetchedValuableItem);
	}

	@Test
	public void testUpdateValuableItem() {
		ValuableItem initialValuableItem = saveInitialValuableItem();
		initialValuableItem.setEstimatedPrice(1000);

		Result updateResult = valuableItemDAO.update(initialValuableItem);

		Assert.assertTrue(updateResult instanceof UpdateSuccessResult);

		ValuableItem fetchedValuableItem = valuableItemDAO.fetch(initialValuableItem.getPk());

		Assert.assertNotEquals(fetchedValuableItem.getEstimatedPrice(), testValuableItem.getEstimatedPrice());
		Assert.assertEquals(fetchedValuableItem.getCreatedDate(), testValuableItem.getCreatedDate());
		Assert.assertEquals(fetchedValuableItem.getItem().toString(), testValuableItem.getItem().toString());
		Assert.assertEquals(fetchedValuableItem.getId(), testValuableItem.getId());
		Assert.assertEquals(fetchedValuableItem.getSubscriptionFk(), testValuableItem.getSubscriptionFk());
	}

	@Test
	public void testFetchAll() {

		generateValuableItems();

		List<ValuableItem> valuableItems = valuableItemDAO.fetchAll();

		Assert.assertFalse(valuableItems.isEmpty());
	}

	private void generateValuableItems() {
		generateValuableItems(100);
	}

	private void generateValuableItems(int numberOfValuableItems) {

		List<ValuableItem> valuableItems = new ArrayList<>();

		for (int i = 0; i < numberOfValuableItems; i++) {
			valuableItems.add(new ValuableItem("qwe" +  i + "qwe", 1, new Item(), i, LocalDateTime.now()));
		}

		for(ValuableItem valuableItem : valuableItems) {
			valuableItemDAO.save(valuableItem);
		}
	}

	private ValuableItem saveInitialValuableItem() {
		Result saveResult = valuableItemDAO.save(testValuableItem);
		Assert.assertTrue(saveResult instanceof CreateSuccessResult);
		return valuableItemDAO.fetch(((CreateSuccessResult) saveResult).getPk());
	}

}
