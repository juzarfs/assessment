package com.assessment.webservice.db.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.assessment.webservice.domain.User;

public class UserRowMappeTest {
	@Mock
	ResultSet resultSet;
	
	@InjectMocks
	private UserRowMapper rowMapper;
	

	@Before
    public void setUp() throws SQLException  {
        MockitoAnnotations.initMocks(this);
        when(resultSet.getLong(eq("id"))).thenReturn(2l);
        when(resultSet.getString(eq("user_name"))).thenReturn("name");
        when(resultSet.getString(eq("roles"))).thenReturn("ACCESS,UPDATE");
        
	}
	
	@Test
	public void testMapRow() throws SQLException{
		User entity = rowMapper.mapRow(resultSet, 0);
		assertNotNull(entity);
		assertEquals(2,entity.getRoles().size());
	}
}
