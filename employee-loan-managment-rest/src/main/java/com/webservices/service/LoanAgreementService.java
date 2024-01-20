package com.webservices.service;

import java.util.List;

import com.webservices.entity.HomeLoan;
import com.webservices.entity.LoanAgreement;
import com.webservices.entity.LoanProduct;

public interface LoanAgreementService {
	List<LoanAgreement> getAll();

	LoanAgreement get(int id);

	void update(LoanAgreement loanAgreement);

	void delete(int id);

	void insert(LoanAgreement loanAgreement);

	void insertAll(List<LoanAgreement> customers);
	
	List<HomeLoan>  getAllHomeLoan();
	List<LoanProduct> getLoanProductById(String code);
	String getCollegeName(String code);
}
