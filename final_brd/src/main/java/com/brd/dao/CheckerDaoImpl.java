package com.brd.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.brd.entity.Customer;
import com.brd.entity.TempCustomer;

@Repository
public class CheckerDaoImpl implements CheckerDao {

	private final SessionFactory factory;

	public CheckerDaoImpl(SessionFactory factory) {
		super();
		this.factory = factory;
	}

	@Override
	public boolean addCustomer(Customer customer) {
		factory.getCurrentSession().saveOrUpdate(customer);
		return true;
	}

	@Override
	public boolean addTempCustomer(TempCustomer customer) {
		return factory.getCurrentSession().save(customer) != null;
	}

	@Override
	public boolean removeCustomer(Customer customer) {
		factory.getCurrentSession().delete(customer);
		return true;
	}

	@Override
	public Customer getCustomer(String customerCode) {
		return factory.getCurrentSession().get(Customer.class, customerCode);

	}
	


	@Override
	public TempCustomer getTempCustomer(String customerCode) {
		return factory.getCurrentSession().get(TempCustomer.class, customerCode);
	}

	@Override
	public List<TempCustomer> getUnAuthorizedCustomer() {
		Session session = factory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<TempCustomer> criteria = builder.createQuery(TempCustomer.class);
		criteria.from(TempCustomer.class);
		return session.createQuery(criteria).getResultList();
	}

}
