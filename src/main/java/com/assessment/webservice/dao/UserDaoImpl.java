package com.assessment.webservice.dao;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.assessment.webservice.db.mapper.UserRowMapper;
import com.assessment.webservice.domain.User;

@Component("userDaoImpl")
public class UserDaoImpl implements UserDao {

	@Inject
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Inject
	private UserRowMapper userRowMapper;
	 final static String SELECT_USER_BY_USERNAME = "select u.id, u.user_name, ur.roles from users u, user_roles ur  where  u.id=ur.user_id and u.user_name = :user_name";

	@Override
	public User getUserByUsername(String username) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("user_name", username);
		List<User>  entities = namedParameterJdbcTemplate.query(SELECT_USER_BY_USERNAME,param,userRowMapper);

		if (entities.size() > 0) {
			return entities.get(0);
		} else {
			return null;
		}
	}

}
