package com.github.perryvaldez.seebooks.controllers;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.github.perryvaldez.seebooks.services.SecurityPrivilege;
import com.github.perryvaldez.seebooks.services.SecurityService;

@Controller
public class IndexController {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger(IndexController.class);
	
	private SecurityService securityService;
	
	public IndexController(SecurityService securityService) {
		this.securityService = securityService;
	}
	
	@GetMapping("/")
	public String indexGet(ModelMap model) {	
		model.addAttribute("myvar", "vall");
				
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		SecurityPrivilege canManageUser = new SecurityPrivilege("*", "can_manage", "user", "*");
		SecurityPrivilege canManageBusiness = new SecurityPrivilege("*", "can_manage", "business", "*");
	
	    List<SecurityPrivilege> requiredPrivileges = Arrays.asList(canManageUser, canManageBusiness);
	    boolean showAdminSection = this.securityService.matchPrivileges(requiredPrivileges, authentication);
	    
	    boolean showManageUsers = this.securityService.matchPrivilege(canManageUser, authentication);
	    boolean showManageBusinesses = this.securityService.matchPrivilege(canManageBusiness, authentication);
		
        model.addAttribute("showAdminSection", showAdminSection);
        model.addAttribute("showManageUsers", showManageUsers);
        model.addAttribute("showManageBusinesses", showManageBusinesses);
        
		return "views/index/index";
	}
}
