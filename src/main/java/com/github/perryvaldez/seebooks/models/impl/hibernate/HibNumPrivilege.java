package com.github.perryvaldez.seebooks.models.impl.hibernate;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.github.perryvaldez.seebooks.models.Privilege;
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
    private HibNumRealm role;
	
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
	
	@Transient
	@Override
	public int getActionEnum() {
		return this.action.getEnum();
	}

	@Transient
	@Override
	public void setActionEnum(int actionEnum) {
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

	public HibNumRealm getRole() {
		return role;
	}

	public void setRole(HibNumRealm role) {
		this.role = role;
	}
}
