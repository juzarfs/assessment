package com.assessment.webservice.domain;

import java.util.Date;

public class AccountDetails {
	private Account account;
	private Amount accountBalance;
	private AccountType accountType;
	private Date lastUpdated;

	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Amount getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(Amount accountBalance) {
		this.accountBalance = accountBalance;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	public static final class AccountDetailsBuilder {
		private Account account;
		private Amount accountBalance;
		private AccountType accountType;
		private Date lastUpdated;

		private AccountDetailsBuilder() {
		}

		public static AccountDetailsBuilder builder() {
			return new AccountDetailsBuilder();
		}
		
		public AccountDetailsBuilder withAccount(Account account) {
			this.account = account;
			return this;
		}

		public AccountDetailsBuilder withAccountBalance(Amount accountBalance) {
			this.accountBalance = accountBalance;
			return this;
		}

		public AccountDetailsBuilder withAccountType(AccountType accountType) {
			this.accountType = accountType;
			return this;
		}

		public AccountDetailsBuilder withLastUpdated(Date lastUpdated) {
			this.lastUpdated = lastUpdated;
			return this;
		}

		public AccountDetails build() {
			AccountDetails accnt =  new AccountDetails();
			accnt.setAccount(this.account);
			accnt.setAccountBalance(this.accountBalance);
			accnt.setAccountType(this.accountType);
			accnt.setLastUpdated(this.lastUpdated);
			return accnt;
		}
	}
}
