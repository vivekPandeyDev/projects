package com.webservices.dao;

import java.util.List;

import com.webservices.entity.Customer;

public interface CustomerDao {
	List<Customer> getAll();

	Customer get(int id);

	void update(Customer customer);

	void delete(Customer customer);

	void insert(Customer customer);

	void insertAll(List<Customer> customers);

}
