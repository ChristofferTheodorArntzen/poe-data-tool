package com.carnnjoh.poedatatool.api;


import com.carnnjoh.poedatatool.db.dao.SubscriptionDAO;
import com.carnnjoh.poedatatool.db.dao.ValuableItemDAO;
import com.carnnjoh.poedatatool.db.model.Subscription;
import com.carnnjoh.poedatatool.db.utils.Result;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestSubscriptionController {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private SubscriptionDAO subscriptionDAO;

	String endPointUrl;

	@BeforeEach
	private void setUp(){
		endPointUrl = "http://localhost:" + port + "/subscription";

		String[] tabIds = {"1", "2", "3", "4", "5"};

		Subscription subscription = new Subscription(null, tabIds, 20.0, "chaos");
		Subscription subscription1 = new Subscription(null, tabIds, 10.0, "exalted");
		Subscription subscription2 = new Subscription(null, tabIds, 100.0, "jewelers");

		subscriptionDAO.save(subscription);
		subscriptionDAO.save(subscription1);
		subscriptionDAO.save(subscription2);

	}


	@Test
	public void testGet() {
		ResponseEntity<Subscription> subscriptionResponse = restTemplate.getForEntity(endPointUrl + "/1", Subscription.class);

		Assert.isTrue(subscriptionResponse.getStatusCode() == HttpStatus.OK,
			"Http status for get call was incorrect");

		Assert.notNull(subscriptionResponse.getBody(),
			"Response body for get call was null");

		Assert.notEmpty(subscriptionResponse.getBody().getTabIds(),
			"Tab id string array was empty");

		String[] tabArray = {"1", "2", "3", "4", "5"};
		Subscription subscription = subscriptionResponse.getBody();

		Assert.isTrue(subscription.getPk() == 1,
			"Pk for first object created was not equal to 1");

		Assert.isTrue(Arrays.equals(subscription.getTabIds(), tabArray),
			"TabIds had incorrect values");

		Assert.isTrue(subscription.getThreshold() == 20.0,
			"Threshold was not equal to the initially saved object");

		Assert.isTrue(subscription.getThresholdCurrencyType().equals("chaos"),
			"ThresholdCurrencyType was not equal to the initially saved object");
	}

	@Test
	public void testGetAll() {
		ResponseEntity<Subscription[]> subscriptionResponse = restTemplate.getForEntity(endPointUrl, Subscription[].class);

		Assert.isTrue(subscriptionResponse.getStatusCode() == HttpStatus.OK,
			"Http status for get call was incorrect");

		Subscription[] subscriptions = subscriptionResponse.getBody();

		Assert.isTrue(subscriptionResponse.getBody().length == 3,
			"The length of the Object array that was in the http response was not expected");
	}

	@Test
	public void testDelete() {
		restTemplate.delete(endPointUrl + "/1");

		Subscription subscription = subscriptionDAO.fetch(1);

		Assert.isTrue(subscription == null, "Subscription was not deleted");
	}

	@Test
	public void testPost(){
		Subscription subscriptionForPost = new Subscription(
			null,
			new String[]{"1", "2", "3"},
			20.0,
			"alch"
		);

		ResponseEntity<Subscription> subscriptionResponse = restTemplate.
			postForEntity(endPointUrl, subscriptionForPost, Subscription.class);

		Assert.isTrue(subscriptionResponse.getStatusCode() == HttpStatus.OK,
			"Posting a valid subscription entity failed");

		Subscription receivedSubscription = subscriptionResponse.getBody();

		Assert.isTrue(receivedSubscription.getPk() == 4,
			"Subscription for the fourth saved object was not equal to 4");

		Assert.isTrue(Arrays.equals(receivedSubscription.getTabIds(), new String[]{"1","2","3"}),
			"Tab ids for subscription that was received from post call was not equal to the posted entity");

		Assert.isTrue(receivedSubscription.getThreshold() == 20.0,
			"Threshold for received subscription was not equal to that of the posted entity");

		Assert.isTrue(receivedSubscription.getThresholdCurrencyType().equals("alch"),
			"Threshold for received subscription was not equal to that of the posted entity");
	}

	@Test
	public void testPut(){

		HttpHeaders request = new HttpHeaders();

		//TODO: Sett lag test ferdig her
		// Se på endringer som ble gjort til Subscription entiten for at SQL ikke skulle feile.
		// Måtte sette initial verdi for alle member variablene - Kanskje ikke så gunstig?

	}


}
