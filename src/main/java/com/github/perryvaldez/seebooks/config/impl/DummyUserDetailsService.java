package com.github.perryvaldez.seebooks.config.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DummyUserDetailsService implements UserDetailsService {

	@SuppressWarnings("deprecation")
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (username.equals("admin")) {	
			return User.withDefaultPasswordEncoder()
					.username("admin")
					.password("password")
					.roles("USER")
					.build()
					;
		}
		
		throw new UsernameNotFoundException("Unknown user: " + username);
	}

}
