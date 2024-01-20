package com.brd.service;

import java.util.List;

import com.brd.dto.CustomerDto;
import com.brd.entity.Customer;
import com.brd.entity.TempCustomer;
import com.brd.enums.RecordStatus;

public interface MakerService {
	boolean addCustomer(CustomerDto customer,String createrName);

	boolean removeCustomer(String customerCode);

	TempCustomer getCustomer(String customerCode);
	
	boolean updateCustomer(CustomerDto customer,String modifiedName);

	Customer getMainCustomer(String customerCode);
	
	boolean updateStatus(String customerCode,RecordStatus status);
	
	List<Customer> getAuthorizedCustomer();
	
	List<TempCustomer> getUnAuthorizedCustomer();
	boolean insertOrUpdateBatch(List<TempCustomer> customers, int batch);
	
}
