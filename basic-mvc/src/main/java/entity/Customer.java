package entity;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class Customer {
	private int customerId;
	private String customerName;
	private double monthlyIncome;
	private String profession;
	private String designation;
	private String companyName;
	private Address address;
	private LocalDate dob;
	
	

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"customerId=" + customerId +
				", customerName='" + customerName + '\'' +
				", monthlyIncome=" + monthlyIncome +
				", profession='" + profession + '\'' +
				", designation='" + designation + '\'' +
				", companyName='" + companyName + '\'' +
				", address=" + address +
				", dob=" + dob +
				'}';
	}
}
