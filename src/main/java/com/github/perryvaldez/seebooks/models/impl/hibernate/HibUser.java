package com.github.perryvaldez.seebooks.models.impl.hibernate;

import java.beans.Transient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.github.perryvaldez.seebooks.models.User;
import com.github.perryvaldez.seebooks.models.types.KeyType;
import com.github.perryvaldez.seebooks.models.types.impl.NumericKeyType;

@Entity
@Table(name = "tbl_users")
public class HibUser implements User {
	@Column(name = "email", nullable = false, length = 255, unique = true)
	private String email;
	
	@Column(name = "password", nullable = false, length = 60)
	private String password;

	@Id 
	@Column(name = "id")
	private long numId;

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
	public String getEmail() {
		return this.email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}
}
