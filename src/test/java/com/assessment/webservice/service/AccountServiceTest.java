package com.assessment.webservice.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.assessment.webservice.dao.AccountDao;
import com.assessment.webservice.domain.Account;
import com.assessment.webservice.domain.Account.AccountBuilder;
import com.assessment.webservice.domain.AccountDetails;
import com.assessment.webservice.domain.AccountDetails.AccountDetailsBuilder;
import com.assessment.webservice.domain.AccountType;
import com.assessment.webservice.domain.Amount;
import com.assessment.webservice.domain.Amount.AmountBuilder;
import com.assessment.webservice.domain.Transaction;
import com.assessment.webservice.domain.Transaction.TransactionBuilder;

public class AccountServiceTest {
	@InjectMocks
	AccountService accountService;
	
	@Mock
	AccountDao accountDoa;
	
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
	public void testGetAccountsByUsername(){
		when(accountDoa.getAccounts(anyString())).thenReturn(Collections.singletonList(accountDetail));
		List<AccountDetails> result = accountService.getAccountsByUsername("test");
		assertNotNull(result);
		verify(accountDoa,times(1)).getAccounts(eq("test"));
	}

	@Test
	public void testGetTransactionsByAccountId(){
		when(accountDoa.getTransactions(any(Long.class))).thenReturn(Collections.singletonList(transaction));
		List<Transaction> result = accountService.getTransactionsByAccountId(123l);
		assertNotNull(result);
		verify(accountDoa,times(1)).getTransactions(any(Long.class));
	}
}
