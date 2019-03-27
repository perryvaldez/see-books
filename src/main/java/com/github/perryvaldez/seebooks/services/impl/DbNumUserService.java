package com.github.perryvaldez.seebooks.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.github.perryvaldez.seebooks.datalayer.impl.jpa.JpaUserRepository;
import com.github.perryvaldez.seebooks.models.Role;
import com.github.perryvaldez.seebooks.models.User;
import com.github.perryvaldez.seebooks.models.impl.hibernate.HibRole;
import com.github.perryvaldez.seebooks.models.impl.hibernate.HibUser;
import com.github.perryvaldez.seebooks.services.UserService;

@Service("dbNumUserService")
public class DbNumUserService implements UserService {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger(DbNumUserService.class);
	
	private JpaUserRepository userRepository;
    private SessionFactory sessionFactory;
	
	public DbNumUserService() {}
	
	public DbNumUserService(JpaUserRepository userRepository, SessionFactory sessionFactory) {
	    this.userRepository = userRepository;
	    this.sessionFactory = sessionFactory;
	}

	@Override
	public User getUserByEmail(String email) {
		var hibUser = this.userRepository.findByEmail(email);		
		return hibUser;
	}

	@Override
	public List<Role> getUserRoles(User user) {
		var hibUser = (HibUser) user;
		List<Role> roles = new ArrayList<Role>();
		
		Session session = null;
		try {
		    session = this.sessionFactory.openSession();
		    hibUser = (HibUser) session.merge(hibUser);
		    
		    Set<HibRole> roleSet = hibUser.getRoles();
		    
			if(roleSet != null && roleSet.size() > 0) {		
				roles = roleSet.stream().collect(Collectors.toList());				
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return roles;
	}
}
