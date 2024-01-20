package com.webservices.service;

import java.util.List;

import com.webservices.entity.Customer;

public interface CustomerService {
	List<Customer> getAll();

	Customer get(int id);

	void update(Customer customer);

	void delete(int id);

	void insert(Customer customer);

	void insertAll(List<Customer> customers);
}
