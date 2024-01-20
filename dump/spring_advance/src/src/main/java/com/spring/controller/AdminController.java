package com.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.dto.CustomerDto;
import com.spring.entity.Customer;
import com.spring.helper.CustomerUtil;
import com.spring.service.CustomerService;

@RequestMapping("/admin")
@Controller
public class AdminController {

	@Autowired
	CustomerService service;

	@GetMapping("/fetch")
	public String FetchCustomers(Model model) {
		List<Customer> customers = service.getCustomers();
		model.addAttribute("customers", customers);
		return "admin-customer";
	}

	@GetMapping("/delete/{id}")
	public String getDeleteCustomer(@PathVariable Integer id, Model model) {
		service.removeCustomer(id);
		model.addAttribute("success", "deleted Customer Successfully");
		return "redirect:/admin/fetch";
	}

	@GetMapping("/get")
	public String fetchCustomer(@RequestParam String id, Model model) {
		Customer customer = service.getCustomer(Integer.parseInt(id));
		model.addAttribute("customerDto", customer);
		return "admin-update-customer";
	}

	@PostMapping("/update")
	public String fetchCustomer(@Valid @ModelAttribute("customerDto") CustomerDto dto, BindingResult errors,
			@RequestParam String id) {

		if (errors.hasErrors()) {
			return "admin-update-customer";
		}

		Customer customer = CustomerUtil.getCustomer(dto);
		customer.setCustomerId(Integer.parseInt(id));
		service.updateCustomer(customer);

		return "redirect:/admin/fetch";
	}
}
