package com.github.perryvaldez.seebooks.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.github.perryvaldez.seebooks.services.SecurityUtil;

public class DefaultSecurityUtil implements SecurityUtil {
	@Override
	public String makePrivilege(String realm, String action, String object, String owner) {
		return realm + ":" + action + ":" + object + ":" + owner;
	}

	@Override
	public List<String> getPrivileges(Authentication authentication) {
		return authentication.getAuthorities().stream()
				.map(auth -> ((SimpleGrantedAuthority) auth).getAuthority())
				.collect(Collectors.toList())
				;
	}
}
