package com.assessment.webservice.domain;

import java.util.Date;

import com.assessment.webservice.domain.AccountDetails.AccountDetailsBuilder;

public class Transaction {
	private Account account;
	private Long transactionId;
	private String transactionNarrative;
	private Amount transactionAmt;
	private Date valueDate;

	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}

	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionNarrative() {
		return transactionNarrative;
	}
	public void setTransactionNarrative(String transactionNarrative) {
		this.transactionNarrative = transactionNarrative;
	}
	public Amount getTransactionAmt() {
		return transactionAmt;
	}
	public void setTransactionAmt(Amount transactionAmt) {
		this.transactionAmt = transactionAmt;
	}
	public Date getValueDate() {
		return valueDate;
	}
	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}
	
	public static final class TransactionBuilder {
		private Account account;
		private Long transactionId;
		private String transactionNarrative;
		private Amount transactionAmt;
		private Date valueDate;

		private TransactionBuilder() {
		}

		public static TransactionBuilder builder() {
			return new TransactionBuilder();
		}

		public TransactionBuilder withAccount(Account account) {
			this.account = account;
			return this;
		}
		
		public TransactionBuilder withTransactionId(Long transactionId) {
			this.transactionId = transactionId;
			return this;
		}

		public TransactionBuilder withTransactionNarrative(String transactionNarrative) {
			this.transactionNarrative = transactionNarrative;
			return this;
		}

		public TransactionBuilder withTransactionAmt(Amount transactionAmt) {
			this.transactionAmt = transactionAmt;
			return this;
		}

		public TransactionBuilder withValueDate(Date valueDate) {
			this.valueDate = valueDate;
			return this;
		}

		public Transaction build() {
			Transaction transaction =  new Transaction();
			transaction.setAccount(this.account);
			transaction.setTransactionAmt(this.transactionAmt);
			transaction.setTransactionId(this.transactionId);
			transaction.setTransactionNarrative(this.transactionNarrative);
			transaction.setValueDate(this.valueDate);
			return transaction;
		}
	}
	
	
	
}
