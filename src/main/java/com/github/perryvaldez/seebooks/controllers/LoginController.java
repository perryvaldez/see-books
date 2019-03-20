package com.github.perryvaldez.seebooks.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @GetMapping("/login") 
    public String loginGet(@RequestParam(name = "error", required = false) String error, ModelMap model) {
    	model.addAttribute("loginError", (error != null));
    	
    	return "views/login/index";
    }
    
    @GetMapping("/loggedout")
    public String loggedOutGet() {
    	return "views/login/loggedout";
    }
}
