package com.spring.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.entity.Loan;

@Repository
public class LoanDaoImpl implements LoanDao {

	@Autowired
	JdbcTemplate template;

	@Override
	public List<Loan> getAllLoan(int id) {
		String query = "Select * from new_vivek_loan where customerId=" + id;
		return template.query(query, BeanPropertyRowMapper.newInstance(Loan.class));
	}

	@Override
	public boolean insertLoan(Loan loan, int id) {
		String query = "insert into new_vivek_loan(customerId,loanId,productType,product,amount,tenure,rate,agreementDate,installmentDate) values(?,?,?,?,?,?,?,?,?)";
		Object[] values = { id, loan.getLoanId(), loan.getProductType(), loan.getProduct(), loan.getAmount(),
				loan.getTenure(), loan.getRate(), Date.valueOf(loan.getAgreementDate()),
				Date.valueOf(loan.getInstallmentDate()) };

		template.update(query, values);

		return true;
	}

	@Override
	public Loan getLoan(int id) {
		String query = "Select * from new_vivek_loan where loanId=" + id;
		return template.queryForObject(query, BeanPropertyRowMapper.newInstance(Loan.class));
	}

	@Override
	public int getCustomerId(int id) {
		String query = "Select customerId from new_vivek_loan where loanId=" + id;
		return template.queryForObject(query, Integer.class);
	}

}
