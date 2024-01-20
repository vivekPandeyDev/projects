package com.spring.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddressDto {
	private int houseNo;
	@NotNull(message = "country cannot be null")
	private String country;
	@NotNull(message = "state cannot be null")
	private String state;
	@NotNull(message = "city cannot be null")
	private String city;
	@NotBlank(message = "address line 1 cannot be null")
	private String addressLine1;
	@NotBlank(message = "address line 2 cannot be null")
	private String addressLine2;

	public int getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	@Override
	public String toString() {
		return "AddressDto [houseNo=" + houseNo + ", country=" + country + ", state=" + state + ", city=" + city
				+ ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + "]";
	}

}
