package com.assessment.webservice.resource;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.assessment.webservice.domain.Account.AccountBuilder;
import com.assessment.webservice.domain.AccountDetails;
import com.assessment.webservice.domain.AccountDetails.AccountDetailsBuilder;
import com.assessment.webservice.domain.AccountType;
import com.assessment.webservice.domain.Amount.AmountBuilder;
import com.assessment.webservice.domain.Transaction;
import com.assessment.webservice.security.model.UserDetailsImpl;
import com.assessment.webservice.service.AccountService;
import com.assessment.webservice.service.SecurityService;


public class AccountResourceTest {

	@InjectMocks
	AccountResource accountResource;
	
	@Mock
	AccountService accountService;
	
	@Mock
	SecurityService securityService;
	
	private AccountDetails accountDetail;
	private BigDecimal value = new BigDecimal("123.12");
	private UserDetailsImpl userdetails = new UserDetailsImpl("test",1l,new ArrayList());
	
	@Before
    public void setUp()  {
        MockitoAnnotations.initMocks(this);
        accountDetail = AccountDetailsBuilder.builder()
        		.withAccount(AccountBuilder.builder()
        				.withAccountId(1l)
        				.withAccountName("accountName")
        				.withAccountNumber("123-1232-12321")
        				.build())
        		.withAccountType(AccountType.SAVINGS)
        		.withAccountBalance(AmountBuilder.builder()
        				.withCcy("AUD")
        				.withValue(value)
        				.build())
        		.withLastUpdated(new Date())
        		.build();
    }
	
	@Test
	public void testFetchAccountTransactions() {
		when(securityService.getUserDetails()).thenReturn(userdetails);
		when(accountService.getAccountsByUsername(any())).thenReturn(Collections.singletonList(accountDetail));
		when(accountService.getTransactionsByAccountId(any(Long.class))).thenReturn(Collections.EMPTY_LIST);
		Response result = accountResource.fetchAccountTransactions(1L);
		List<Transaction> entity = (List<Transaction>) result.getEntity(); 
		assertEquals(entity.size(),0);
		assertEquals(200, result.getStatus());
	}
	
	@Test
	public void testFetchAccountTransactionsForOtherUser403(){
		when(securityService.getUserDetails()).thenReturn(userdetails);
		when(accountService.getAccountsByUsername(any())).thenReturn(Collections.EMPTY_LIST);
		Response result = accountResource.fetchAccountTransactions(123L);
		assertEquals(403, result.getStatus());
	}
}
