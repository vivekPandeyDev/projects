package com.webservices.dao;

import java.util.Map;

import com.webservices.entity.Employee;

public interface EmployeeDao {
	boolean add(Employee employee);

	boolean remove(int empId);

	boolean update(int empId,Employee employee);

	Employee get(int empId);

	Map<Integer, Employee> getAll();
}
