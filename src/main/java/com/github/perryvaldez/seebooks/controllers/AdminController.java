package com.github.perryvaldez.seebooks.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import com.github.perryvaldez.sebooks.utilities.FormUtils;
import com.github.perryvaldez.sebooks.utilities.Utils;
import com.github.perryvaldez.seebooks.datalayer.KeyUtilities;
import com.github.perryvaldez.seebooks.datalayer.UnitOfWorkManager;
import com.github.perryvaldez.seebooks.datalayer.WorkSession;
import com.github.perryvaldez.seebooks.forms.UserForm;
import com.github.perryvaldez.seebooks.forms.UserFormValidator;
import com.github.perryvaldez.seebooks.models.Role;
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
    private PasswordEncoder passwordEncoder;
	
	public AdminController(UserService userService, KeyUtilities keyUtil, UnitOfWorkManager uowManager, 
			UserFormValidator userFormValidator, PasswordEncoder passwordEncoder) {
		
    	this.userService = userService;
    	this.keyUtil = keyUtil;
    	this.uowManager = uowManager;
    	this.userFormValidator = userFormValidator;
    	this.passwordEncoder = passwordEncoder;
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
		List<Role> roles = this.userService.getUserRoles(user);
		
		userForm.setId(id);
		userForm.setEmail(user.getEmail());
		userForm.setPassword("");
		userForm.setConfirmPassword("");
		
		roles.stream().forEach(role -> {				
			userForm.getRoleIds().add(role.getId().serialize());
			userForm.getRoleLookup().put(role.getId().serialize(), role.getName());
		}); 

		FormUtils.saveFormOrigValues(userForm);
		
		return new ModelAndView("views/admin/users/byid_edit", "userForm", userForm);
	}
	
	@PostMapping("/admin/users/{id}/edit")
	public ModelAndView usersPostByIdEdit(@Valid @ModelAttribute("userForm") UserForm userForm, BindingResult bindResult, ModelMap model) {
		if(bindResult.hasErrors()) {
		    List<String> globalErrors = bindResult.getGlobalErrors().stream()
		    		.map(item -> item.getDefaultMessage())
		    		.collect(Collectors.toList())
		    		;
		    
		    Map<String, String> fieldErrors = new HashMap<String, String>();
			bindResult.getFieldErrors().stream().forEach(item -> {
				fieldErrors.put(item.getField(), item.getDefaultMessage());
			});
			
			model.addAttribute("globalErrors", globalErrors);
			model.addAttribute("fieldErrors", fieldErrors);
			
			return new ModelAndView("views/admin/users/byid_edit", "userForm", userForm);
		} else {				
			List<String> dirtyProps = FormUtils.getDirtyProperties(userForm).stream()
					.filter((p -> p == "email" || p == "password" || p == "roleIds"))
					.collect(Collectors.toList())
					;
			
			if(dirtyProps.size() > 0) {			
			    try (WorkSession workSession = this.uowManager.begin()) {			
					KeyType key = this.keyUtil.makeKey(userForm.getId());		
					User user = this.userService.getUserById(key);
					
					if(dirtyProps.contains("email")) {
						user.setEmail(userForm.getEmail());	
					}
					
					if(dirtyProps.contains("password")) {
						user.setPassword(this.passwordEncoder.encode(userForm.getPassword()));	
					}

					if(dirtyProps.contains("roleIds")) {
						List<String> origRoleIds = FormUtils.getOriginalValue(userForm, "roleIds");
 
						List<String> deletedIds = Utils.listDifference(origRoleIds, userForm.getRoleIds());
						List<String> insertedIds = Utils.listDifference(userForm.getRoleIds(), origRoleIds);
						
						List<KeyType> deletedRoleKeys = deletedIds.stream().map(id -> this.keyUtil.makeKey(id)).collect(Collectors.toList());
						List<KeyType> insertedRoleKeys = insertedIds.stream().map(id -> this.keyUtil.makeKey(id)).collect(Collectors.toList());
						
						this.userService.addRolesToUser(workSession, user, insertedRoleKeys);
						this.userService.removeRolesFromUser(workSession, user, deletedRoleKeys);
					}
					
					this.userService.updateUser(workSession, user);
					workSession.commit();
					
					return new ModelAndView("redirect:/admin/users");
			    }			
				
			} else {
				return new ModelAndView("redirect:/admin/users");
			}
			
		}
	}
	
	@GetMapping("/admin/businesses")
	public String businesssesGet() {
		return "views/admin/businesses";
	}
}
