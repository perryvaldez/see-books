package com.github.perryvaldez.seebooks.models.impl.hibernate;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.github.perryvaldez.seebooks.models.Privilege;
import com.github.perryvaldez.seebooks.models.types.KeyType;
import com.github.perryvaldez.seebooks.models.types.impl.NumericKeyType;

@Entity
@Table(name = "public.tbl_privileges")
public class HibNumPrivilege implements Privilege {
	private static final long serialVersionUID = 20190326L;
	
	@Id 
	@Column(name = "id")
	private long numId;

	@Column(name = "role_id")
	private long numRoleId;
	
	@Column(name = "realm_id")
	private long numRealmId;

	@Column(name = "action_enum")
	private int actionEnum;

	@Column(name = "object_id")
	private long numObjectId;
	
	@Column(name = "owned_object_only")
	private boolean ownedObjectOnly;
	
	@Transient
	@Override
	public KeyType getId() {
		return new NumericKeyType(this.getNumId());
	}

	@Transient
	@Override
	public void setId(KeyType id) {
		var numKey = (NumericKeyType) id;
		this.setNumId(numKey.getValue());
	}

	@Transient
	@Override
	public KeyType getRoleId() {
		return new NumericKeyType(this.getNumRoleId());
	}

	@Transient
	@Override
	public void setRoleId(KeyType roleId) {
		var numKey = (NumericKeyType) roleId;
		this.setNumRoleId(numKey.getValue());
	}

	@Transient
	@Override
	public KeyType getRealmId() {
		return new NumericKeyType(this.getNumRealmId());
	}

	@Transient
	@Override
	public void setRealmId(KeyType realmId) {
		var numKey = (NumericKeyType) realmId;
		this.setNumRealmId(numKey.getValue());
	}

	@Transient
	@Override
	public KeyType getObjectId() {
		return new NumericKeyType(this.getNumObjectId());
	}

	@Transient
	@Override
	public void setObjectId(KeyType objectId) {
		var numKey = (NumericKeyType) objectId;
		this.setNumObjectId(numKey.getValue());
	}
	
	@Override
	public int getActionEnum() {
		return this.actionEnum;
	}

	@Override
	public void setActionEnum(int actionEnum) {
		this.actionEnum = actionEnum;
	}

	@Override
	public boolean isOwnedObjectOnly() {
		return this.ownedObjectOnly;
	}

	@Override
	public void setOwnedObjectOnly(boolean ownedObjectOnly) {
        this.ownedObjectOnly = ownedObjectOnly;
	}

	public long getNumRoleId() {
		return numRoleId;
	}

	public void setNumRoleId(long numRoleId) {
		this.numRoleId = numRoleId;
	}

	public long getNumRealmId() {
		return numRealmId;
	}

	public void setNumRealmId(long numRealmId) {
		this.numRealmId = numRealmId;
	}

	public long getNumObjectId() {
		return numObjectId;
	}

	public void setNumObjectId(long numObjectId) {
		this.numObjectId = numObjectId;
	}

	public long getNumId() {
		return numId;
	}

	public void setNumId(long numId) {
		this.numId = numId;
	}
}
