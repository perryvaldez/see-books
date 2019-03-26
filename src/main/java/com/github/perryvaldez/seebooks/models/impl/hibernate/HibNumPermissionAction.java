package com.github.perryvaldez.seebooks.models.impl.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.github.perryvaldez.seebooks.models.PermissionAction;

@Entity
@Table(name = "tbl_perm_actions")
public class HibNumPermissionAction implements PermissionAction {
	private static final long serialVersionUID = 20190326L;

    @Id
	@Column(name = "enum", nullable = false)
	private int ennum;
	
	@Column(name = "name", nullable = false, length = 20, unique = true)
	private String name;
	
	@Override
	public int getEnum() {
		return this.ennum;
	}

	@Override
	public void setEnum(int ennum) {
		this.ennum = ennum;	
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
