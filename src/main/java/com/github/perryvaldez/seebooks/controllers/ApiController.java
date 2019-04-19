package com.github.perryvaldez.seebooks.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.perryvaldez.seebooks.datalayer.KeyUtilities;
import com.github.perryvaldez.seebooks.models.Role;
import com.github.perryvaldez.seebooks.models.User;
import com.github.perryvaldez.seebooks.models.types.KeyType;
import com.github.perryvaldez.seebooks.models.types.KeyValuePair;
import com.github.perryvaldez.seebooks.services.RoleService;
import com.github.perryvaldez.seebooks.services.UserService;

@Controller
public class ApiController {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger(ApiController.class);
	
	private UserService userService;
	private RoleService roleService;
	private KeyUtilities keyUtil;
	
	public ApiController(UserService userService, RoleService roleService, KeyUtilities keyUtil) {
		this.userService = userService;
		this.keyUtil = keyUtil;
		this.roleService = roleService;
	}
	
	@GetMapping("/api/users/{id}/roles")
	@ResponseBody
	public List<KeyValuePair<String, String>> getUserRoles(@PathVariable String id) {
		List<KeyValuePair<String, String>> roles = new ArrayList<KeyValuePair<String, String>>();
		
		KeyType userId = this.keyUtil.makeKey(id);
		User user = this.userService.getUserById(userId);
		List<Role> roleList = this.userService.getUserRoles(user);
		
		roleList.stream().forEach(role -> {
			KeyValuePair<String, String> pair = new KeyValuePair<String, String>(role.getId().serialize(), role.getName());
			roles.add(pair);
		});
		
		return roles;
	}

	@GetMapping("/api/roles")   // /api/roles?exceptids=10,12,16,22
	@ResponseBody
	public List<KeyValuePair<String, String>> getAvailableRoles(@RequestParam(name = "exceptids", required = false) String exceptIds) {
		List<KeyValuePair<String, String>> roles = new ArrayList<KeyValuePair<String, String>>();
		
        String[] ids = new String[0];
        
        if (exceptIds != null) {
            ids = exceptIds.split("\\s*,\\s*");
        }
        
        List<KeyType> idList = Arrays.asList(ids).stream()
        		.map(id -> this.keyUtil.makeKey(id))
        		.collect(Collectors.toList())
        		; 
		
        List<Role> roleList = this.roleService.getRolesExceptIds(idList);
        
        roles = roleList.stream()
        		.map(role -> new KeyValuePair<String, String>(role.getId().serialize(), role.getName()))
        		.collect(Collectors.toList())
        		;
        
		return roles;
	}
}
