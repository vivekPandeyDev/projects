package com.webservices.entity;

import java.io.Serializable;
import java.util.Objects;

public class CustomerCompositeKey implements Serializable {
	private static final long serialVersionUID = 2306079248052876183L;
	private String customerName;
	private int customerId;
	public String getCustomerName() {
		return customerName;
	}
	public int getCustomerId() {
		return customerId;
	}
	public CustomerCompositeKey setCustomerName(String customerName) {
		this.customerName = customerName;
		return this;
	}
	public CustomerCompositeKey setCustomerId(int customerId) {
		this.customerId = customerId;
		return this;
	}
	@Override
	public int hashCode() {
		return Objects.hash(customerId, customerName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerCompositeKey other = (CustomerCompositeKey) obj;
		return customerId == other.customerId
				&& Objects.equals(customerName, other.customerName);
	}

}
