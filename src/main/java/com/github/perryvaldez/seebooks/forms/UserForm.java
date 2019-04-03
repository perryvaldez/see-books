package com.github.perryvaldez.seebooks.forms;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserForm {
    private String id;
    
    @Email(message = "Invalid email")
    private String email;
    
    @NotNull(message = "Password cannot be null")
    private String password;
    
    private String hashedPassword;
    private String confirmPassword;
    
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
