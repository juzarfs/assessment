package com.assessment.webservice.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.assessment.webservice.domain.Account.AccountBuilder;
import com.assessment.webservice.domain.AccountType;
import com.assessment.webservice.domain.Amount.AmountBuilder;
import com.assessment.webservice.domain.Transaction;
import com.assessment.webservice.domain.Transaction.TransactionBuilder;

@Component
public class TransactionRowMapper implements RowMapper<Transaction>{

	@Override
	public Transaction mapRow(ResultSet rs, int rownum) throws SQLException {
		return TransactionBuilder.builder()
				.withTransactionId(rs.getLong("id"))
				.withAccount(AccountBuilder.builder()
						.withAccountId(rs.getLong("account_id"))
						.withAccountNumber(rs.getString("account_number"))
						.withAccountName(rs.getString("account_name"))
						.build())
				.withTransactionAmt(AmountBuilder.builder()
						.withValue(rs.getBigDecimal("transaction_amnt"))
						.withCcy(rs.getString("ccy"))
						.build())
				.withTransactionNarrative(rs.getString("transaction_narrative"))
				.withValueDate(rs.getDate("value_date"))
				.build();
	}

}
