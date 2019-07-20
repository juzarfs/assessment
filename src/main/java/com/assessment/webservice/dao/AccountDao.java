package com.assessment.webservice.dao;

import java.util.List;

import com.assessment.webservice.domain.AccountDetails;
import com.assessment.webservice.domain.Transaction;

public interface AccountDao {
	
	List<AccountDetails> getAccounts(String username);
	List<Transaction> getTransactions(Long accountId);

}
