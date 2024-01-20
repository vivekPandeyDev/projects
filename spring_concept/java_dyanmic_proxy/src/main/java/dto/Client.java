package dto;

import proxy.EmployeeSalaryEnvocationHandler;

import java.lang.reflect.Proxy;

import static proxy.EmployeeSalaryEnvocationHandler.getEmployeeProxy;

public class Client {
    public static void main(String[] args) {
        Employee employee = new ItEmployee(20_000D,1,"vivek pandey");
        System.out.println("previous salary : " + employee.getSalary());
        Employee employeeProxy =  getEmployeeProxy(employee);
        employeeProxy.giveHike(5000);
        System.out.println("new salary:" +employeeProxy.getSalary());
        employeeProxy.payCut(20000);
        System.out.println("new salary:" +employeeProxy.getSalary());
    }
}
