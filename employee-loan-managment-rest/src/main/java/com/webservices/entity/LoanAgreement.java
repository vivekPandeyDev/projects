package com.webservices.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.webservices.enums.LoanStatus;
import com.webservices.formatter.LocalDateDeserializer;
import com.webservices.formatter.LocalDateSerializer;


@NamedQueries({
@NamedQuery(query = "Select loanAgreement from LoanAgreement loanAgreement where loanAgreement.loanStatus='PENDING'", name = "findLoanStatus"),
})
@NamedNativeQueries({
@NamedNativeQuery(query = "Select * from vivek_jpa_loanAgreement where CUSTOMER_CUSTOMERNAME=?", name = "findLoanByCustomerId"),
@NamedNativeQuery(query = "Select * from vivek_jpa_loanAgreement having count(CUSTOMER_CUSTOMERNAME=?)>=1", name = "findCustomerByLoan"),
@NamedNativeQuery(query = "Select LOANDISBURSALDATE from vivek_jpa_loanAgreement where CUSTOMER_CUSTOMERNAME=?", name = "findDateByCustomerId")
})
@Entity
@Table(name = "vivek_jpa_loanAgreement")
public class LoanAgreement {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int loanAgreementId;
	@OneToOne(cascade = CascadeType.ALL)
	private LoanProduct loanProduct;
	private double loanAmount;
	private int tenure;
	private double roi;
	private LoanStatus loanStatus;
	private double emiPerMonth;
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate loanDisbursalDate;
	private int repaymentFrequency;

	@ManyToOne
	@JsonBackReference
	private Customer customer;

	public LoanAgreement(LoanProduct loanProduct, double loanAmount, int tenure,
			double roi, LoanStatus loanStatus, LocalDate loanDisbursalDate,
			int repaymentFrequency) {
		this.loanProduct = loanProduct;
		this.loanAmount = loanAmount;
		this.tenure = tenure;
		this.roi = roi;
		this.loanStatus = loanStatus;
		this.loanDisbursalDate = loanDisbursalDate;
		this.repaymentFrequency = repaymentFrequency;
		this.emiPerMonth = calculateEMI();
	}

	public double calculateEMI() {
		int tenureInMonth = tenure * 12;
		double ratePerMonth = (roi / (1200));
		double usable = Math.pow(1 + ratePerMonth, tenureInMonth);
		return loanAmount * ratePerMonth * usable / (usable - 1);
	}

	public LoanAgreement() {

	}

	public int getLoanAgreementId() {
		return loanAgreementId;
	}

	public LoanProduct getLoanProduct() {
		return loanProduct;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public int getTenure() {
		return tenure;
	}

	public double getRoi() {
		return roi;
	}

	public LoanStatus getLoanStatus() {
		return loanStatus;
	}

	public double getEmiPerMonth() {
		return emiPerMonth;
	}

	public LocalDate getLoanDisbursalDate() {
		return loanDisbursalDate;
	}

	public int getRepaymentFrequency() {
		return repaymentFrequency;
	}

	public void setLoanAgreementId(int loanAgreementId) {
		this.loanAgreementId = loanAgreementId;
	}

	public void setLoanProduct(LoanProduct loanProduct) {
		this.loanProduct = loanProduct;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	public void setRoi(double roi) {
		this.roi = roi;
	}

	public void setLoanStatus(LoanStatus loanStatus) {
		this.loanStatus = loanStatus;
	}

	public void setEmiPerMonth(double emiPerMonth) {
		this.emiPerMonth = emiPerMonth;
	}

	public void setLoanDisbursalDate(LocalDate loanDisbursalDate) {
		this.loanDisbursalDate = loanDisbursalDate;
	}

	public void setRepaymentFrequency(int repaymentFrequency) {
		this.repaymentFrequency = repaymentFrequency;
	}

	public Customer getCustomer() {
		return customer;
	}

	public LoanAgreement setCustomer(Customer customer) {
		this.customer = customer;
		return this;
	}

	@Override
	public String toString() {
		return "LoanAgreement [loanAgreementId=" + loanAgreementId
				+ ", loanProduct=" + loanProduct + ", loanAmount=" + loanAmount
				+ ", tenure=" + tenure + ", roi=" + roi + ", loanStatus="
				+ loanStatus + ", emiPerMonth=" + emiPerMonth
				+ ", loanDisbursalDate=" + loanDisbursalDate
				+ ", repaymentFrequency=" + repaymentFrequency + "]";
	}

}
