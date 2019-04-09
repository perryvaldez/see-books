package com.github.perryvaldez.seebooks.config;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.perryvaldez.seebooks.datalayer.impl.jpa.JpaRoleRepository;
import com.github.perryvaldez.seebooks.datalayer.impl.jpa.JpaUserRepository;
import com.github.perryvaldez.seebooks.services.UserService;
import com.github.perryvaldez.seebooks.services.impl.DbNumUserService;

@Configuration
public class ServiceConfig {
	@Autowired
	private JpaUserRepository userRepository;
	
	@Autowired
	private JpaRoleRepository roleRepository;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Bean
	public UserService userService() {
		SessionFactory sessionFactory = this.entityManagerFactory.unwrap(SessionFactory.class);
		return new DbNumUserService(this.userRepository, this.roleRepository, sessionFactory);
	}
}
