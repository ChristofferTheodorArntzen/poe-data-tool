package com.carnnjoh.poedatatool.factories;

import com.carnnjoh.poedatatool.db.inMemory.dao.ConstantsDao;
import com.carnnjoh.poedatatool.db.inMemory.dao.StatsDao;
import com.carnnjoh.poedatatool.db.inMemory.dao.UniqueDao;
import com.carnnjoh.poedatatool.db.inMemory.implementation.ConstantsDaoImpl;
import com.carnnjoh.poedatatool.db.inMemory.implementation.StatsDaoImpl;
import com.carnnjoh.poedatatool.db.inMemory.implementation.UniqueDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InMemoryDAOFactory {

	ConstantsDao constantsDao = null;
	StatsDao statsDao = null;
	UniqueDao uniqueDao = null;

	@Bean
	public ConstantsDao getConstantsDao() {
		if(constantsDao == null)
			return constantsDao = new ConstantsDaoImpl();

		return constantsDao;
	}

	@Bean
	public StatsDao getStatsDao() {
		if(statsDao == null)
			return statsDao = new StatsDaoImpl();

		return statsDao;
	}

	@Bean
	public UniqueDao getUniqueDao() {
		if (uniqueDao == null) {
			return uniqueDao = new UniqueDaoImpl();
		}

		return uniqueDao;
	}

}
