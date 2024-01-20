package com.brd.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import com.brd.entity.Customer;
import com.brd.entity.TempCustomer;

@Repository
public class MakerDaoImpl implements MakerDao {

	private final SessionFactory factory;

	public MakerDaoImpl(SessionFactory factory) {
		super();
		this.factory = factory;
	}

	@Override
	public boolean addCustomer(TempCustomer customer) {
		return factory.getCurrentSession().save(customer) != null;
	}

	@Override
	public boolean removeCustomer(TempCustomer customer) {
		factory.getCurrentSession().delete(customer);
		return true;
	}

	@Override
	public boolean updateCustomer(TempCustomer customer) {
		factory.getCurrentSession().update(customer);
		return true;
	}

	@Override
	public TempCustomer getCustomer(String customerCode) {
		return factory.getCurrentSession().get(TempCustomer.class, customerCode);

	}

	@Override
	public Customer getMainCustomer(String customerCode) {
		return factory.getCurrentSession().get(Customer.class, customerCode);
	}

	@Override
	public List<Customer> getAuthorizedCustomer() {
		Session session = factory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
		criteria.from(Customer.class);
		return session.createQuery(criteria).getResultList();
	}

	@Override
	public List<TempCustomer> getUnAuthorizedCustomer() {
		Session session = factory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<TempCustomer> criteria = builder.createQuery(TempCustomer.class);
		criteria.from(TempCustomer.class);
		return session.createQuery(criteria).getResultList();
	}

	@Override
	public boolean insertOrUpdateBatch(List<TempCustomer> customers, int batch) {
		Session session = factory.getCurrentSession();
		int i = 0;
		for (TempCustomer customer : customers) {
			TempCustomer tempCustomer = session.get(TempCustomer.class, customer.getCustomerCode());
			if (tempCustomer == null) {
				session.save(customer);
			} else {
				ModelMapper mapper = new ModelMapper();
				mapper.map(customer, tempCustomer);
				session.update(tempCustomer);
			}
			if (i > 0 && i % batch == 0) {
				session.flush();
				session.clear();
			}
			i++;
		}
		return true;
	}

}
