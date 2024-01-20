package com.webservices.dao;

import java.util.List;

import com.webservices.entity.HomeLoan;
import com.webservices.entity.LoanAgreement;
import com.webservices.entity.LoanProduct;

public interface LoanAgreementDao {
	List<LoanAgreement> getAll();

	LoanAgreement get(int id);

	void update(LoanAgreement loanAgreement);

	void delete(LoanAgreement loanAgreement);

	void insert(LoanAgreement loanAgreement);

	void insertAll(List<LoanAgreement> customers);
	
	List<HomeLoan>  getAllHomeLoan();
	
	List<LoanProduct> getLoanProductById(String code);
	
	String getCollegeName(String code);
}
