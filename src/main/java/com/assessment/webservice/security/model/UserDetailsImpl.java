package com.assessment.webservice.security.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {
	private String username;
	private Long userId;
	private List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
	
	public UserDetailsImpl(String username, Long userId, List<GrantedAuthority> grantedAuthorities) {
		super();
		this.username=username;
		this.userId=userId;
		this.grantedAuthorities=grantedAuthorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return null;
	}
	
	public Long getUserId() {
		return this.userId;
	}

	@Override
	public String getUsername() {
		return this.username;
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
