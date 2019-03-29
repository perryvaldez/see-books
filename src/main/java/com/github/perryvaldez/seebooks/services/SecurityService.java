package com.github.perryvaldez.seebooks.services;

import java.util.List;

import org.springframework.security.core.Authentication;

public interface SecurityService {
    boolean matchPrivilege(SecurityPrivilege requiredPrivilege, Authentication authentication);
    boolean matchPrivileges(List<SecurityPrivilege> listOfRequiredPrivileges, Authentication authentication);
    boolean matchPrivilege(Authentication authentication, List<String> requiredPrivilege);
    boolean matchPrivileges(Authentication authentication, List<List<String>> listOfRequiredPrivileges);
}
