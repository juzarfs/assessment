package com.assessment.webservice.security;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.assessment.webservice.dao.UserDao;
import com.assessment.webservice.domain.User;
import com.assessment.webservice.security.model.UserDetailsImpl;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Inject
	UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User domainUser = userDao.getUserByUsername(username);
		if(domainUser == null) {
			throw new UsernameNotFoundException("Failed to find user:["+username+"]");
		}
		List<GrantedAuthority> authorities = populateGrantedAuthorities(domainUser.getRoles());
		UserDetails principal = new UserDetailsImpl(domainUser.getUserName(),domainUser.getUserId(),authorities);
		return principal;
	}

	private List<GrantedAuthority> populateGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String role : roles) {
			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
			authorities.add(authority);
		}
		return authorities;
	}
}
