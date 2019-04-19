package com.github.perryvaldez.seebooks.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import com.github.perryvaldez.sebooks.utilities.Utils;
import com.github.perryvaldez.seebooks.datalayer.impl.jpa.JpaRoleRepository;
import com.github.perryvaldez.seebooks.models.Role;
import com.github.perryvaldez.seebooks.models.impl.hibernate.HibRole;
import com.github.perryvaldez.seebooks.models.types.KeyType;
import com.github.perryvaldez.seebooks.models.types.impl.NumericKeyType;
import com.github.perryvaldez.seebooks.services.RoleService;

@Service("dbNumRoleService")
public class DbNumRoleService implements RoleService {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger(DbNumRoleService.class);
	
	@SuppressWarnings("unused")
	private JpaRoleRepository roleRepository;
	
	private SessionFactory sessionFactory;
	
	public DbNumRoleService() {}
	
	public DbNumRoleService(JpaRoleRepository roleRepository, SessionFactory sessionFactory) {
		this.roleRepository = roleRepository;
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Role> getRolesExceptIds(List<KeyType> idList) {
		List<Role> result = new ArrayList<Role>();
		
		List<Long> numIds = idList.stream()
				.map(id -> ((NumericKeyType) id).getValue())
				.collect(Collectors.toList())
				;
		
		List<HibRole> hroles = new ArrayList<HibRole>();
		try (Session session = sessionFactory.openSession()) {
		    if (numIds.size() > 0) {
		    	LOGGER.info("==== getRolesExceptIds: idList: " + idList);
		    	hroles = Utils.castList(
		    			session.createQuery("from HibRole role where role.numId not in (:numIdList) order by role.name")
		    			       .setParameterList("numIdList", numIds)
		    			       .list()
		    			);
		    } else {
		    	LOGGER.info("==== getRolesExceptIds: No idList, returning all roles...");
		    	
		    	hroles = Utils.castList(session.createQuery("from HibRole role order by role.name").list());	
		    }
		    
	    	result = hroles.stream()
	    			.map(hr -> (Role)hr)
	    			.collect(Collectors.toList())
	    			;
		    
		    return result;
		}
	}

}
