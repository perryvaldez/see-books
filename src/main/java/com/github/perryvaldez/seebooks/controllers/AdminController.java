package com.github.perryvaldez.seebooks.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.github.perryvaldez.seebooks.datalayer.KeyGen;
import com.github.perryvaldez.seebooks.forms.UserForm;
import com.github.perryvaldez.seebooks.models.User;
import com.github.perryvaldez.seebooks.models.types.KeyType;
import com.github.perryvaldez.seebooks.models.types.UserWithRoles;
import com.github.perryvaldez.seebooks.services.UserService;

@Controller
public class AdminController {
	private static final Logger LOGGER = LogManager.getLogger(AdminController.class);
	
    private UserService userService;
    private KeyGen keyUtil;
	
	public AdminController(UserService userService, KeyGen keyUtil) {
    	this.userService = userService;
    	this.keyUtil = keyUtil;
    }
	
	@GetMapping("/admin/users")
	public String usersGet(ModelMap model) {
		List<UserWithRoles> userList = this.userService.getUsersWithRoles();
		
		model.addAttribute("userList", userList);
		return "views/admin/users";
	}
	
	@GetMapping("/admin/users/{id}/edit")
	public ModelAndView usersGetByIdEdit(@PathVariable String id, @ModelAttribute("userForm") UserForm userForm) {
		KeyType key = this.keyUtil.makeKey(id);
		
		User user = this.userService.getUserById(key);
		
		userForm.setId(id);
		userForm.setEmail(user.getEmail());
		userForm.setPassword("********");		
					
		return new ModelAndView("views/admin/users/byid_edit", "userForm", userForm);
	}
	
	@GetMapping("/admin/businesses")
	public String businesssesGet() {
		return "views/admin/businesses";
	}
}
