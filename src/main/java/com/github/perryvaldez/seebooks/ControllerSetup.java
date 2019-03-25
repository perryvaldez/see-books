package com.github.perryvaldez.seebooks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class ControllerSetup {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger(ControllerSetup.class);
	
	@ModelAttribute
	public void initModel(HttpServletRequest request, Model model) {
		String currentUser = null;
		List<String> roles = new ArrayList<String>();
		
		var principal = request.getUserPrincipal();
		if(principal != null) {
			currentUser = principal.getName();
			
			@SuppressWarnings("unchecked")
			var grantedAuths = (Collection<SimpleGrantedAuthority>) SecurityContextHolder
					               .getContext().getAuthentication().getAuthorities();
			
			roles = grantedAuths.stream().map(auth -> auth.getAuthority()).collect(Collectors.toList()); 
		}
				
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("currentUserRoles", roles);
		model.addAttribute("_csrf", request.getAttribute("_csrf"));
	}
}
