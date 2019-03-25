package com.github.perryvaldez.seebooks.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.github.perryvaldez.seebooks.datalayer.impl.jpa.JpaUserRepository;
import com.github.perryvaldez.seebooks.models.Role;
import com.github.perryvaldez.seebooks.models.User;
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
		    var query = session
		    		.createQuery("select r from HibUser u join u.roles r where u.numId = :numId")
		    		.setParameter("numId", hibUser.getNumId())    
		    		;
		    
		    var list = query.getResultList();
		    
			if(list != null && list.size() > 0) {			
				for(var item : list) {
					roles.add((Role) item);
				}
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
		
		return roles;
	}
}
