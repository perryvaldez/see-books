package com.github.perryvaldez.seebooks.models.types;

import java.util.ArrayList;
import java.util.List;

import com.github.perryvaldez.seebooks.models.User;

public class UserWithRoles {
    private User user;
    private List<String> roles = new ArrayList<String>();
    
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public List<String> getRoles() {
		return roles;
	}
}
