package com.brd.service;

import java.util.List;

import com.brd.entity.Customer;
import com.brd.entity.TempCustomer;

public interface CheckerService {
	boolean addCustomer(Customer customer);

	boolean addTempCustomer(TempCustomer customer);

	boolean removeCustomer(String customerCode);

	Customer getCustomer(String customerCode);

	String acceptCustomer(String customerCode,String authorizerName);

	String rejectCustomer(String customerCode);

	List<TempCustomer> getUnAuthorizedCustomer();
	TempCustomer getTempCustomer(String customerCode);

}
