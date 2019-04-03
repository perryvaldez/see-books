package com.github.perryvaldez.seebooks.config;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.perryvaldez.seebooks.datalayer.KeyUtilities;
import com.github.perryvaldez.seebooks.datalayer.UnitOfWorkManager;
import com.github.perryvaldez.seebooks.datalayer.impl.DbNumericKeyUtilities;
import com.github.perryvaldez.seebooks.datalayer.impl.HibUnitOfWorkManager;

@Configuration
public class DataLayerConfig {
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Bean
	public KeyUtilities keyUtilities() {
		return new DbNumericKeyUtilities();
	}
	
	@Bean
	public UnitOfWorkManager unitOfWorkManager() {
		SessionFactory sessionFactory = this.entityManagerFactory.unwrap(SessionFactory.class);
		return new HibUnitOfWorkManager(sessionFactory);
	}
}
