package com.brd.validation;

import com.brd.dto.FileUploadCustomerDto;
import com.brd.exception.CustomerException;

public class CustomerValidation {

	private CustomerValidation() {
	}

	public static boolean isAlphaNumeric(String name) {
		if (name.isBlank())
			return false;
		for (char c : name.toCharArray()) {
			if (!(Character.isLetter(c) || Character.isDigit(c) || c == ' '))
				return false;

		}
		return true;
	}

	public static void validCustomer(FileUploadCustomerDto customer) throws CustomerException {
		if (!Validate.validateEmail(customer.getEmail())) {
			throw new CustomerException("email not valid", new NumberFormatException());
		} else if ((String.valueOf(customer.getPinCode()).length() <= 5)) {
			throw new CustomerException("pin code not valid", new NumberFormatException());
		} else if (customer.getContactNumber() == 0) {
			throw new CustomerException("contact number not valid", new NumberFormatException());
		} else {
			if (!isAlphaNumeric(customer.getCustomerName())) {
				throw new CustomerException("customer name is not alpha numeric", new NumberFormatException());
			}
		}
		
		//length validation
		if( !Validate.dataLength(customer.getCustomerCode(), 10)) {
			throw new CustomerException("code greater than 10", new NumberFormatException());
		}else if(!Validate.dataLength(customer.getCustomerName(), 30)) {
			throw new CustomerException("name greater than 30", new NumberFormatException());
		}else if(!Validate.dataLength(customer.getEmail(), 100)) {
			throw new CustomerException("email greater than 100", new NumberFormatException());
		}else if(!Validate.dataLength(customer.getContactNumber(), 20)) {
			throw new CustomerException("number greater than 20", new NumberFormatException());
		}else if(!Validate.dataLength(customer.getContactPerson(), 100)) {
			throw new CustomerException("person greater than 100", new NumberFormatException());
		}else if(!Validate.dataLength(customer.getAddress1(), 100)) {
			throw new CustomerException("address1 greater than 100", new NumberFormatException());
		}else if(String.valueOf(customer.getPinCode()).length() !=6 ) {
			throw new CustomerException("pincode length must 6", new NumberFormatException());
		}
	}
}
