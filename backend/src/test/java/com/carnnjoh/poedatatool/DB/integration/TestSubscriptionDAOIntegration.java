package com.carnnjoh.poedatatool.DB.integration;

import com.carnnjoh.poedatatool.db.dao.SubscriptionDAO;
import com.carnnjoh.poedatatool.db.model.Subscription;
import com.carnnjoh.poedatatool.db.utils.CreateSuccessResult;
import com.carnnjoh.poedatatool.db.utils.DeleteSuccessResult;
import com.carnnjoh.poedatatool.db.utils.Result;
import com.carnnjoh.poedatatool.db.utils.UpdateSuccessResult;
import com.carnnjoh.poedatatool.model.ItemType;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.*;

@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@ActiveProfiles("test")
public class TestSubscriptionDAOIntegration {

	@Autowired
	SubscriptionDAO subscriptionDAO;

	Subscription testSubscription;

	Offset<Double> offset = Offset.offset(0.01);

	@BeforeEach
	public void setup() {
		testSubscription = new Subscription(
			"test-name",
			new Integer[]{1, 2},
			200.0,
			"chaos",
			Arrays.asList(ItemType.CARD, ItemType.FRAGMENT),
			true
		);

		Result saveResult = subscriptionDAO.save(testSubscription);
		testSubscription.setPk(((CreateSuccessResult) saveResult).getPk());
	}

	@AfterEach
	public void flush() {
		List<Subscription> subscriptions = subscriptionDAO.fetchAll();

		for (Subscription subscription : subscriptions) {
			subscriptionDAO.deleteByPk(subscription.getPk());
		}
	}

	@Test
	public void testSave() {
		Result saveResult = subscriptionDAO.save(testSubscription);

		assertThat(saveResult).isInstanceOf(CreateSuccessResult.class);

		Subscription fetchedSubscription = subscriptionDAO.fetch(testSubscription.getPk());

		assertThat(fetchedSubscription.getPk()).isEqualTo(testSubscription.getPk());
		assertThat(fetchedSubscription.getCurrencyThreshold()).isCloseTo(testSubscription.getCurrencyThreshold(), offset);
		assertThat(fetchedSubscription.getCurrencyType()).isEqualTo(testSubscription.getCurrencyType());
		assertThat(fetchedSubscription.getName()).isEqualTo(testSubscription.getName());
		assertThat(fetchedSubscription.getItemTypes()).isEqualTo(testSubscription.getItemTypes());
		assertThat(fetchedSubscription.getTabIds()).isEqualTo(testSubscription.getTabIds());

	}

	@Test
	public void testDelete() {
		Subscription fetchedSubscription = subscriptionDAO.fetch(testSubscription.getPk());

		Result deleteResult = subscriptionDAO.deleteByPk(fetchedSubscription.getPk());
		assertThat(deleteResult).isInstanceOf(DeleteSuccessResult.class);

		fetchedSubscription = subscriptionDAO.fetch(fetchedSubscription.getPk());
		assertThat(fetchedSubscription).isNull();
	}

	@Test
	public void testUpdate() {
		Subscription initialSubscription = subscriptionDAO.fetch(testSubscription.getPk());

		initialSubscription.setName("xyz");
		initialSubscription.setCurrencyType("exalted");

		Result updateResult = subscriptionDAO.update(initialSubscription);

		assertThat(updateResult).isInstanceOf(UpdateSuccessResult.class);

		Subscription fetchedSubscription = subscriptionDAO.fetch(initialSubscription.getPk());

		assertThat(fetchedSubscription.getName()).isNotEqualTo(testSubscription.getName());
		assertThat(fetchedSubscription.getCurrencyType()).isNotEqualTo(testSubscription.getCurrencyType());
		assertThat(fetchedSubscription.getCurrencyThreshold()).isCloseTo(testSubscription.getCurrencyThreshold(), offset);
		assertThat(fetchedSubscription.getTabIds()).isEqualTo(testSubscription.getTabIds());
		assertThat(fetchedSubscription.getItemTypes()).isEqualTo(testSubscription.getItemTypes());
	}

	@Test
	public void testFetch() {
		Subscription fetchedSubscription = subscriptionDAO.fetch(testSubscription.getPk());
		assertThat(fetchedSubscription).isNotNull();
		assertThat(fetchedSubscription.getPk()).isEqualTo(testSubscription.getPk());
	}

	@Test
	public void testFetchAll() {
		generateSubscriptions();

		List<Subscription> subscriptions = subscriptionDAO.fetchAll();
		assertThat(subscriptions.size() > 0).isTrue();

		for(Subscription subscription : subscriptions) {
			assertThat(subscription.getPk()).isNotNull();
		}
	}

	@Test
	public void testFetchByStatus() {
		Subscription subscription = subscriptionDAO.fetchFirstActive(true);
		assertThat(subscription).isNotNull();
		assertThat(subscription.isActive()).isTrue();
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
				new ArrayList<>(),
				false
			));
			subscriptionDAO.save(subscription);
		}
	}

}
