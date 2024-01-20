package com.spring.dto;

import java.time.LocalDate;

public class LoanDto {
	private int customerId;
	private int loanId;
	private String productType;
	private String product;
	private double amount;
	private int tenure;
	private int rate;
	private LocalDate agreementDate;
	private LocalDate installmentDate;
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getLoanId() {
		return loanId;
	}
	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getTenure() {
		return tenure;
	}
	public void setTenure(int tenure) {
		this.tenure = tenure;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public LocalDate getAgreementDate() {
		return agreementDate;
	}
	public void setAgreementDate(LocalDate agreementDate) {
		this.agreementDate = agreementDate;
	}
	public LocalDate getInstallmentDate() {
		return installmentDate;
	}
	public void setInstallmentDate(LocalDate installmentDate) {
		this.installmentDate = installmentDate;
	}
	@Override
	public String toString() {
		return "LoanDto [customerId=" + customerId + ", loanId=" + loanId + ", productType=" + productType
				+ ", product=" + product + ", amount=" + amount + ", tenure=" + tenure + ", rate=" + rate
				+ ", agreementDate=" + agreementDate + ", installmentDate=" + installmentDate + "]";
	}
	
}
