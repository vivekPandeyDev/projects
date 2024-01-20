package dto;

import java.time.LocalDate;
import java.util.Random;

public class CustomerDto {
	private int customerId;
	private String customerName;
	private double monthlyIncome;
	private String profession;
	private String designation;
	private String companyName;

	private LocalDate  dob;


	public CustomerDto() {
		this.customerId = new Random().nextInt(1000);
	}

	public LocalDate getDob() {
		return dob;
	}

	public CustomerDto setDob(LocalDate dob) {
		this.dob = dob;
		return this;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public double getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "CustomerDto [customerId=" + customerId + ", customerName=" + customerName + ", monthlyIncome="
				+ monthlyIncome + ", profession=" + profession + ", designation=" + designation + ", companyName="
				+ companyName + ", " + dob+ "]";
	}

}
