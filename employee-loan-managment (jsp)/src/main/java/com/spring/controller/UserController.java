package com.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.dto.UserDto;
import com.spring.validators.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Logger logger = LogManager.getLogger(UserController.class);

	@GetMapping("/form")
	public String getUserPage(@ModelAttribute("userInfo") UserDto dto) {
		return "user-register";
	}

	@PostMapping("/register")
	public String getUserPage(@Valid @ModelAttribute("userInfo") UserDto dto, BindingResult result) {
		new UserValidator().validate(dto, result); 
		
		if (result.hasErrors()) {
			List<ObjectError> allErrors = result.getAllErrors();
			for (ObjectError error : allErrors) {
				logger.info("mapping /user/register error : {}",error);
			}
			return "user-register";
		}

		return "user-register";
	}

	@ModelAttribute("hobbiesList")
	public List<String> getHobbies() {
		List<String> hobbies = new ArrayList<>();
		hobbies.add("cricket");
		hobbies.add("basketball");
		hobbies.add("table tennis");
		return hobbies;

	}

	@ModelAttribute("locationList")
	public List<String> populateLocation() {
		List<String> preferLoc = new ArrayList<>();
		preferLoc.add("Nodia");
		preferLoc.add("Indore");
		preferLoc.add("JK");
		preferLoc.add("Dehradun");
		return preferLoc;
	}
}
