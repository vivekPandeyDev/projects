package com.webservices.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.webservices.dao.CustomerDao;
import com.webservices.entity.Customer;
import com.webservices.exception.UnAvailableException;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;

	public CustomerServiceImpl(CustomerDao customerDao) {
		super();
		this.customerDao = customerDao;
	}

	@Override
	public List<Customer> getAll() {
		return customerDao.getAll();
	}

	@Override
	public Customer get(int id) {
		Customer customer = customerDao.get(id);
		if (customer == null) {
			throw new UnAvailableException(HttpStatus.NOT_FOUND,
					"cannot find the customer with customer given customer id");
		}
		return customer;
	}

	@Override
	public void update(Customer customer) {
		if (customer == null || customerDao.get(customer.getCustomerId()) == null) {
			throw new UnAvailableException(HttpStatus.NOT_FOUND,
					"cannot find the customer with customer given customer id");
		}
		customerDao.update(customer);
	}

	@Override
	public void delete(int id) {
		if (customerDao.get(id) == null) {
			throw new UnAvailableException(HttpStatus.NOT_FOUND,
					"cannot find the customer with customer given customer id");
		}
		customerDao.delete(customerDao.get(id));
	}

	@Override
	public void insert(Customer customer) {
		if (customer == null) {
			throw new UnAvailableException(HttpStatus.BAD_REQUEST, "customer cannot be empty");
		}
		customerDao.insert(customer);
	}

	@Override
	public void insertAll(List<Customer> customers) {
		if (customers == null) {
			throw new UnAvailableException(HttpStatus.BAD_REQUEST, "customer list cannot be empty");
		}
		customerDao.insertAll(customers);
	}


	
	


}
