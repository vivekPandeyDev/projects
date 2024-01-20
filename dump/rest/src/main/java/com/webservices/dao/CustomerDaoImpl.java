package com.webservices.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webservices.entity.Customer;

@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao {

	private SessionFactory factory;
	
	 

	public CustomerDaoImpl(SessionFactory factory) {
		super();
		this.factory = factory;
	}



	@Override
	public List<Customer> getAll() {
		return factory.getCurrentSession().createQuery("SELECT a FROM Customer a LEFT JOIN FETCH a.loanAgreements",Customer.class).getResultList();
	}



	@Override
	public Customer get(int id) {
		return factory.getCurrentSession().get(Customer.class, id);
	}



	@Override
	public void update(Customer customer) {
		factory.getCurrentSession().update(customer);
	}



	@Override
	public void delete(Customer customer) {
		factory.getCurrentSession().delete(customer);
	}



	@Override
	public void insert(Customer customer) {
		factory.getCurrentSession().save(customer);
	}



	@Override
	public void insertAll(List<Customer> customers) {
		customers.forEach(this::insert);
	}

	
}
