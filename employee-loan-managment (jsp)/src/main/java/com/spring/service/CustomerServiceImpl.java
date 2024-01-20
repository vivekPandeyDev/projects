package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.CustomerDao;
import com.spring.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao dao;

	@Override
	public Customer getCustomer(int id) {
		return dao.getCustomer(id);
	}

	@Override
	public boolean insertCustomer(Customer customer) {
		return dao.insertCustomer(customer);
	}

	@Override
	public List<Customer> getCustomers() {
		return dao.getCustomers();
	}

	@Override
	public int removeCustomer(int id) {
		return dao.removeCustomer(id);
	}

	@Override
	public int updateCustomer(Customer customer) {
		return dao.updateCustomer(customer);
	}

}
