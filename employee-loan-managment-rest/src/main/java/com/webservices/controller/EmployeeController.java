package com.webservices.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webservices.dao.EmployeeDao;
import com.webservices.entity.Employee;
import com.webservices.entity.Message;
import com.webservices.exception.UnAvailableException;

@RestController
@RequestMapping("/emp")
@PropertySource("classpath:exception-messages.properties")
public class EmployeeController {

	private final EmployeeDao employeeDao;
	private final Environment env;

	/*
	 * By Default auto-wired
	 */
	public EmployeeController(EmployeeDao employeeDao, Environment env) {
		super();
		this.employeeDao = employeeDao;
		this.env = env;
	}

	@GetMapping("/test")
	public String test() {
		return "hello world";
	}

	@GetMapping("/{id}")
	public Employee getEmployee(@PathVariable Integer id) {
		if (employeeDao.get(id) == null) {
			throw new UnAvailableException(HttpStatus.NOT_FOUND, env.getProperty("exception.fetch.failure"));
		}

		return employeeDao.get(id);
	}

	@GetMapping("/")
	public Map<Integer, Employee> getEmployees() {
		return employeeDao.getAll();
	}

	@PostMapping(value = "/create")
	public Message createEmployee(@Valid @RequestBody Employee employee, BindingResult errors) {
		if (errors.hasErrors()) {

			Message message = new Message(HttpStatus.BAD_REQUEST, env.getProperty("exception.create.failure"));
			message.setErrors(errors.getAllErrors().stream().map(error -> error.getDefaultMessage()).toList());
			return message;
		}
		if (employeeDao.add(employee)) {
			return new Message(HttpStatus.CREATED, env.getProperty("exception.create.success"));
		} else {
			return new Message(HttpStatus.BAD_REQUEST, env.getProperty("exception.create.failure"));
		}
	}

	@DeleteMapping(value = "/delete/{id}")
	public Message deleteEmployee(@PathVariable Integer id) {
		if (employeeDao.remove(id)) {
			return new Message(HttpStatus.OK, env.getProperty("exception.delete.success"));
		} else {
			return new Message(HttpStatus.BAD_REQUEST, env.getProperty("exception.delete.failure") + id);
		}
	}

	@PutMapping(value = "/edit/{id}")
	public Message updateEmployee(@Valid @RequestBody Employee employee, BindingResult errors,
			@PathVariable Integer id) {
		if (errors.hasErrors()) {
			Message message = new Message(HttpStatus.BAD_REQUEST, env.getProperty("exception.update.failure"));
			message.setErrors(errors.getAllErrors().stream().map(error -> error.getDefaultMessage()).toList());
			return message;
		}

		if (employeeDao.update(id, employee)) {
			return new Message(HttpStatus.OK, env.getProperty("exception.update.success"));
		} else {
			return new Message(HttpStatus.BAD_REQUEST, env.getProperty("exception.update.failure"));
		}
	}

}
