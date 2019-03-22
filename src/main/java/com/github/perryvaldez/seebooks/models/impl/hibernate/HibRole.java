package com.github.perryvaldez.seebooks.models.impl.hibernate;

import com.github.perryvaldez.seebooks.models.Role;
import com.github.perryvaldez.seebooks.models.types.KeyType;

public class HibRole implements Role {
    private KeyType id;
    private String name;
    
	@Override
	public KeyType getId() {
		return this.id;
	}

	@Override
	public void setId(KeyType id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
}
