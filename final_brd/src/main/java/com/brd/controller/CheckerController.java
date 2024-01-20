package com.brd.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brd.service.CheckerService;

@Controller
@RequestMapping("/checker")
public class CheckerController {

	private final CheckerService checkerService;
	private final String CHECKER_TABLE_URL = "redirect:/checker/show/table";
	public CheckerController(CheckerService checkerService) {
		super();
		this.checkerService = checkerService;
	}

	@GetMapping("/customer/accept/{code}")
	public String checkerAcceptAction(@PathVariable String code, Authentication auth, Model model) {
		model.addAttribute("accept_msg", checkerService.acceptCustomer(code, auth.getName()));
		return CHECKER_TABLE_URL;
	}

	@GetMapping("/customer/reject/{code}")
	public String checkerRejectAction(@PathVariable String code, Model model) {
		model.addAttribute("reject_msg", checkerService.rejectCustomer(code));
		return CHECKER_TABLE_URL;
	}

	@GetMapping("/customer/delete/{code}")
	public String checkerDeleteAction(@PathVariable String code,Model model) {
		
		if (checkerService.removeCustomer(code)) {
			model.addAttribute("delete_msg", "customer deleted from both table");
		} else {
			model.addAttribute("delete_msg", "cannot delete the customer");
		}
		return CHECKER_TABLE_URL;
	}

	@GetMapping("/show/table")
	public String showTable(Model model) {
		model.addAttribute("unAuthorizedCustomers", checkerService.getUnAuthorizedCustomer());
		return "checker_show_table";
	}

	@GetMapping("/customer/detail/{code}")
	public String getDetailpage(@PathVariable("code") String code, Model model) {
		
		model.addAttribute("customer", checkerService.getTempCustomer(code));
		return "customer_detail";
	}

	@GetMapping
	public String getDashBoard() {
		return "checker_dashboard";
	}

}
