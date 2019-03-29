package com.github.perryvaldez.seebooks.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.github.perryvaldez.seebooks.services.SecurityPrivilege;
import com.github.perryvaldez.seebooks.services.SecurityUtil;

public class DefaultSecurityUtil implements SecurityUtil {
	@Override
	public SecurityPrivilege makePrivilege(String realm, String action, String object, String owner) {
		return new SecurityPrivilege(realm, action, object, owner);
	}

	@Override
	public List<SecurityPrivilege> getPrivileges(Authentication authentication) {
		List<String> privStrings = authentication.getAuthorities().stream()
				.map(auth -> ((SimpleGrantedAuthority) auth).getAuthority())
				.collect(Collectors.toList())
				;
		
		if(privStrings.size() > 0 && privStrings.get(0).equals("ROLE_ANONYMOUS")) {
			return new ArrayList<SecurityPrivilege>();  // Zero items
		}
		
		return privStrings.stream()
				.map(privString -> {
					String[] parts = privString.split(":");
					return new SecurityPrivilege(parts[0], parts[1], parts[2], parts[3]); 
				})
				.collect(Collectors.toList());
	}

	@Override
	public boolean matchPart(String required, String given) {
		return (required.equals(given) || required.equals("*") || given.equals("*"));
	}
}
