package com.spring.dao;

import java.util.List;

import com.spring.entity.Customer;

public interface CustomerDao {
	Customer getCustomer(int id);
	List<Customer> getCustomers();
	boolean insertCustomer(Customer customer);
	int removeCustomer(int id);
	int updateCustomer(Customer customer);
}
