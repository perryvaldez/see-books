package com.github.perryvaldez.seebooks.forms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserFormValidator implements Validator {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LogManager.getLogger(UserFormValidator.class);
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UserForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserForm form = (UserForm) target;
 
		if(form == null || !form.getPassword().equals(form.getConfirmPassword())) {
			errors.reject("ERR_PASSWD_NOT_MATCH_CONFIRM", "You mistyped your password.");
		}
	}

}
