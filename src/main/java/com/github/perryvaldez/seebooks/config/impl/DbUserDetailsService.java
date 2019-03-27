package com.github.perryvaldez.seebooks.config.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.perryvaldez.seebooks.models.Privilege;
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
		String[] privArray = new String[0];
		Set<String> privList = new HashSet<String>();
		
		try {
		    ud = this.userService.getUserByEmail(username);
		    
		    if (ud != null) {			
				List<Privilege> privileges = this.userService.getUserPrivileges(ud);
				
				privileges.stream().forEach(priv -> {
					privList.add(priv.serialize());
				});
				
				privArray = privList.toArray(String[]::new);
				
				return new org.springframework.security.core.userdetails.User(username, ud.getPassword(), 
		                true, true, true, true, AuthorityUtils.createAuthorityList(privArray));	
		    }
			
		} catch(Exception ex) {
			LOGGER.error("==== Exception thrown while retrieving user info: ", ex);
			throw ex;
		}
	    	
        throw new UsernameNotFoundException("Unknown username: " + username);
	}
}
