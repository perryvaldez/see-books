package com.github.perryvaldez.seebooks.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger(IndexController.class);
	
	@GetMapping("/")
	public String indexGet(ModelMap model) {	
		model.addAttribute("myvar", "vall");
			
		return "views/index/index";
	}
}
