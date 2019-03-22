package com.github.perryvaldez.seebooks.config.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.github.perryvaldez.seebooks.models.User;

public class DbUserDetails implements UserDetails {
	private static final long serialVersionUID = 20190322L;

	private User user;
	private List<String> roleList;
	private PasswordEncoder encoder;
	
	public DbUserDetails(User user, List<String> roleList, PasswordEncoder encoder) {
		this.user = user;
		this.encoder = encoder;
		
		this.roleList = new ArrayList<String>();
		
		for(String role : roleList) {
			this.roleList.add(role);
		}
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		var list = this.roleList.stream()
				.map(auth -> new SimpleGrantedAuthority(auth))
				.collect(Collectors.toList());
		
		return list;
	}

	@Override
	public String getPassword() {
		return this.encoder.encode(this.user.getPassword());
	}

	@Override
	public String getUsername() {
	    return this.user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
