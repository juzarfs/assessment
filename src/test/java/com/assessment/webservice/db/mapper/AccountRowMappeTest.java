package com.assessment.webservice.db.mapper;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.assessment.webservice.domain.AccountDetails;
import com.assessment.webservice.domain.AccountType;


public class AccountRowMappeTest {

	@Mock
	ResultSet resultSet;
	
	@InjectMocks
	private AccountRowMapper rowMapper;
	
	private BigDecimal balance = new BigDecimal("123.12"); 
	private Date now = new  Date();
	@Before
    public void setUp() throws SQLException  {
        MockitoAnnotations.initMocks(this);
        when(resultSet.getLong(eq("id"))).thenReturn(2l);
        when(resultSet.getString(eq("account_number"))).thenReturn("123-123");
        when(resultSet.getString(eq("account_name"))).thenReturn("name");
        when(resultSet.getBigDecimal(eq("balance"))).thenReturn(balance);
        when(resultSet.getString(eq("ccy"))).thenReturn("AUD");
        when(resultSet.getString(eq("account_type"))).thenReturn("Current");

	}
	
	@Test
	public void testMapRow() throws SQLException{
		AccountDetails entity = rowMapper.mapRow(resultSet, 0);
		assertNotNull(entity);
		assertEquals(AccountType.CURRENT,entity.getAccountType());
	}
}
