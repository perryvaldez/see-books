package com.github.perryvaldez.seebooks.services;

import java.util.List;

import org.springframework.security.core.Authentication;

public interface SecurityService {
    boolean matchPrivilege(String requiredPrivilege, Authentication authentication);
    boolean matchPrivileges(List<String> listOfRequiredPrivileges, Authentication authentication);
}
