package com.assessment.webservice.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.assessment.webservice.db.mapper.AccountRowMapper;
import com.assessment.webservice.db.mapper.UserRowMapper;
import com.assessment.webservice.domain.User;
import com.assessment.webservice.domain.User.UserBuilder;

public class UserDaoTest {
	@InjectMocks
	UserDaoImpl userDao;
	
	@Mock
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Captor
	ArgumentCaptor<MapSqlParameterSource> sqlParamCaptor;
	
	@Mock
	UserRowMapper userRowMapper;
	
	private User user;
	
	@Before
    public void setUp()  {
        MockitoAnnotations.initMocks(this);
        user = UserBuilder.builder()
        		.withUserId(123l)
        		.withUserName("userName")
        		.build();
        user.setRoles(Collections.singletonList("ACCESS"));
        	
	}
	
	@Test
	public void testGetAccounts(){
		when(namedParameterJdbcTemplate.query(eq(UserDaoImpl.SELECT_USER_BY_USERNAME), sqlParamCaptor.capture(),any(UserRowMapper.class)))
		.thenReturn(Collections.singletonList(user));
		User user = userDao.getUserByUsername("userName");
		assertNotNull(user);
		verify(namedParameterJdbcTemplate, times(1)).query(eq(UserDaoImpl.SELECT_USER_BY_USERNAME), sqlParamCaptor.capture(),any(AccountRowMapper.class));
		MapSqlParameterSource sqlParams = sqlParamCaptor.getAllValues().get(0);
		assertEquals("userName",sqlParams.getValue("user_name"));
	}
	
	
	
}
