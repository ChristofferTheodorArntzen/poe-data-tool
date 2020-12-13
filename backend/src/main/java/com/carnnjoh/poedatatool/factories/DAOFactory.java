package com.carnnjoh.poedatatool.factories;

import com.carnnjoh.poedatatool.db.dao.SubscriptionDAO;
import com.carnnjoh.poedatatool.db.dao.UserDAO;
import com.carnnjoh.poedatatool.db.dao.ValuableItemDAO;
import com.carnnjoh.poedatatool.db.implementation.SubscriptionDAOImpl;
import com.carnnjoh.poedatatool.db.implementation.UserDAOImpl;
import com.carnnjoh.poedatatool.db.implementation.ValuableItemDAOImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DAOFactory {

	@Autowired
	private NamedParameterJdbcTemplate template;

	@Autowired
	private ObjectMapper objectMapper;

	@Bean
	public ValuableItemDAO getValuableItemDAO() {
		return new ValuableItemDAOImpl(template, objectMapper);
	}

	@Bean
	public SubscriptionDAO getSubscriptionDAO() {
		return new SubscriptionDAOImpl(template);
	}

	@Bean
	public UserDAO getUserDAO() {
		return new UserDAOImpl(template);
	}




}
