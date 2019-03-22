package com.github.perryvaldez.seebooks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.perryvaldez.seebooks.datalayer.impl.jpa.JpaUserRepository;
import com.github.perryvaldez.seebooks.services.UserService;
import com.github.perryvaldez.seebooks.services.impl.DbNumUserService;

@Configuration
public class ServiceConfig {
	@Autowired
	private JpaUserRepository userRepository;
	
	@Bean
	public UserService userService() {
		return new DbNumUserService(this.userRepository);
	}
}
