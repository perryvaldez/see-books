package com.github.perryvaldez.seebooks.services;

import java.util.List;

import org.springframework.security.core.Authentication;

public interface SecurityUtil {
	public String makePrivilege(String realm, String action, String object, String owner);
	public List<String> getPrivileges(Authentication authentication);
}
