package com.assessment.webservice.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.assessment.webservice.domain.Account.AccountBuilder;
import com.assessment.webservice.domain.AccountDetails;
import com.assessment.webservice.domain.AccountDetails.AccountDetailsBuilder;
import com.assessment.webservice.domain.AccountType;
import com.assessment.webservice.domain.Amount.AmountBuilder;

@Component
public class AccountRowMapper implements RowMapper<AccountDetails>{

	@Override
	public AccountDetails mapRow(ResultSet rs, int rownum) throws SQLException {
		return AccountDetailsBuilder.builder()
				.withAccount(AccountBuilder.builder()
						.withAccountNumber(rs.getString("account_number"))
						.withAccountName(rs.getString("account_name"))
						.withAccountId(rs.getLong("id"))
						.build())
				.withAccountBalance(AmountBuilder.builder()
						.withValue(rs.getBigDecimal("balance"))
						.withCcy(rs.getString("ccy"))
						.build())
				.withLastUpdated(rs.getDate("last_updated"))
				.withAccountType(AccountType.fromValue(rs.getString("account_type")))
				.build();
	}

}
