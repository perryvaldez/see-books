package com.github.perryvaldez.seebooks;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class ControllerSetup {

	@ModelAttribute
	public void initModel(HttpServletRequest request, Model model) {
		String currentUser = null;
		
		var principal = request.getUserPrincipal();
		if(principal != null) {
			currentUser = principal.getName();
		}
		
		model.addAttribute("currentUser", currentUser);
	}
}
