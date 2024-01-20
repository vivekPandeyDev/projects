package com.webservices.dao;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.webservices.db.EmployeeDB;
import com.webservices.entity.Employee;

@Service
public class EmployeeDaoImpl implements EmployeeDao {

	private final Map<Integer, Employee> employees;
	private int employeeId;

	/*
	 * By Default auto-wired
	 */

	public EmployeeDaoImpl(EmployeeDB db) {
		super();
		this.employees = db.getEmployees();
		this.employeeId = db.getEmployeeId();
	}

	@Override
	public boolean add(Employee employee) {
		return (employees.put(++employeeId, employee) == null);

	}

	@Override
	public boolean remove(int empId) {
		return employees.remove(empId) != null;

	}

	@Override
	public boolean update(int empId, Employee employee) {
		return employees.replace(empId, employee) != null;
	}

	@Override
	public Employee get(int empId) {
		return employees.get(empId);
	}

	@Override
	public Map<Integer, Employee> getAll() {
		return employees;
	}

}
