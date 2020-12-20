package com.carnnjoh.poedatatool.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitialDataLoaderBean implements InitializingBean {

	private static final Logger LOG = LoggerFactory.getLogger(InitialDataLoaderBean.class);

	public InitialDataLoaderBean() {
		LOG.info("Constructor");
	}

	@Override
	public void afterPropertiesSet() {
		LOG.info("InitializingBean");
	}

	@PostConstruct
	public void postConstruct() {
		LOG.info("PostConstruct");
	}

	public void init() {
		LOG.info("init-method");
	}

}