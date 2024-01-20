package com.brd.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.brd.dto.AdminDto;
import com.brd.dto.UserDto;
import com.brd.entity.User;
import com.brd.exception.CustomUserException;
import com.brd.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private final UserService userService;
	

	public UserController(UserService loginUser) {
		super();
		this.userService = loginUser;
	}

	@GetMapping
	public String getUserForm(@ModelAttribute("userDto") UserDto dto) {
		return "login";
	}
	
	@GetMapping("/admin")
	public String getAdminPage(Model model) {
	 	model.addAttribute("users",userService.getAllUser().stream().filter( user -> !user.getUsername().equals("admin")).collect(Collectors.toList()));
		return "admin_show_table";
	}
	@GetMapping("/admin/delete/{username}")
	public String deleteUser(@PathVariable("username") String userName) {
	 	if(userService.getUserByName(userName) == null) {
	 		throw new CustomUserException("no user found with given user name");
	 	}else {
	 		userService.deleteUser(userName);
	 	}
		return "redirect:/user/admin";
	}
	
	
	@GetMapping("/admin/updateRole/{userName}")
	public String updateUserRole(@PathVariable("userName") String userName,Model model) {
		User user = userService.getUserByName(userName);
		if(user == null) {
			throw new CustomUserException("no user found with given user name");
		}
		AdminDto dto = new AdminDto();
		dto.setUsername(userName);
		dto.setRoles(user.getAuthorities());
		model.addAttribute("adminDto",dto);
		return "update_user_role";
	}
	
	@PostMapping("/admin/assignRole/{username}")
	public String assignNewRole(@ModelAttribute("adminDto") AdminDto dto,@PathVariable String username) {
		userService.updateUserRole(username,dto.getRoles());
		return "redirect:/user/admin";
	}
	
	
	@GetMapping("/action/delete/{userName}")
	public String removeUser(@PathVariable String userName, Authentication auth) {
		if (userName.equals(auth.getName())) {
			userService.deleteUser(userName);
		} else {
			throw new CustomUserException("not authorized to delete this user");
		}

		return "redirect:/logout";
	}

	@PostMapping("/action/update")
	public String updateUser(@Valid @ModelAttribute("userDto") UserDto dto, BindingResult errors, Authentication auth) {
		if (errors.hasErrors()) {
			return "user";
		} else if (dto.getUsername().equals(auth.getName())) {
			userService.updateUser(dto);
		} else {
			throw new CustomUserException("not authorized to update this user");
		}
		return "redirect:/";
	}

	@GetMapping("/action/update")
	public String getUserUpdateForm(Model model, Authentication auth) {
		User user = userService.getUserByName(auth.getName());
		model.addAttribute("userDto", user);
		return "user";
	}

	@PostMapping("/signUp")
	public String addUser(@Valid @ModelAttribute("userDto") UserDto dto, BindingResult errors) {
		if (errors.hasErrors()) {
			return "login";
		}
		userService.createUser(dto);
		return "redirect:/";
	}
	


//	TODO -> FILE UPLOAD AND TRANSCATION MANAGEMENT

}
