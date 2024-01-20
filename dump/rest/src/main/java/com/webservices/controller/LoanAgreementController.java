package com.webservices.controller;

import java.time.LocalDate;
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

import com.webservices.entity.ConsumerVehicleLoan;
import com.webservices.entity.EducationLoan;
import com.webservices.entity.HomeLoan;
import com.webservices.entity.LoanAgreement;
import com.webservices.entity.LoanProduct;
import com.webservices.entity.Message;
import com.webservices.enums.LoanStatus;
import com.webservices.service.LoanAgreementService;

@RestController
@RequestMapping("/loans")
public class LoanAgreementController {

	private LoanAgreementService loanAgreementService;

	public LoanAgreementController(LoanAgreementService loanAgreementService) {
		super();
		this.loanAgreementService = loanAgreementService;
	}

	@GetMapping
	public List<LoanAgreement> getAllLoanAgreement() {
		return loanAgreementService.getAll();
	}
	
	@GetMapping("/homeloans")
	public List<HomeLoan> getAllHomeLoa () {
		return loanAgreementService.getAllHomeLoan();
	}
	
	@GetMapping("/loanproducts/{code}")
	public List<LoanProduct> getAllLoanProductById (@PathVariable String code) {
		return loanAgreementService.getLoanProductById(code);
	}
	
	@GetMapping("/college/{code}")
	public String getCollegeName (@PathVariable String code) {
		return loanAgreementService.getCollegeName(code);
	}

	@PostMapping
	public Message addCustomer(@RequestBody LoanAgreement loanAgreement) {
		loanAgreementService.insert(loanAgreement);
		return new Message(HttpStatus.CREATED,
				"loanAgreement added sucessfully!!!");
	}

	@GetMapping("/{id}")
	public LoanAgreement getCustomerById(@PathVariable Integer id) {
		return loanAgreementService.get(id);
	}

	@PutMapping
	public Message updateCustomer(@RequestBody LoanAgreement loanAgreement) {
		loanAgreementService.update(loanAgreement);
		return new Message(HttpStatus.OK,
				"loanAgreement updated sucessfully!!!");

	}

	@DeleteMapping("/{id}")
	public Message deleteCustomer(@PathVariable Integer id) {
		loanAgreementService.delete(id);
		return new Message(HttpStatus.NO_CONTENT,
				"loanAgreement deleted sucessfully!!!");
	}

	@PostMapping("/dummy")
	public String insertDummyLoan() {
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

		List<LoanAgreement> loanAgreements = Arrays.asList(loanAgreement1,
				loanAgreement2, loanAgreement3);

		loanAgreementService.insertAll(loanAgreements);
		return "added dummy data";

	}
	
	

}
