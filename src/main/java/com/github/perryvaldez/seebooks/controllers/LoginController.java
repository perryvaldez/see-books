package com.github.perryvaldez.seebooks.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginGet() {
    	return "views/login/index";
    }
}
