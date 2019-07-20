package com.assessment.webservice.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.StringUtils;

public class User {
	private Long userId;
	private String userName;
	private List<String> roles;

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<String> getRoles() {
		return roles;
	}
	
	public void setRoles(String roles) {
		
		if(StringUtils.isEmpty(roles)) {
			this.roles= new ArrayList<>();
		} else {
			this.roles = Arrays.stream(roles.split(",")).map(String::trim).collect(Collectors.toList());
		}
	}
	
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	public static final class UserBuilder {
		private Long userId;
		private String userName;


		public static UserBuilder builder() {
			return new UserBuilder();
		}
		
		private UserBuilder() {
		}

		public UserBuilder withUserId(Long userId) {
			this.userId = userId;
			return this;
		}

		public UserBuilder withUserName(String userName) {
			this.userName = userName;
			return this;
		}
		
		public User build() {
			User user =  new User();
			user.setUserId(this.userId);
			user.setUserName(this.userName);
			return user;
		}
	}

	
}
