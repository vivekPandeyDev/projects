package com.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.dao.LoanDao;
import com.spring.dto.CustomerDto;
import com.spring.entity.Customer;
import com.spring.entity.Loan;
import com.spring.helper.CustomerUtil;
import com.spring.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService service;

	@Autowired
	private LoanDao dao;

	@PostMapping("/add")
	public String addCustomer(@Valid @ModelAttribute("customerDto") CustomerDto dto, BindingResult errors) {

		if (errors.hasErrors()) {

			return "customer";
		}
		Customer customer = CustomerUtil.getCustomer(dto);
		service.insertCustomer(customer);
		return "redirect:/index.jsp";
	}

	@GetMapping("/showRegister")
	public String showPage(@ModelAttribute("customerDto") CustomerDto dto) {
		return "customer";
	}

	@GetMapping("/search")
	public String searchCustomer(@RequestParam Integer id, Model model) {
		Customer customer = service.getCustomer(id);
		List<Loan> loans = dao.getAllLoan(id);
		model.addAttribute("customer", customer);
		model.addAttribute("loans", loans);
		return "display-customer";
	}

}
