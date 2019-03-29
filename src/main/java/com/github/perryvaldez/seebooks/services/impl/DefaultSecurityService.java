package com.github.perryvaldez.seebooks.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.github.perryvaldez.seebooks.services.SecurityPrivilege;
import com.github.perryvaldez.seebooks.services.SecurityService;
import com.github.perryvaldez.seebooks.services.SecurityUtil;

@Service("defaultSecurityService")
public class DefaultSecurityService implements SecurityService {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger(DefaultSecurityService.class);
	
	private SecurityUtil securityUtil;
	
	public DefaultSecurityService(SecurityUtil securityUtil) {
		this.securityUtil = securityUtil;
	}
	
	@Override
	public boolean matchPrivilege(SecurityPrivilege requiredPrivilege, Authentication authentication) {
        return this.matchPrivileges(Arrays.asList(requiredPrivilege), authentication);
	}

	@Override
	public boolean matchPrivileges(List<SecurityPrivilege> listOfRequiredPrivileges, Authentication authentication) {
		listOfRequiredPrivileges.stream().forEach(priv -> {
			LOGGER.debug("==== Matching privilege: required: " + priv);
		});
				
		List<SecurityPrivilege> userPrivileges = securityUtil.getPrivileges(authentication);
		
		for(SecurityPrivilege requiredPriv : listOfRequiredPrivileges) {
		    for(SecurityPrivilege userPriv : userPrivileges) {
				boolean realmMatch = (this.securityUtil.matchPart(requiredPriv.getRealm(), userPriv.getRealm()));
				boolean actionMatch = (this.securityUtil.matchPart(requiredPriv.getAction(), userPriv.getAction()));
				boolean objectMatch = (this.securityUtil.matchPart(requiredPriv.getObject(), userPriv.getObject()));
				boolean ownerMatch = (this.securityUtil.matchPart(requiredPriv.getOwner(), userPriv.getOwner()));
				
				if (realmMatch && actionMatch && objectMatch && ownerMatch) {
					LOGGER.debug("==== User privilege " + userPriv + " matches required privilege " + requiredPriv);
					return true;
				}		    	
		    }	
		}

		LOGGER.debug("==== User privileges do not match required privileges");
		return false;
	}

	@Override
	public boolean matchPrivilege(Authentication authentication, List<String> requiredPrivilege) {
		return this.matchPrivilege(new SecurityPrivilege(requiredPrivilege), authentication);
	}

	@Override
	public boolean matchPrivileges(Authentication authentication, List<List<String>> listOfRequiredPrivileges) {
		List<SecurityPrivilege> list = listOfRequiredPrivileges.stream().map(part -> new SecurityPrivilege(part)).collect(Collectors.toList()); 
		return this.matchPrivileges(list, authentication);		
	}
}
