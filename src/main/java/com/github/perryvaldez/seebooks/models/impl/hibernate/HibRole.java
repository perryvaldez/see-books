package com.github.perryvaldez.seebooks.models.impl.hibernate;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.github.perryvaldez.seebooks.models.Role;

@Entity
@Table(name = "tbl_roles")
public class HibRole implements Role {
	private static final long serialVersionUID = 20190223L;
	
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
	
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
                },
            mappedBy = "roles")
	private Set<HibUser> users = new HashSet<>();
}
