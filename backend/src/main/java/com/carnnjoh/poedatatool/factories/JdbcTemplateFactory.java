package com.carnnjoh.poedatatool.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplateFactory {

	@Autowired
	private DataSource dataSource;

	@Bean
	public JdbcTemplate createTemplate() {
		return new JdbcTemplate(dataSource);
	}
}