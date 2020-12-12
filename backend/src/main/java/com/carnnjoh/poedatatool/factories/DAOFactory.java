package com.carnnjoh.poedatatool.factories;

import com.carnnjoh.poedatatool.db.model.ValuableItemDAO;
import com.carnnjoh.poedatatool.db.model.ValuableItemDAOImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DAOFactory {

	@Autowired
	JdbcTemplate template;

	@Autowired
	ObjectMapper objectMapper;

	public ValuableItemDAO getValuableItemDAO(){
		return new ValuableItemDAOImpl(template, objectMapper);
	}






}
