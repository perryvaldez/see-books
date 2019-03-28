package com.github.perryvaldez.seebooks.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@PreAuthorize("@securityService.matchPrivilege(@securityUtil.makePrivilege('aa', 'bb', 'cc', 'dd'), authentication)")
	@GetMapping("/admin/users")
	public String usersGet() {
		return "views/admin/users";
	}
	
	@PreAuthorize("@securityService.matchPrivilege(@securityUtil.makePrivilege('ee', 'ff', 'gg', 'hh'), authentication)")
	@GetMapping("/admin/businesses")
	public String businesssesGet() {
		return "views/admin/businesses";
	}
}
