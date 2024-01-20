package com.webservices.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.webservices.entity.EducationLoan;
import com.webservices.entity.HomeLoan;
import com.webservices.entity.LoanAgreement;
import com.webservices.entity.LoanProduct;

@Repository
@Transactional
public class LoanAgreementDaoImpl implements LoanAgreementDao {

	private SessionFactory factory;

	public LoanAgreementDaoImpl(SessionFactory factory) {
		super();
		this.factory = factory;
	}

	@Override
	public List<LoanAgreement> getAll() {
		return factory.getCurrentSession().createQuery("from LoanAgreement",LoanAgreement.class).getResultList();
	}

	@Override
	public LoanAgreement get(int id) {
		return factory.getCurrentSession().get(LoanAgreement.class, id);
	}

	@Override
	public void update(LoanAgreement loanAgreement) {		
		factory.getCurrentSession().update(loanAgreement);

	}

	@Override
	public void delete(LoanAgreement loanAgreement) {
		factory.getCurrentSession().delete(loanAgreement);

	}

	@Override
	public void insert(LoanAgreement loanAgreement) {
		factory.getCurrentSession().save(loanAgreement);

	}

	@Override
	public void insertAll(List<LoanAgreement> customers) {
		customers.forEach(this::insert);		
	}

	@Override
	public List<HomeLoan> getAllHomeLoan() {
		return factory.getCurrentSession().createQuery("from HomeLoan",HomeLoan.class).getResultList();
	}

	@Override
	public List<LoanProduct> getLoanProductById(String code) {
		TypedQuery<LoanProduct> query =  factory.getCurrentSession().createQuery("from LoanProduct where loanProductCode=:code",LoanProduct.class);
		query.setParameter("code", code);
		return query.getResultList();
	
	}

	@Override
	public String getCollegeName(String code) {
		TypedQuery<EducationLoan> query =  factory.getCurrentSession().createQuery("from EducationLoan where loanProductCode=:code",EducationLoan.class);
		query.setParameter("code", code);
		return query.getSingleResult().getCollegeName();
	}
	
	
	
	
	
	
	
	
	
	
}
