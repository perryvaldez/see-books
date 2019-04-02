package com.github.perryvaldez.seebooks.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.github.perryvaldez.seebooks.forms.UserForm;
import com.github.perryvaldez.seebooks.models.types.UserWithRoles;
import com.github.perryvaldez.seebooks.services.UserService;

@Controller
public class AdminController {
	private static final Logger LOGGER = LogManager.getLogger(AdminController.class);
	
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
	
	@GetMapping("/admin/users/{id}")
	public ModelAndView usersGetById(@PathVariable String id) {
		LOGGER.info("==== usersGetById: id = " + id);
		
		return new ModelAndView("views/admin/users/byid", "userForm", new UserForm());
	}
	
	@GetMapping("/admin/businesses")
	public String businesssesGet() {
		return "views/admin/businesses";
	}
}
