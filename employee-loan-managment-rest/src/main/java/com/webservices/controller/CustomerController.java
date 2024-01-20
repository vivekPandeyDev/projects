package com.webservices.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webservices.entity.Address;
import com.webservices.entity.ConsumerVehicleLoan;
import com.webservices.entity.Customer;
import com.webservices.entity.EducationLoan;
import com.webservices.entity.HomeLoan;
import com.webservices.entity.LoanAgreement;
import com.webservices.entity.LoanProduct;
import com.webservices.entity.Message;
import com.webservices.enums.LoanStatus;
import com.webservices.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {



	private CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	@GetMapping
	public List<Customer> getAllCustomers() {
		List<Customer> customers = customerService.getAll();
		return customers;
	}

	@PostMapping
	public Message addCustomer(@RequestBody Customer customer) {
		customerService.insert(customer);
		return new Message(HttpStatus.CREATED, "customer added sucessfully!!!");
	}

	@GetMapping("/{id}")
	public Customer getCustomerById(@PathVariable Integer id) {
		return customerService.get(id);
	}

	@PutMapping
	public Message updateCustomer(@RequestBody Customer customer) {
		customerService.update(customer);
		return new Message(HttpStatus.OK, "customer updated sucessfully!!!");

	}

	@DeleteMapping("/{id}")
	public Message deleteCustomer(@PathVariable Integer id) {
		customerService.delete(id);
		return new Message(HttpStatus.NO_CONTENT,
				"customer deleted sucessfully!!!");
	}

	@PostMapping("/dummy")
	public String insertDummyCustomer() {
		List<Customer> customers = new ArrayList<>();
		Address address1 = new Address(101, "address 1", "address 2", "city 1",
				"state 1", 123);
		Customer customer1 = Customer.builder().dateOfBirth(LocalDate.now())
				.customerName("vivek pandey").address(address1)
				.loanAgreements(getAllLoanAgreement()).build();
		customer1.getLoanAgreements().forEach(t -> t.setCustomer(customer1));
		customers.add(customer1);

		customerService.insertAll(customers);
		return "added dummy data";

	}

	private List<LoanAgreement> getAllLoanAgreement() {
		HomeLoan homeLoan = new HomeLoan.HomeLoanBuilder(
				new LoanProduct.LoanProductBuilder("l1o1", "home loan", 12)
						.setMaxLoanAmount(200000),
				50000, 40000).build();
		ConsumerVehicleLoan consumerVehicleLoan = new ConsumerVehicleLoan.ConsumerVehicleLoanBuilder(
				new LoanProduct.LoanProductBuilder("l102", "consumer loan", 10),
				20000, 10000).build();
		EducationLoan educationLoan = new EducationLoan.EducationLoanBuilder(
				new LoanProduct.LoanProductBuilder("l103", "education loan",
						10),
				400000, 200000).build();
		LoanAgreement loanAgreement1 = new LoanAgreement(educationLoan, 20000,
				12, 5, LoanStatus.ACTIVE, LocalDate.now(), 12);
		LoanAgreement loanAgreement2 = new LoanAgreement(homeLoan, 500000, 12,
				5, LoanStatus.PENDING, LocalDate.now(), 12);
		LoanAgreement loanAgreement3 = new LoanAgreement(consumerVehicleLoan,
				100000, 12, 5, LoanStatus.APPROVED, LocalDate.now(), 12);

		return Arrays.asList(loanAgreement1, loanAgreement2, loanAgreement3);

	}

}
