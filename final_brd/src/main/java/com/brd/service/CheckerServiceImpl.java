package com.brd.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import com.brd.dao.CheckerDao;
import com.brd.entity.Customer;
import com.brd.entity.TempCustomer;
import com.brd.enums.RecordStatus;

@Service
@Transactional
public class CheckerServiceImpl implements CheckerService {

	private final CheckerDao checkerDao;
	private final MakerService makerService;
	private final ModelMapper mapper;

	public CheckerServiceImpl(CheckerDao checkerDao, ModelMapper mapper, MakerService makerService) {
		super();
		this.checkerDao = checkerDao;
		this.mapper = mapper;
		this.makerService = makerService;
	}

	@Override
	public boolean addCustomer(Customer customer) {
		return checkerDao.addCustomer(customer);
	}

	@Override
	public boolean addTempCustomer(TempCustomer customer) {
		return checkerDao.addTempCustomer(customer);
	}

	@Override
	public boolean removeCustomer(String customerCode) {
		TempCustomer customer = makerService.getCustomer(customerCode);
		Customer mainCustomer = checkerDao.getCustomer(customerCode);
		if (customer == null) {
			return false;
		} else if (customer.getRecordStatus() == RecordStatus.D) {
			makerService.removeCustomer(customerCode);
			checkerDao.removeCustomer(mainCustomer);
			return true;
		}
		return false;
	}

	@Override
	public Customer getCustomer(String customerCode) {
		return checkerDao.getCustomer(customerCode);
	}
	
	

	@Override
	public TempCustomer getTempCustomer(String customerCode) {
		return checkerDao.getTempCustomer(customerCode);
	}

	@Override
	public String acceptCustomer(String code, String authorizerName) {
		TempCustomer customer = makerService.getCustomer(code);
		if (customer == null) {
			return "no customer found with given customer code";
		} else if (customer.getRecordStatus() == RecordStatus.N || customer.getRecordStatus() == RecordStatus.M) {
			makerService.removeCustomer(code);
			Customer mainCustomer = new Customer();
			mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
			mapper.map(customer, mainCustomer);
			mainCustomer.getRecordDetail().setAuthorizedDate(LocalDate.now());
			mainCustomer.getRecordDetail().setAuthorizedBy(authorizerName);
			addCustomer(mainCustomer);
			return "customer with customer code is accepted :" + code;
		} else {
			return "record with status N or M can be authorized";
		}
	}

	@Override
	public String rejectCustomer(String code) {
		TempCustomer customer = makerService.getCustomer(code);
		if (customer == null) {
			return "no customer found with given customer code";
		} else {
			if (customer.getRecordStatus() == RecordStatus.N) {
				makerService.updateStatus(code, RecordStatus.NR);
			} else if (customer.getRecordStatus() == RecordStatus.M) {
				makerService.updateStatus(code, RecordStatus.MR);
			} else if (customer.getRecordStatus() == RecordStatus.D) {
				makerService.updateStatus(code, RecordStatus.DR);
			} else {
				return "status must be in N,M,D to reject the current record";
			}

		}
		return "customer with customer code is rejected :" + code;
	}

	@Override
	public List<TempCustomer> getUnAuthorizedCustomer() {
		return checkerDao.getUnAuthorizedCustomer();
	}

}
