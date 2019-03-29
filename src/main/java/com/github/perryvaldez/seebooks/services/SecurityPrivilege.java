package com.github.perryvaldez.seebooks.services;

import java.io.Serializable;
import java.util.List;

public final class SecurityPrivilege implements Serializable {

	private static final long serialVersionUID = 20190328L;

	private final String realm;
	private final String action;
	private final String object;
	private final String owner;
	
	public SecurityPrivilege(String realm, String action, String object, String owner) {
		this.realm = realm;
		this.action = action;
		this.object = object;
		this.owner = owner;
	}
	
	public SecurityPrivilege(List<String> parts) {
		this.realm = parts.get(0);
		this.action = parts.get(1);
		this.object = parts.get(2);
		this.owner = parts.get(3);
	}	

	public SecurityPrivilege(String[] parts) {
		this.realm = parts[0];
		this.action = parts[1];
		this.object = parts[2];
		this.owner = parts[3];
	}	

	public String getRealm() {
		return realm;
	}
	
	public String getAction() {
		return action;
	}
	
	public String getObject() {
		return object;
	}
	
	public String getOwner() {
		return owner;
	}
	
	@Override
	public String toString() {
		return this.realm + ":" + this.action + ":" + this.object + ":" + this.owner;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SecurityPrivilege)) {
			return false;
		}
		
		return (this.toString() == obj.toString());
	}
	
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}
}
