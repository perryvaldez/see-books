package com.github.perryvaldez.seebooks.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.perryvaldez.seebooks.datalayer.KeyUtilities;
import com.github.perryvaldez.seebooks.models.Role;
import com.github.perryvaldez.seebooks.models.User;
import com.github.perryvaldez.seebooks.models.types.KeyType;
import com.github.perryvaldez.seebooks.services.UserService;

@Controller
public class ApiController {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger(ApiController.class);
	
	private UserService userService;
	private KeyUtilities keyUtil;
	
	public ApiController(UserService userService, KeyUtilities keyUtil) {
		this.userService = userService;
		this.keyUtil = keyUtil;
	}
	
	@GetMapping("/api/users/{id}/roles")
	@ResponseBody
	public List<Map<String, String>> getUserRoles(@PathVariable String id) {
		List<Map<String, String>> roles = new ArrayList<Map<String, String>>();
		
		KeyType userId = this.keyUtil.makeKey(id);
		User user = this.userService.getUserById(userId);
		List<Role> roleList = this.userService.getUserRoles(user);
		
		roleList.stream().forEach(role -> {
			Map<String, String> entry = new HashMap<String, String>();
			entry.put(role.getId().serialize(), role.getName());
			roles.add(entry);
		});
		
		return roles;
	}

}
