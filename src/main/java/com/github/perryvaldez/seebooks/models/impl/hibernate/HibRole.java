package com.github.perryvaldez.seebooks.models.impl.hibernate;

import java.beans.Transient;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.github.perryvaldez.seebooks.models.Role;
import com.github.perryvaldez.seebooks.models.types.KeyType;
import com.github.perryvaldez.seebooks.models.types.impl.NumericKeyType;

@Entity
@Table(name = "tbl_roles")
public class HibRole implements Role {
	private static final long serialVersionUID = 20190223L;
	
	@Id 
	@Column(name = "id")
	private long numId;

	@Column(name = "name", nullable = false, length = 50, unique = true)
    private String name;
	
	@Transient
	@Override
	public KeyType getId() {
		return new NumericKeyType(this.numId);
	}

	@Transient
	@Override
	public void setId(KeyType id) {
		var numKey = (NumericKeyType) id;
		this.numId = numKey.getValue();
	}
	
	public long getNumId() {
		return this.numId;
	}
	
	public void setNumId(long numId) {
		this.numId = numId;
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
