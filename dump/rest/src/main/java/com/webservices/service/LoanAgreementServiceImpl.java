package com.webservices.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.webservices.dao.LoanAgreementDao;
import com.webservices.entity.HomeLoan;
import com.webservices.entity.LoanAgreement;
import com.webservices.entity.LoanProduct;
import com.webservices.exception.UnAvailableException;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class LoanAgreementServiceImpl implements LoanAgreementService {

	private LoanAgreementDao loanAgreementDao;

	public LoanAgreementServiceImpl(LoanAgreementDao LoanDao) {
		super();
		this.loanAgreementDao = LoanDao;
	}

	@Override
	public List<LoanAgreement> getAll() {
		return loanAgreementDao.getAll();
	}

	@Override
	public LoanAgreement get(int id) {
		LoanAgreement loan = loanAgreementDao.get(id);
		if (loan == null) {
			throw new UnAvailableException(HttpStatus.NOT_FOUND, "cannot find the loan with loan given loan id");
		}
		return loan;
	}

	@Override
	public void update(LoanAgreement loan) {
		if (loan == null || loanAgreementDao.get(loan.getLoanAgreementId()) == null) {
			throw new UnAvailableException(HttpStatus.NOT_FOUND, "cannot find the loan with loan given loan id");
		}
		loanAgreementDao.update(loan);
	}

	@Override
	public void delete(int id) {
		if (loanAgreementDao.get(id) == null) {
			throw new UnAvailableException(HttpStatus.NOT_FOUND, "cannot find the Loan with Loan given Loan id");
		}
		loanAgreementDao.delete(loanAgreementDao.get(id));
	}

	@Override
	public void insert(LoanAgreement loan) {
		if (loan == null) {
			throw new UnAvailableException(HttpStatus.BAD_REQUEST, "Loan cannot be empty");
		}
		loanAgreementDao.insert(loan);
	}
	
	@Override
	public void insertAll(List<LoanAgreement> loanAgreements) {
		if (loanAgreements == null) {
			throw new UnAvailableException(HttpStatus.BAD_REQUEST, "customer list cannot be empty");
		}
		loanAgreementDao.insertAll(loanAgreements);
	}

	@Override
	public List<HomeLoan> getAllHomeLoan() {
		List<HomeLoan> homeloans= loanAgreementDao.getAllHomeLoan();
		if (homeloans == null) {
			throw new UnAvailableException(HttpStatus.BAD_REQUEST, "customer list cannot be empty");
		}
		return homeloans;
	}

	@Override
	public List<LoanProduct> getLoanProductById(String code) {
		List<LoanProduct> loanProducts = loanAgreementDao.getLoanProductById(code);
		if (loanProducts == null) {
			throw new UnAvailableException(HttpStatus.BAD_REQUEST, "customer list cannot be empty");
		}
		return loanProducts;
	}

	@Override
	public String getCollegeName(String code) {
		String name = loanAgreementDao.getCollegeName(code);
		log.error("get college:" , name);
		if (name == null) {
			throw new UnAvailableException(HttpStatus.BAD_REQUEST, "customer list cannot be empty");
		}
		return	name;
	}
	
	

	
	

	
	


}
