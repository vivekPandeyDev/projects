package com.brd.dto;

import java.time.LocalDate;

import com.brd.enums.CustomerFlag;
import com.brd.enums.RecordStatus;

public class FileUploadCustomerDto {
	private String customerCode;
	private String customerName;
	private String address1;
	private String address2;
	private int pinCode;
	private String email;
	private long contactNumber;
	private String contactPerson;
	private RecordStatus status;
	private CustomerFlag flag;
	private LocalDate createDate;
	private String createdBy;
	private LocalDate modifiedDate;
	private String modifiedBy;
	private LocalDate authorizedDate;
	private String authorizedBy;
	private boolean isAccepted;
	private String message;

	public FileUploadCustomerDto() {
		super();

	}

	public FileUploadCustomerDto(String customerCode, String customerName, String address1, int pinCode, String email,
			String contactPerson, RecordStatus status, CustomerFlag flag, LocalDate createDate, String createdBy) {
		this.customerCode = customerCode;
		this.customerName = customerName;
		this.address1 = address1;
		this.pinCode = pinCode;
		this.email = email;
		this.contactPerson = contactPerson;
		this.status = status;
		this.flag = flag;
		this.createDate = createDate;
		this.createdBy = createdBy;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public RecordStatus getStatus() {
		return status;
	}

	public void setStatus(RecordStatus status) {
		this.status = status;
	}

	public CustomerFlag getFlag() {
		return flag;
	}

	public void setFlag(CustomerFlag flag) {
		this.flag = flag;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDate getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDate getAuthorizedDate() {
		return authorizedDate;
	}

	public void setAuthorizedDate(LocalDate authorizedDate) {
		this.authorizedDate = authorizedDate;
	}

	public String getAuthorizedBy() {
		return authorizedBy;
	}

	public void setAuthorizedBy(String authorizedBy) {
		this.authorizedBy = authorizedBy;
	}

	public boolean getIsAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "FileUploadCustomerDto [customerCode=" + customerCode + ", customerName=" + customerName + ", address1="
				+ address1 + ", address2=" + address2 + ", pinCode=" + pinCode + ", email=" + email + ", contactNumber="
				+ contactNumber + ", contactPerson=" + contactPerson + ", status=" + status + ", flag=" + flag
				+ ", createDate=" + createDate + ", createdBy=" + createdBy + ", modifiedDate=" + modifiedDate
				+ ", modifiedBy=" + modifiedBy + ", authorizedDate=" + authorizedDate + ", authorizedBy=" + authorizedBy
				+ ", isAccepted=" + isAccepted + ", message=" + message + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		FileUploadCustomerDto customer = (FileUploadCustomerDto) o;

		if (!customerCode.equals(customer.customerCode))
			return false;
		if (!customerName.equals(customer.customerName))
			return false;
		return email.equals(customer.email);
	}

	@Override
	public int hashCode() {
		int result = customerCode.hashCode();
		result = 31 * result + customerName.hashCode();
		result = 31 * result + email.hashCode();
		return result;
	}

}
