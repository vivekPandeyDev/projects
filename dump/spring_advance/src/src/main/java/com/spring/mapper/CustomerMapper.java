package com.spring.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.entity.Address;
import com.spring.entity.Customer;

public class CustomerMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet arg, int rowNum) throws SQLException {
		Customer customer = new Customer();
		customer.setCustomerId(arg.getInt("customerId"));
		customer.setFirstName(arg.getString("firstName"));
		customer.setLastName(arg.getString("lastName"));
		customer.setNationality(arg.getString("nationality"));
		customer.setGender(arg.getString("gender"));
		customer.setDob(arg.getDate("dob").toLocalDate() );
		Address address = new Address();
		address.setHouseNo(arg.getInt("houseNo"));
		address.setCity(arg.getString("city"));
		address.setState(arg.getString("state"));
		address.setCountry(arg.getString("country"));
		address.setAddressLine1(arg.getString("addressLine1"));
		address.setAddressLine2(arg.getString("addressLine2"));
		customer.setAddress(address);
		return customer;
	}

}
