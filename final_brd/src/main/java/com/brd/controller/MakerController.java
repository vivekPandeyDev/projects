package com.brd.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.brd.dto.CustomerDto;
import com.brd.entity.Customer;
import com.brd.entity.TempCustomer;
import com.brd.enums.RecordStatus;
import com.brd.service.MakerService;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/maker")
@Log4j2
public class MakerController {

	private final MakerService makerService;

	public MakerController(MakerService service) {
		super();
		this.makerService = service;
	}
	
	/*
	 * Shows Maker's dashBoard
	 */

	@GetMapping
	public String getDashBoard() {
		return "maker_dashboard";
	}
	/*
	 * Shows all the customers to the maker by adding it to model
	 */

	@GetMapping("/show/table")
	public String showTable(Model model) {
		model.addAttribute("unAuthorizedCustomers", makerService.getUnAuthorizedCustomer());
		model.addAttribute("authorizedCustomers", makerService.getAuthorizedCustomer());
		return "maker_show_table";
	}
	
	/*
	 * Add a new customer using the form using customer Data transfer object
	 */

	@GetMapping("/customer")
	public String getForm(@ModelAttribute("customerDto") CustomerDto dto) {
		return "customer";
	}
	/*
	 * Get specific customer detail
	 * @Path -> which contains customer code (primary key)
	 */

	@GetMapping("/customer/detail/{code}")
	public String getDetailpage(@PathVariable("code") String code, @RequestParam("status") RecordStatus status,
			Model model) {
		if (status == RecordStatus.A) {
			model.addAttribute("customer", makerService.getMainCustomer(code));
		} else {
			model.addAttribute("customer", makerService.getCustomer(code));
		}

		return "customer_detail";
	}
	
	/*
	 * Adding new customer after validation
	 * takes data transfer object and author's name
	 */

	@PostMapping("/customer/add")
	public String addCustomer(@Valid @ModelAttribute("customerDto") CustomerDto dto, BindingResult errors,
			Authentication auth) {
		if (errors.hasErrors()) {
			return "customer";
		}
		log.error("user name while adding :" + auth.getName());
		makerService.addCustomer(dto, auth.getName());
		return "redirect:/maker/show/table";
	}
	
	/*
	 * Removing new customer after validation
	 * takes customer code (primary key)
	 */

	@GetMapping("/customer/remove/{code}")
	public String removeCustomer(@PathVariable String code, Model model) {
		if (makerService.removeCustomer(code)) {
			model.addAttribute("delete_msg", "customer with given customer code is removed : " + code);
		} else {
			model.addAttribute("delete_msg", "no customer found with given customer code: " + code);
		}

		return "redirect:/maker/show/table";

	}
	
	/*
	 * Updating new customer after validation
	 * takes data transfer object and author's name
	 */

	@PostMapping("/customer/update/{code}")
	public String updateCustomer(@Valid @ModelAttribute("customerDto") CustomerDto dto, BindingResult errors,
			@PathVariable String code, Authentication auth) {
		dto.setCustomerCode(code);
		if (errors.hasErrors()) {
			return "customer_update";
		}
		makerService.updateCustomer(dto, auth.getName());
		return "redirect:/maker/show/table";
	}
	
	/*
	 * Customer detail update form 
	 */

	@GetMapping("/customer/update/{code}")
	public String getCustomerForm(@PathVariable String code, Model model) {
		CustomerDto dto = getCustomerDto(code);
		model.addAttribute("customerDto", dto);
		return "customer_update";
	}



	/*
	 * Utility to get customerDto using customer code (primary key)
	 */

	private CustomerDto getCustomerDto(String code) {
		Customer mainCustomer = makerService.getMainCustomer(code);
		CustomerDto dto = new CustomerDto();
		ModelMapper mapper = new ModelMapper();
		if (mainCustomer == null) {
			TempCustomer tempCustomer = makerService.getCustomer(code);
			mapper.map(tempCustomer, dto);
		} else {
			mapper.map(mainCustomer, dto);
		}
		return dto;
	}

}
