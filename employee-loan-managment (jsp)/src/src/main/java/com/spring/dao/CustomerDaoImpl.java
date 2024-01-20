package com.spring.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.spring.entity.Customer;
import com.spring.mapper.CustomerMapper;

@Repository
public class CustomerDaoImpl implements CustomerDao {
	@Autowired
	private JdbcTemplate template;

	@Override
	public Customer getCustomer(int id) {
		String query = "Select * from new_vivek_customer where customerId=" + id;
		try {
			return template.queryForObject(query, new CustomerMapper());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean insertCustomer(Customer customer) {
		String query = "insert into new_vivek_customer(firstName,lastName,nationality,gender,dob,houseNo,state,city,country,addressLine1,addressLine2) values(?,?,?,?,?,?,?,?,?,?,?)";
		Object[] values = { customer.getFirstName(), customer.getLastName(), customer.getNationality(),
				customer.getGender(), Date.valueOf(customer.getDob()), customer.getAddress().getHouseNo(),
				customer.getAddress().getState(), customer.getAddress().getCity(), customer.getAddress().getCountry(),
				customer.getAddress().getAddressLine1(), customer.getAddress().getAddressLine2()

		};

		template.update(query, values);

		return true;
	}

	@Override
	public List<Customer> getCustomers() {
		String query = "Select * from new_vivek_customer";
		try {
			return template.query(query, new CustomerMapper());
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@Override
	public int removeCustomer(int id) {
		String query = "delete from new_vivek_customer where customerId=" + id;
		return template.update(query);
	}

	@Override
	public int updateCustomer(Customer customer) {
		String query = "update new_vivek_customer set firstName=?, lastName = ?, nationality =? ,gender =?,dob = ?,houseNo = ? ,state = ?,city = ?,country = ?,addressLine1 = ?,addressLine2 =? where customerId=?";
		Object[] values = { customer.getFirstName(), customer.getLastName(), customer.getNationality(),
				customer.getGender(), Date.valueOf(customer.getDob()), customer.getAddress().getHouseNo(),
				customer.getAddress().getState(), customer.getAddress().getCity(), customer.getAddress().getCountry(),
				customer.getAddress().getAddressLine1(), customer.getAddress().getAddressLine2(),
				customer.getCustomerId()

		};
		return template.update(query, values);
	}

}
