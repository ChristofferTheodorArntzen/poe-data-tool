package com.carnnjoh.poedatatool.DB.implementation;

import com.carnnjoh.poedatatool.db.dao.SubscriptionDAO;
import com.carnnjoh.poedatatool.db.model.Subscription;
import com.carnnjoh.poedatatool.db.utils.CreateSuccessResult;
import com.carnnjoh.poedatatool.db.utils.DeleteSuccessResult;
import com.carnnjoh.poedatatool.db.utils.Result;
import com.carnnjoh.poedatatool.db.utils.UpdateSuccessResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestSubscriptionDAO {

	@Autowired
	SubscriptionDAO subscriptionDAO;

	Subscription testSubscription;

	@Before
	public void setup() {
		testSubscription = new Subscription(
			1,
			"test-name",
			new Integer[]{1, 2},
			200.0,
			"chaos",
			null
		);
	}

	@Test
	public void testSave() {
		Result saveResult = subscriptionDAO.save(testSubscription);

		Assert.assertTrue(saveResult instanceof CreateSuccessResult);

		Subscription fetchedSubscription = subscriptionDAO.fetch(testSubscription.getPk());

		Assert.assertEquals(testSubscription.getPk(), fetchedSubscription.getPk());
		Assert.assertEquals(testSubscription.getCurrencyThreshold(), fetchedSubscription.getCurrencyThreshold(), 0.0001);
		Assert.assertEquals(testSubscription.getCurrencyType(), fetchedSubscription.getCurrencyType());
		Assert.assertEquals(testSubscription.getPk(), fetchedSubscription.getPk());
		Assert.assertNull(fetchedSubscription.getPk());
		Assert.assertEquals(testSubscription.getPk(), fetchedSubscription.getPk());
	}

	@Test
	public void testDelete() {
		saveInitialSubscription();

		Subscription fetchedSubscription = subscriptionDAO.fetch(testSubscription.getPk());
		Result deleteResult = subscriptionDAO.deleteByPk(fetchedSubscription.getPk());
		Assert.assertTrue(deleteResult instanceof DeleteSuccessResult);
		fetchedSubscription = subscriptionDAO.fetch(fetchedSubscription.getPk());
		Assert.assertNull(fetchedSubscription);
	}

	@Test
	public void testUpdate() {
		Subscription initialSubscription = saveInitialSubscription();

		initialSubscription.setName("xyz");
		initialSubscription.setCurrencyType("exalted");

		Result updateResult = subscriptionDAO.update(initialSubscription);

		Assert.assertTrue(updateResult instanceof UpdateSuccessResult);

		Subscription fetchedSubscription = subscriptionDAO.fetch(initialSubscription.getPk());

		Assert.assertNotEquals(fetchedSubscription.getName(), testSubscription.getName());
		Assert.assertNotEquals(fetchedSubscription.getCurrencyType(), testSubscription.getCurrencyType());
		Assert.assertEquals(fetchedSubscription.getCurrencyThreshold(), testSubscription.getCurrencyThreshold(), 0.001);
		Assert.assertArrayEquals(fetchedSubscription.getTabIds(), testSubscription.getTabIds());
		Assert.assertEquals(fetchedSubscription.getItemFilterTypes(), testSubscription.getItemFilterTypes());
	}

	@Test
	public void testFetch() {
		saveInitialSubscription();
		Subscription fetchedSubscription = subscriptionDAO.fetch(testSubscription.getPk());

		Assert.assertNotNull(fetchedSubscription);
	}

	@Test
	public void testFetchAll() {
		generateSubscriptions();

		List<Subscription> subscriptions = subscriptionDAO.fetchAll();

		subscriptions.forEach(subscription -> System.out.println(subscription.toString()));

		Assert.assertFalse(subscriptions.isEmpty());

		Assert.assertEquals(100, subscriptions.size());

	}

	private void generateSubscriptions() {
		generateSubscriptions(100);
	}

	private void generateSubscriptions(int numberOfSubscriptions) {
		for (int i = 0; i < numberOfSubscriptions; i++) {
			Subscription subscription = (new Subscription(
				"Name" + i,
				new Integer[]{1, 2, 3},
				200.0,
				"chaos",
				new ArrayList<>()
			));
			subscriptionDAO.save(subscription);
		}
	}

	private Subscription saveInitialSubscription() {

		Result saveResult = subscriptionDAO.save(testSubscription);
		Assert.assertTrue(saveResult instanceof CreateSuccessResult);
		return testSubscription.copy();
	}

}
