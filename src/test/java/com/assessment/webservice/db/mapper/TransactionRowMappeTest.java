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

import com.assessment.webservice.domain.AccountType;
import com.assessment.webservice.domain.Transaction;

public class TransactionRowMappeTest {
	@Mock
	ResultSet resultSet;
	
	@InjectMocks
	private TransactionRowMapper rowMapper;
	
	private BigDecimal balance = new BigDecimal("123.12"); 

	@Before
    public void setUp() throws SQLException  {
        MockitoAnnotations.initMocks(this);
        when(resultSet.getLong(eq("id"))).thenReturn(2l);
        when(resultSet.getLong(eq("account_id"))).thenReturn(2l);
        when(resultSet.getString(eq("account_number"))).thenReturn("123-123");
        when(resultSet.getString(eq("account_name"))).thenReturn("name");
        when(resultSet.getBigDecimal(eq("transaction_amnt"))).thenReturn(balance);
        when(resultSet.getString(eq("ccy"))).thenReturn("AUD");
        when(resultSet.getString(eq("transaction_narrative"))).thenReturn("online");

	}
	
	@Test
	public void testMapRow() throws SQLException{
		Transaction entity = rowMapper.mapRow(resultSet, 0);
		assertNotNull(entity);
		assertEquals("online",entity.getTransactionNarrative());
	}

}
