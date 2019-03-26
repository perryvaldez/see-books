package com.github.perryvaldez.seebooks.models.impl.hibernate;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.github.perryvaldez.seebooks.models.PermissionObject;
import com.github.perryvaldez.seebooks.models.types.KeyType;
import com.github.perryvaldez.seebooks.models.types.impl.NumericKeyType;

@Entity
@Table(name = "tbl_perm_objects")
public class HibNumPermissionObject implements PermissionObject {
	private static final long serialVersionUID = 20190326L;
	
	@Id 
	@Column(name = "id")
	private long numId;
	
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
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
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public long getNumId() {
		return numId;
	}

	public void setNumId(long numId) {
		this.numId = numId;
	}
}
