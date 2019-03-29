package com.github.perryvaldez.seebooks.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping("/admin/users")
	public String usersGet() {
		return "views/admin/users";
	}
	
	@GetMapping("/admin/businesses")
	public String businesssesGet() {
		return "views/admin/businesses";
	}
}
