package com.brd.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.brd.dao.MakerDao;
import com.brd.dto.CustomerDto;
import com.brd.entity.Customer;
import com.brd.entity.RecordDetail;
import com.brd.entity.TempCustomer;
import com.brd.enums.RecordStatus;
import com.brd.exception.CustomMakerException;

import lombok.extern.log4j.Log4j2;

@Service
@Transactional
@Log4j2
public class MakerServiceImpl implements MakerService {

	private final MakerDao makerDao;
	private final ModelMapper mapper;
	private final String NO_CUSTOMER_FOUND = "NO CUSTOMER FOUND TO PERFORM OPERATION";
	public MakerServiceImpl(MakerDao makerDao, ModelMapper mapper) {
		super();
		this.makerDao = makerDao;
		this.mapper = mapper;
	}

	@Override
	public boolean addCustomer(CustomerDto dto,String createrName) {
		TempCustomer tempCustomer = new TempCustomer();
		mapper.map(dto, tempCustomer);
		tempCustomer.setRecordDetail(new RecordDetail(LocalDate.now(), createrName, null, null, null, null));
		tempCustomer.setRecordStatus(RecordStatus.N);
		makerDao.addCustomer(tempCustomer);
		log.info("customer inserted with code: {}",tempCustomer.getCustomerCode());
		return true;
	}

	@Override
	public boolean removeCustomer(String customerCode) {
		TempCustomer tempCustomer = makerDao.getCustomer(customerCode);
		if (tempCustomer != null) {
			makerDao.removeCustomer(tempCustomer);
		} else {
			Customer customer = makerDao.getMainCustomer(customerCode);
			if (customer == null) {
				throw new CustomMakerException(NO_CUSTOMER_FOUND);
			} else {
				tempCustomer = new TempCustomer();
				mapper.map(customer, tempCustomer);
				tempCustomer.setRecordStatus(RecordStatus.D);
				makerDao.addCustomer(tempCustomer);
				
			}
		}
		log.info("customer removed with code: {}",tempCustomer.getCustomerCode());
		return true;
	}

	@Override
	public boolean updateCustomer(CustomerDto dto,String modifiedName) {
		TempCustomer customer = makerDao.getCustomer(dto.getCustomerCode());
		if (customer != null) {
			mapper.map(dto, customer);
			customer.getRecordDetail().setModifiedBy(modifiedName);
			customer.getRecordDetail().setModifiedDate(LocalDate.now());
			customer.setRecordStatus(RecordStatus.M);
			makerDao.updateCustomer(customer);
		} else {
			Customer mainCustomer = makerDao.getMainCustomer(dto.getCustomerCode());
			if (mainCustomer == null)
				throw new CustomMakerException(NO_CUSTOMER_FOUND);
			customer = new TempCustomer();
			mapper.map(dto, mainCustomer);
			mapper.map(mainCustomer,customer);
			customer.getRecordDetail().setModifiedBy(modifiedName);
			customer.getRecordDetail().setModifiedDate(LocalDate.now());
			customer.setRecordStatus(RecordStatus.M);
			makerDao.addCustomer(customer);
		}
		log.info("customer updated with code: {}",dto.getCustomerCode());
		return true;

	}

	@Override
	public boolean updateStatus(String customerCode, RecordStatus status) {
		TempCustomer customer = makerDao.getCustomer(customerCode);
		if (customer == null) {
			throw new CustomMakerException(NO_CUSTOMER_FOUND);
		} else {
			customer.setRecordStatus(status);
			makerDao.updateCustomer(customer);
		}
		log.info("customer status updated with code: {}",customer.getCustomerCode());
		return true;
	}

	@Override
	public TempCustomer getCustomer(String customerCode) {
		return makerDao.getCustomer(customerCode);
	}

	@Override
	public Customer getMainCustomer(String customerCode) {
		return makerDao.getMainCustomer(customerCode);
	}

	@Override
	public List<Customer> getAuthorizedCustomer() {
		return makerDao.getAuthorizedCustomer();
	}

	@Override
	public List<TempCustomer> getUnAuthorizedCustomer() {
		return makerDao.getUnAuthorizedCustomer();
	}

	@Override
	public boolean insertOrUpdateBatch(List<TempCustomer> customers, int batch) {
		return makerDao.insertOrUpdateBatch(customers, batch);
	}


	
	
	 

}
