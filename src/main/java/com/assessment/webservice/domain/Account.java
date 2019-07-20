package com.assessment.webservice.domain;

public class Account {

	private Long accountId;
	private String accountName;
	private String accountNumber;
	
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public static final class AccountBuilder {
		private Long accountId;
		private String accountName;
		private String accountNumber;

		private AccountBuilder() {
		}
		
		public static AccountBuilder builder() {
			return new AccountBuilder();
		}
		
		public AccountBuilder withAccountId(Long accountId) {
			this.accountId = accountId;
			return this;
		}

		public AccountBuilder withAccountName(String accountName) {
			this.accountName = accountName;
			return this;
		}

		public AccountBuilder withAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
			return this;
		}

		public Account build() {
			Account account =  new Account();
			account.setAccountId(this.accountId);
			account.setAccountName(this.accountName);
			account.setAccountNumber(this.accountNumber);
			return account;
		}
	}
	
}
