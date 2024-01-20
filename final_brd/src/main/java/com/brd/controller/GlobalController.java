package com.brd.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.brd.dto.UserDto;

@Controller
public class GlobalController {
	

	

	@GetMapping("/changeLanguage")
	public String language(@RequestParam String lang) {
		return "forward:/?lang=" + lang;
	}

	@GetMapping("/access-denied")
	public String accessDenied() {
		return "access-denied";
	}

	@GetMapping("/404")
	public String notFoundPage() {
		return "404";

	}

	@GetMapping("/logout")
	public String logoutDo(HttpServletRequest request, HttpServletResponse response) {
		SecurityContextHolder.clearContext();
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		for (Cookie cookie : request.getCookies()) {
			cookie.setMaxAge(0);
		}

		return "redirect:/";
	}
	
	@PostMapping("/loginFailed")
	public String loginFailed(Model model) {
		model.addAttribute("msg", "invalid user name of password");
		model.addAttribute("userDto", new UserDto());
		return "login";
	}
	
	@GetMapping("/error")
	public String errorPage() {
		return "505";
	}
}
