package com.webservices.db;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.webservices.entity.Employee;
import com.webservices.enums.Qualification;

@Component
public class EmployeeDB {
	private Map<Integer, Employee> employees;
	private int employeeId = 102;

	public EmployeeDB() {
		super();
		employees = new HashMap<>();
		employees.put(100, new Employee("vivek pandey", Qualification.UNDER_GRADUATE, LocalDate.of(2002, 3, 20),
				"1@gmail.com", "9910242323", "software engineer"));
		employees.put(101, new Employee("vishal pandey", Qualification.POST_GRADUATE, LocalDate.of(2003, 3, 20),
				"2@gmail.com", "99105532323", "doctor"));
		employees.put(102, new Employee("shivam garg", Qualification.UNDER_GRADUATE, LocalDate.of(2000, 3, 20),
				"3@gmail.com", "9910242323", "software engineer"));
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public Map<Integer, Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Map<Integer, Employee> employees) {
		this.employees = employees;
	}

}
