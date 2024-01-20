package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GlobalController {

	@GetMapping("/changeLanguage")
	public String language(@RequestParam String lang) {
		return "redirect:/?lang=" + lang;
	}

	@GetMapping("/access-denied")
	public String accessDenied() {
		return "access-denied";
	}

	@GetMapping("/404")
	public String notFoundPage() {
		return "404";

	}

}
