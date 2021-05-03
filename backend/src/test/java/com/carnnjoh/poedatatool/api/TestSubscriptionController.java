package com.carnnjoh.poedatatool.api;


import com.carnnjoh.poedatatool.db.dao.SubscriptionDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestSubscriptionController {

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



	}
	@Test
	void testGet() {

	}

	@Test
	void testGetAll() {

	}

	@Test
	void testDelete() {

	}

	@Test
	void testPost(){

	}

	@Test
	void testPut(){


	}


}
