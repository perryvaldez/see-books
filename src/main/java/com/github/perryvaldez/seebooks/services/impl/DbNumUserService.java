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

import com.github.perryvaldez.sebooks.utilities.Utils;
import com.github.perryvaldez.seebooks.datalayer.WorkSession;
import com.github.perryvaldez.seebooks.datalayer.impl.HibWorkSession;
import com.github.perryvaldez.seebooks.datalayer.impl.jpa.JpaUserRepository;
import com.github.perryvaldez.seebooks.models.Privilege;
import com.github.perryvaldez.seebooks.models.Role;
import com.github.perryvaldez.seebooks.models.User;
import com.github.perryvaldez.seebooks.models.impl.hibernate.HibRole;
import com.github.perryvaldez.seebooks.models.impl.hibernate.HibUser;
import com.github.perryvaldez.seebooks.models.types.KeyType;
import com.github.perryvaldez.seebooks.models.types.UserWithRoles;
import com.github.perryvaldez.seebooks.models.types.impl.NumericKeyType;
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

	@Override
	public List<Privilege> getUserPrivileges(User user) {
        var hibUser = (HibUser) user;
        List<Privilege> privileges = new ArrayList<Privilege>();

		Session session = null;
		try {
		    session = this.sessionFactory.openSession();
		    hibUser = (HibUser) session.merge(hibUser);

		    privileges = Utils.castList(session
					.createQuery("select priv " + 
		                 "from HibNumPrivilege as priv " + 
							"join fetch priv.role as role " + 
		                    "join fetch priv.realm as realm " + 
							"join fetch priv.action as action " + 
		                    "join fetch priv.object as object " + 
							    "where role in (" + 
		                            "select r from HibUser as user join user.roles as r " + 
							            "where user = :user" + 
							    ")")
					.setParameter("user", hibUser)
					.list())
		    		;

			return privileges;
			
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public List<User> getUsers() {
		List<User> result = this.userRepository.findAll().stream().map(huser -> (User) huser).collect(Collectors.toList());
		
		return result;
	}

	@Override
	public List<UserWithRoles> getUsersWithRoles() {
		Session session = null;
		List<UserWithRoles> userWithRolesList = new ArrayList<UserWithRoles>();
		
		try {
		    session = this.sessionFactory.openSession();
	
		    List <HibUser> userList = Utils.castList(session.createQuery("select user from HibUser user join fetch user.roles as r").list());
			
		    userList.stream().forEach(user -> {
		    	var ur = new UserWithRoles();
		    	ur.setUser(user);
		    	
		    	user.getRoles().stream().forEach(role -> {
		    		ur.getRoles().add(role.getName());
		    	});
		    	
		    	userWithRolesList.add(ur);
		    }); 
		    
			return userWithRolesList;
		} finally {
            if (session != null) {
            	session.close();
            }			
		}

	}

	@Override
	public User getUserById(KeyType id) {
		NumericKeyType numKeyId = (NumericKeyType) id;
		var user = this.userRepository.findById(numKeyId.getValue());
		return user;
	}

	@Override
	public void updateUser(WorkSession workSession, User user) {
        HibWorkSession ws = (HibWorkSession) workSession;
        
        ws.getSession().merge((HibUser) user);
	}
}
