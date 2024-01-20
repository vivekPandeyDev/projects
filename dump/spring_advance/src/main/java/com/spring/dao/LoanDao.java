package com.spring.dao;

import java.util.List;

import com.spring.entity.Loan;

public interface LoanDao {
	List<Loan> getAllLoan(int id);

	boolean insertLoan(Loan loan, int id);

	Loan getLoan(int id);
	
	int getCustomerId(int id);
}
