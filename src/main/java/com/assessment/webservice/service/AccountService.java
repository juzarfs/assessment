package com.assessment.webservice.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.assessment.webservice.dao.AccountDao;
import com.assessment.webservice.domain.AccountDetails;
import com.assessment.webservice.domain.Transaction;

@Component
public class AccountService {

	@Inject
	private AccountDao accountDao;
	
	public List<AccountDetails> getAccountsByUsername(String username){
		return accountDao.getAccounts(username);
	}
	
	public List<Transaction> getTransactionsByAccountId(Long accountId){
		return accountDao.getTransactions(accountId);
	}
}
