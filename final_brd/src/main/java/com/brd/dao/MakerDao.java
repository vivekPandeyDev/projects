package com.brd.dao;

import java.util.List;

import com.brd.entity.Customer;
import com.brd.entity.TempCustomer;

public interface MakerDao {
	boolean addCustomer(TempCustomer customer);

	boolean removeCustomer(TempCustomer customer);

	boolean updateCustomer(TempCustomer customer);
	
	TempCustomer getCustomer(String customerCode);

	Customer getMainCustomer(String customerCode);

	List<Customer> getAuthorizedCustomer();

	List<TempCustomer> getUnAuthorizedCustomer();
	
	boolean insertOrUpdateBatch(List<TempCustomer> customers, int batch);
}
