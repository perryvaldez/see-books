package com.github.perryvaldez.seebooks.config.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.perryvaldez.seebooks.models.Role;
import com.github.perryvaldez.seebooks.models.User;
import com.github.perryvaldez.seebooks.services.UserService;

@Service("dbUserDetailsService")
public class DbUserDetailsService implements UserDetailsService {
	private static final Logger LOGGER = LogManager.getLogger(DbUserDetailsService.class);
	
    private UserService userService;
	
	public DbUserDetailsService(UserService userService) {  
	    this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User ud;
		String[] rolesArray = new String[0];
		
		try {
		    ud = this.userService.getUserByEmail(username);
		    
		    if (ud != null) {
			    List<Role> roles = this.userService.getUserRoles(ud);
				rolesArray = roles.stream().map(role -> role.getName()).toArray(String[]::new);

				return new org.springframework.security.core.userdetails.User(username, ud.getPassword(), 
		                true, true, true, true, AuthorityUtils.createAuthorityList(rolesArray));	
		    }
			
		} catch(Exception ex) {
			LOGGER.error("==== Exception thrown while retrieving user info: ", ex);
			throw ex;
		}
	    	
        throw new UsernameNotFoundException("Unknown username: " + username);
	}
}
