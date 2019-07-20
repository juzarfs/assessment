package com.assessment.webservice.dao;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;



import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
import com.assessment.webservice.db.mapper.TransactionRowMapper;
import com.assessment.webservice.domain.Account;
import com.assessment.webservice.domain.Account.AccountBuilder;
import com.assessment.webservice.domain.AccountDetails;
import com.assessment.webservice.domain.AccountDetails.AccountDetailsBuilder;
import com.assessment.webservice.domain.AccountType;
import com.assessment.webservice.domain.Amount;
import com.assessment.webservice.domain.Amount.AmountBuilder;
import com.assessment.webservice.domain.Transaction;
import com.assessment.webservice.domain.Transaction.TransactionBuilder;

public class AccountDaoTest {
	
	@InjectMocks
	AccountDaoImpl accountDao;
	
	@Mock
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Captor
	ArgumentCaptor<MapSqlParameterSource> sqlParamCaptor;
	
	@Mock
	AccountRowMapper accountRowMapper;
	
	@Mock
	TransactionRowMapper transactionRowMapper;
	
	
	private AccountDetails accountDetail;
	private Account account;
	private Transaction transaction;
	private Amount amount;
	private BigDecimal value = new BigDecimal("123.12");
	@Before
    public void setUp()  {
        MockitoAnnotations.initMocks(this);
        amount = AmountBuilder.builder()
        		.withCcy("AUD")
        		.withValue(value)
        		.build();
                account = AccountBuilder.builder()
        				.withAccountId(1l)
        				.withAccountName("accountName")
        				.withAccountNumber("123-1232-12321")
        				.build();
                accountDetail = AccountDetailsBuilder.builder()
                		.withAccount(account)
                		.withAccountType(AccountType.SAVINGS)
                		.withAccountBalance(amount)
                		.withLastUpdated(new Date())
                		.build();
                
                transaction = TransactionBuilder.builder()
                		.withAccount(account)
                		.withTransactionId(123l)
                		.withTransactionAmt(amount)
                		.withTransactionNarrative("narrative")
                		.withValueDate(new Date())
                		.build();
        	
	}
	
	@Test
	public void testGetAccounts(){
		when(namedParameterJdbcTemplate.query(eq(AccountDaoImpl.SELECT_ACCOUNTS_BY_ID), sqlParamCaptor.capture(),any(AccountRowMapper.class)))
		.thenReturn(Collections.singletonList(accountDetail));
		List<AccountDetails> accountDetails = accountDao.getAccounts("userName");
		assertNotNull(accountDetails);
		verify(namedParameterJdbcTemplate, times(1)).query(eq(AccountDaoImpl.SELECT_ACCOUNTS_BY_ID), sqlParamCaptor.capture(),any(AccountRowMapper.class));
		MapSqlParameterSource sqlParams = sqlParamCaptor.getAllValues().get(0);
		assertEquals("userName",sqlParams.getValue("user_name"));
	}
	
	@Test
	public void testGetTransactions(){
		when(namedParameterJdbcTemplate.query(eq(AccountDaoImpl.SELECT_TRANSACTION_BY_ACCOUNT_ID), sqlParamCaptor.capture(),any(TransactionRowMapper.class)))
		.thenReturn(Collections.singletonList(transaction));
		List<Transaction> transactions = accountDao.getTransactions(123l);
		assertNotNull(transactions);
		verify(namedParameterJdbcTemplate, times(1)).query(eq(AccountDaoImpl.SELECT_TRANSACTION_BY_ACCOUNT_ID), sqlParamCaptor.capture(),any(AccountRowMapper.class));
		MapSqlParameterSource sqlParams = sqlParamCaptor.getAllValues().get(0);
		assertEquals("123",sqlParams.getValue("account_id").toString());
	}
}
