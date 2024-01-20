package com.spring.helper;

import org.springframework.beans.BeanUtils;

import com.spring.dto.CustomerDto;
import com.spring.entity.Address;
import com.spring.entity.Customer;

public class CustomerUtil {

	private CustomerUtil() {
		super();
	}

	public static Customer getCustomer(CustomerDto dto) {
		Customer customer = new Customer();
		Address address = new Address();
		BeanUtils.copyProperties(dto, customer);
		BeanUtils.copyProperties(dto.getAddress(), address);
		customer.setAddress(address);
		return customer;
	}
}
