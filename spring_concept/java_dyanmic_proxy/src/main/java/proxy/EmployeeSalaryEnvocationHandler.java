package proxy;

import dto.Employee;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class EmployeeSalaryEnvocationHandler implements InvocationHandler {
    private Employee employeeTarget;

    public EmployeeSalaryEnvocationHandler(Employee employee) {
        this.employeeTarget = employee;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //pre processing
        if(method.getName().equals("giveHike")){
            double amount = (double) args[0];
            if(amount <= 0.0){
                throw new RuntimeException("hike cannot be zero or negative");
            }
        }else if(method.getName().equals("payCut")){
            double amount = (double) args[0];
            if(employeeTarget.getSalary() - amount <= 0.0){
                throw new RuntimeException("deduction amount is greater then salary");
            }
        }
        //making actual call to service layer
        Object object =  method.invoke(employeeTarget,args);
        //post-processing
        if(method.getName().equals("giveHike")){
            System.out.println("invoking salary hike for employee with id: " + employeeTarget.getId());
        } else if (method.getName().equals("payCut")) {
            System.out.println("invoking salary payCut for employee with id: " + employeeTarget.getId());
        }


        return object;
    }

    public static Employee getEmployeeProxy(Employee employee){
        return (Employee) Proxy.newProxyInstance(Employee.class.getClassLoader(),
                new Class[] {Employee.class},
                new EmployeeSalaryEnvocationHandler(employee)
        );
    }
}
