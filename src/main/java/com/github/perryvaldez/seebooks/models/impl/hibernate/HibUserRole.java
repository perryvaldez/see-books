package com.github.perryvaldez.seebooks.models.impl.hibernate;

import com.github.perryvaldez.seebooks.models.UserRole;
import com.github.perryvaldez.seebooks.models.types.KeyType;

public class HibUserRole implements UserRole {
	private static final long serialVersionUID = 20190223L;
	
	private KeyType userId;
	private KeyType roleId;

	@Override
	public KeyType getUserId() {
		return this.userId;
	}

	@Override
	public void setUserId(KeyType userId) {
        this.userId = userId;
	}

	@Override
	public KeyType getRoleId() {
		return this.roleId;
	}

	@Override
	public void setRoleId(KeyType roleId) {
        this.roleId = roleId;
	}
}
