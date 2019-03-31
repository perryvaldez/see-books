package com.github.perryvaldez.seebooks;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class ControllerSetup {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger(ControllerSetup.class);
	
	@Value("${build.timestamp}")
	private String buildTimestamp;
	
	@ModelAttribute
	public void initModel(HttpServletRequest request, Model model) {
		String tstamp = this.buildTimestamp.replaceAll("[:-]", "");
		
		LOGGER.info("==== Build timestamp: " + tstamp);
		String currentUser = null;
		
		var principal = request.getUserPrincipal();
		if(principal != null) {
			currentUser = principal.getName();
		}

		model.addAttribute("currentUser", currentUser);
		model.addAttribute("_csrf", request.getAttribute("_csrf"));
		model.addAttribute("buildTimestamp", tstamp);
	}
}
