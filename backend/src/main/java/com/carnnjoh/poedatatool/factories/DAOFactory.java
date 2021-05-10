package com.carnnjoh.poedatatool.factories;

import com.carnnjoh.poedatatool.db.DataSourceConfig;
import com.carnnjoh.poedatatool.db.dao.SubscriptionDAO;
import com.carnnjoh.poedatatool.db.dao.UserDAO;
import com.carnnjoh.poedatatool.db.dao.ValuableItemDAO;
import com.carnnjoh.poedatatool.db.implementation.SubscriptionDAOImpl;
import com.carnnjoh.poedatatool.db.implementation.UserDAOImpl;
import com.carnnjoh.poedatatool.db.implementation.ValuableItemDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DAOFactory {

	@Autowired
	NamedParameterJdbcTemplate template;

	@Autowired
	GeneratedKeyHolderFactory keyHolderFactory;

	@Autowired
	private DataSourceConfig dataSourceConfig;

	@Bean
	public ValuableItemDAO getValuableItemDAO() {
		return new ValuableItemDAOImpl(template, keyHolderFactory);
	}

	@Bean
	public SubscriptionDAO getSubscriptionDAO() {
		return new SubscriptionDAOImpl(template, keyHolderFactory);
	}

	@Bean
	public UserDAO getUserDAO() {
		return new UserDAOImpl(template,keyHolderFactory);
	}
}
