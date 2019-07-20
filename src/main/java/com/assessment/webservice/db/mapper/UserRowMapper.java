package com.assessment.webservice.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.assessment.webservice.domain.User;
import com.assessment.webservice.domain.User.UserBuilder;

@Component
public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rownum) throws SQLException {
		User user =  UserBuilder.builder()
				.withUserId(rs.getLong("id"))
				.withUserName(rs.getString("user_name"))
				.build();
		user.setRoles(rs.getString("roles"));
		return user;
	}

}
