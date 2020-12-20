package com.carnnjoh.poedatatool.init;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class initialBean {

	@Bean(initMethod="init")
	public InitialDataLoaderBean initDataBase() {
		return new InitialDataLoaderBean();
	}
}
