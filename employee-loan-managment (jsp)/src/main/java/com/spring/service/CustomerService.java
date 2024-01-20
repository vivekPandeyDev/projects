package com.spring.service;

import java.util.List;

import com.spring.entity.Customer;

public interface CustomerService {
	Customer getCustomer(int id);

	List<Customer> getCustomers();

	int removeCustomer(int id);

	int updateCustomer(Customer customer);

	boolean insertCustomer(Customer customer);
}
