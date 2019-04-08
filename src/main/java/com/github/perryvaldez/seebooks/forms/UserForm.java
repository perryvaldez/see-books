package com.github.perryvaldez.seebooks.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserForm implements FormPersistable {
    private String id;
    
    @Email(message = "Invalid email")
    private String email;
    
    @NotNull(message = "Password cannot be null")
    private String password;
    
    private String confirmPassword;
    
    private String serializedOrigValues;
    
    private List<String> roleIds = new ArrayList<String>();
    
    private Map<String, String> roleLookup = new HashMap<String, String>();
    
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String getSerializedOrigValues() {
		return this.serializedOrigValues;
	}

	@Override
	public void setSerializedOrigValues(String serializedOrigValues) {
		this.serializedOrigValues = serializedOrigValues;
	}

	public List<String> getRoleIds() {
		return this.roleIds;
	}

	public Map<String, String> getRoleLookup() {
		return roleLookup;
	}

}
