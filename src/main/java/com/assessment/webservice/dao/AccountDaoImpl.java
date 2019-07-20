package com.assessment.webservice.dao;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.assessment.webservice.db.mapper.AccountRowMapper;
import com.assessment.webservice.db.mapper.TransactionRowMapper;
import com.assessment.webservice.domain.AccountDetails;
import com.assessment.webservice.domain.Transaction;

@Component("accountDaoImpl")
public class AccountDaoImpl implements AccountDao {

	@Inject
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Inject
	private AccountRowMapper accountRowMapper;
	
	@Inject
	private TransactionRowMapper transactionRowMapper;
	
	  static final String SELECT_ACCOUNTS_BY_ID = "select a.id, a.account_name, a.account_number, a.account_type, a.balance, a.ccy, last_updated "
			+ "from accounts a, user_accounts ua, users u   where  u.id = ua.user_id and a.id=ua.account_id and u.user_name = :user_name";
	
	  static final String SELECT_TRANSACTION_BY_ACCOUNT_ID = "select a.id  as account_id, a.account_number , a.account_name, t.id, t.transaction_amnt, t.transaction_narrative, t.transaction_type, t.ccy, t.value_date from transactions t , account_transactions at , accounts a "
			 + "where t.id=at.transaction_id  and at.account_id=a.id and a.id = :account_id";
	
	@Override
	public List<AccountDetails> getAccounts(String username) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("user_name", username);
		List<AccountDetails>  entities = namedParameterJdbcTemplate.query(SELECT_ACCOUNTS_BY_ID,param,accountRowMapper);

		return entities;
	}


	@Override
	public List<Transaction> getTransactions(Long accountId) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("account_id", accountId);
		List<Transaction>  entities = namedParameterJdbcTemplate.query(SELECT_TRANSACTION_BY_ACCOUNT_ID,param,transactionRowMapper);

		return entities;
	}

}
