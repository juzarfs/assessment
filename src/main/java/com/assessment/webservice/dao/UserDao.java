package com.assessment.webservice.dao;

import com.assessment.webservice.domain.User;

public interface UserDao {

	User getUserByUsername(String username);
	
}
