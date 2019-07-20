package com.assessment.webservice.service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityService {

	public UserDetails getUserDetails(){
		UserDetails loggedInUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (loggedInUser == null) {
			throw new AccessDeniedException("Principal is null.");
		}
		return loggedInUser;
	}
}
