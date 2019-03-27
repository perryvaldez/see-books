package com.github.perryvaldez.seebooks.models.impl.hibernate;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.github.perryvaldez.seebooks.models.PermissionAction;
import com.github.perryvaldez.seebooks.models.PermissionObject;
import com.github.perryvaldez.seebooks.models.Privilege;
import com.github.perryvaldez.seebooks.models.Realm;
import com.github.perryvaldez.seebooks.models.Role;
import com.github.perryvaldez.seebooks.models.types.KeyType;
import com.github.perryvaldez.seebooks.models.types.impl.NumericKeyType;

@Entity
@Table(name = "tbl_privileges")
public class HibNumPrivilege implements Privilege {
	private static final long serialVersionUID = 20190326L;
	
	@Id 
	@Column(name = "id")
	private long numId;

	@Column(name = "owned_object_only")
	private boolean ownedObjectOnly;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "action_enum", nullable = false, foreignKey = @ForeignKey(name = "fk_perm_actions_privileges"))
    private HibNumPermissionAction action;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "object_id", nullable = false, foreignKey = @ForeignKey(name = "fk_perm_objects_privileges"))
    private HibNumPermissionObject object;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "realm_id", nullable = false, foreignKey = @ForeignKey(name = "fk_realms_privileges"))
    private HibNumRealm realm;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "role_id", nullable = false, foreignKey = @ForeignKey(name = "fk_roles_privileges"))
    private HibRole role;
	
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



	@Override
	public boolean isOwnedObjectOnly() {
		return this.ownedObjectOnly;
	}

	@Override
	public void setOwnedObjectOnly(boolean ownedObjectOnly) {
        this.ownedObjectOnly = ownedObjectOnly;
	}

	@Transient
	public long getNumRoleId() {
		return this.role.getNumId();
	}

	@Transient
	public void setNumRoleId(long numRoleId) {
	}

	@Transient
	public long getNumRealmId() {
		return this.realm.getNumId();
	}

	@Transient
	public void setNumRealmId(long numRealmId) {
	}

	@Transient
	public long getNumObjectId() {
		return this.object.getNumId();
	}

	@Transient
	public void setNumObjectId(long numObjectId) {
	}

	public long getNumId() {
		return numId;
	}

	public void setNumId(long numId) {
		this.numId = numId;
	}
	
	public HibNumPermissionAction getAction() {
		return action;
	}

	public void setAction(HibNumPermissionAction action) {
		this.action = action;
	}

	public HibNumPermissionObject getObject() {
		return object;
	}

	public void setObject(HibNumPermissionObject object) {
		this.object = object;
	}

	public HibNumRealm getRealm() {
		return realm;
	}

	public void setRealm(HibNumRealm realm) {
		this.realm = realm;
	}

	public HibRole getRole() {
		return role;
	}

	public void setRole(HibRole role) {
		this.role = role;
	}

	@Override
	public void setRole(Role role) {
		this.role = (HibRole) role;
		
	}

	@Override
	public void setRealm(Realm realm) {
		this.realm = (HibNumRealm) realm;
		
	}

	@Override
	public void setAction(PermissionAction action) {
		this.action = (HibNumPermissionAction) action;
		
	}

	@Override
	public void setObject(PermissionObject object) {
		this.object = (HibNumPermissionObject) object;
		
	}

	@Transient
	@Override
	public String serialize() {
		List<String> parts = new ArrayList<String>();
		parts.add(this.getRealm().getName());
		parts.add(this.getAction().getName());
		parts.add(this.getObject().getName());
		parts.add(this.isOwnedObjectOnly() ? "own" : "any");
		
		return String.join("/", parts);
	}
	
	@Override
	public String toString() {
		return this.serialize();
	}
}
