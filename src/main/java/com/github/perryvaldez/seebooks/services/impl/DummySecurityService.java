package com.github.perryvaldez.seebooks.services.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.github.perryvaldez.seebooks.services.SecurityService;
import com.github.perryvaldez.seebooks.services.SecurityUtil;

@Service("dummySecurityService")
public class DummySecurityService implements SecurityService {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger(DummySecurityService.class);
	
	private SecurityUtil securityUtil;
	
	public DummySecurityService(SecurityUtil securityUtil) {
		this.securityUtil = securityUtil;
	}
	
	@Override
	public boolean matchPrivilege(String requiredPrivilege, Authentication authentication) {
        return this.matchPrivileges(Arrays.asList(requiredPrivilege), authentication);
	}

	@Override
	public boolean matchPrivileges(List<String> listOfRequiredPrivileges, Authentication authentication) {
		listOfRequiredPrivileges.stream().forEach(priv -> {
			LOGGER.info("==== Matching privilege: required: " + priv);
		});
				
		List<String> privileges = securityUtil.getPrivileges(authentication);
		
		if(privileges.size() == 0) {
            LOGGER.info("==== Matching privilege: None found: ");
		} else {
			privileges.stream().forEach(auth -> {
				LOGGER.info("==== Matching privilege: found: " + auth);
			});			
		}

		return true;
	}
}
