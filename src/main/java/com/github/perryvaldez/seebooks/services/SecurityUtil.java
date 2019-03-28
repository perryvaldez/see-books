package com.github.perryvaldez.seebooks.services;

import java.util.List;

import org.springframework.security.core.Authentication;

public interface SecurityUtil {
	public SecurityPrivilege makePrivilege(String realm, String action, String object, String owner);
	public List<SecurityPrivilege> getPrivileges(Authentication authentication);
}
