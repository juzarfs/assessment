package com.assessment.webservice.domain;

public enum AccountType {

	CURRENT("Current"),
	SAVINGS("Savings");
	
	private String value;
	
	private AccountType(String value) {
		this.value=value;
 	}
	
	@Override
	public String toString(){
		return String.valueOf(value);
	}
	
	public static AccountType fromValue(String text) {
		for (AccountType a : AccountType.values()) {
			if(String.valueOf(a.value).equals(text)) {
				return a;
			}
		}
		return null;
	}
	
}
