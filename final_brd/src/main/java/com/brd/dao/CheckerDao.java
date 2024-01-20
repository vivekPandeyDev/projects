package com.brd.dao;

import java.util.List;

import com.brd.entity.Customer;
import com.brd.entity.TempCustomer;

public interface CheckerDao {
	boolean addCustomer(Customer customer);

	boolean addTempCustomer(TempCustomer customer);

	boolean removeCustomer(Customer customer);

	Customer getCustomer(String customerCode);
	
	TempCustomer getTempCustomer(String customerCode);
	
	List<TempCustomer> getUnAuthorizedCustomer();
}
