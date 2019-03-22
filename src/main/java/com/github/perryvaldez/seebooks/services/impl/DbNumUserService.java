package com.github.perryvaldez.seebooks.services.impl;

import com.github.perryvaldez.seebooks.datalayer.impl.jpa.JpaUserRepository;
import com.github.perryvaldez.seebooks.models.User;
import com.github.perryvaldez.seebooks.services.UserService;

public class DbNumUserService implements UserService {
	private JpaUserRepository userRepository;
	
	public DbNumUserService(JpaUserRepository userRepository) {
	    this.userRepository = userRepository;	
	}

	@Override
	public User getUserByEmail(String email) {
		var hibUser = this.userRepository.findByEmail(email);		
		return hibUser;
	}
}
