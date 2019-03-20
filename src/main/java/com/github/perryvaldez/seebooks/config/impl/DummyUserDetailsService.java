package com.github.perryvaldez.seebooks.config.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DummyUserDetailsService implements UserDetailsService {
	private PasswordEncoder encoder;
	
	public DummyUserDetailsService(PasswordEncoder encoder) {
	    this.encoder = encoder;	
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (username.equals("admin")) {
			return User
					.withUsername("admin")
					.password(this.encoder.encode("password"))
					.roles("USER")
					.build()
					;
		}
		
		throw new UsernameNotFoundException("Unknown user: " + username);
	}

}
