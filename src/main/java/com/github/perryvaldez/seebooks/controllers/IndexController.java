package com.github.perryvaldez.seebooks.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	@GetMapping("/")
	public String indexGet(ModelMap model) {
		model.addAttribute("myvar", "myval");
		return "views/index/index";
	}
}
