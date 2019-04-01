package com.github.perryvaldez.seebooks.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.github.perryvaldez.seebooks.models.types.UserWithRoles;
import com.github.perryvaldez.seebooks.services.UserService;

@Controller
public class AdminController {
    private UserService userService;
	
	public AdminController(UserService userService) {
    	this.userService = userService;
    }
	
	@GetMapping("/admin/users")
	public String usersGet(ModelMap model) {
		List<UserWithRoles> userList = this.userService.getUsersWithRoles();
		
		model.addAttribute("userList", userList);
		return "views/admin/users";
	}
	
	@GetMapping("/admin/businesses")
	public String businesssesGet() {
		return "views/admin/businesses";
	}
}
