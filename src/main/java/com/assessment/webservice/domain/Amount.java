package com.assessment.webservice.domain;

import java.math.BigDecimal;

public class Amount {
	String ccy;
	BigDecimal value ;

	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public String getCcy() {
		return ccy;
	}
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	
	public static final class AmountBuilder {
		private String ccy;
		private BigDecimal value;

		private AmountBuilder() {
		}

		public static AmountBuilder builder() {
			return new AmountBuilder();
		}
		
		public AmountBuilder withCcy(String ccy) {
			this.ccy = ccy;
			return this;
		}

		public AmountBuilder withValue(BigDecimal value) {
			this.value = value;
			return this;
		}

		public Amount build() {
			Amount amnt =  new Amount();
			amnt.setCcy(this.ccy);
			amnt.setValue(this.value);
			return amnt;
		}
	}
	
}
