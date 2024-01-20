package com.spring.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.dao.LoanDao;
import com.spring.dto.LoanDto;
import com.spring.entity.Customer;
import com.spring.entity.Loan;
import com.spring.service.CustomerService;

@Controller
@RequestMapping("/loan")
public class LoanController {

	@Autowired
	private LoanDao dao;
	
	@Autowired
	private CustomerService service;

	@GetMapping("/showLoan")
	public String showLoanPage() {
		return "loan-detail";
	}

	@PostMapping("/add")
	public String addLoan(@ModelAttribute("loan") LoanDto dto) {
		Loan loan = new Loan();
		BeanUtils.copyProperties(dto, loan);
		try {
			dao.insertLoan(loan, dto.getCustomerId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "loan-detail";
		}
		return "redirect:/index.jsp";
	}

	@GetMapping("/search")
	public String searchCustomer(@RequestParam Integer id, Model model) {
		try {
			Loan loan = dao.getLoan(id);
			Integer customerId = dao.getCustomerId(id);
			Customer customer =  service.getCustomer(customerId);
			model.addAttribute("loan", loan);
			model.addAttribute("customer",customer);
			return "display-loan";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "display-loan";
		}

	}

}
