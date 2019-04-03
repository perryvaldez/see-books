package com.github.perryvaldez.seebooks.controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.perryvaldez.seebooks.datalayer.KeyUtilities;
import com.github.perryvaldez.seebooks.datalayer.UnitOfWorkManager;
import com.github.perryvaldez.seebooks.datalayer.WorkSession;
import com.github.perryvaldez.seebooks.forms.UserForm;
import com.github.perryvaldez.seebooks.forms.UserFormValidator;
import com.github.perryvaldez.seebooks.models.User;
import com.github.perryvaldez.seebooks.models.types.KeyType;
import com.github.perryvaldez.seebooks.models.types.UserWithRoles;
import com.github.perryvaldez.seebooks.services.UserService;

@Controller
public class AdminController {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger(AdminController.class);
	
    private UserService userService;
    private KeyUtilities keyUtil;
    private UnitOfWorkManager uowManager;
    private UserFormValidator userFormValidator;
	
	public AdminController(UserService userService, KeyUtilities keyUtil, UnitOfWorkManager uowManager, UserFormValidator userFormValidator) {
    	this.userService = userService;
    	this.keyUtil = keyUtil;
    	this.uowManager = uowManager;
    	this.userFormValidator = userFormValidator;
    }
	
	@InitBinder("userForm")
	public void setupBinder(WebDataBinder binder) {
	    binder.addValidators(this.userFormValidator);
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
		userForm.setHashedPassword(user.getPassword());		
					
		return new ModelAndView("views/admin/users/byid_edit", "userForm", userForm);
	}
	
	@PostMapping("/admin/users/{id}/edit")
	public ModelAndView usersPostByIdEdit(@Valid @ModelAttribute("userForm") UserForm userForm, BindingResult bindResult, @PathVariable String id) {
		if(bindResult.hasErrors()) {
			bindResult.getFieldErrors().stream().forEach(fieldError -> {
		        LOGGER.error("==== Error in field '" + fieldError.getField() + "': " + fieldError.getDefaultMessage());
			});    	
		} else {
		    try (WorkSession workSession = this.uowManager.begin()) {			
				KeyType key = this.keyUtil.makeKey(id);		
				User user = this.userService.getUserById(key);
				
				user.setEmail(userForm.getEmail());
				user.setPassword(userForm.getHashedPassword());
				
				this.userService.updateUser(workSession, user);
				workSession.commit();	    	
		    }			
		}
		
		return new ModelAndView("redirect:/admin/users");
	}
	
	@GetMapping("/admin/businesses")
	public String businesssesGet() {
		return "views/admin/businesses";
	}
}
