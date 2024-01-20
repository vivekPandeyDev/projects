package main;

import app_config.BeanConfig;
import dao.AddressDao;
import db_connection.MyConnection;
import entity.Customer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main2 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        Customer customer =  context.getBean("customer", Customer.class);
        Customer customer2 =  context.getBean("customer", Customer.class);
        AddressDao addressDao = context.getBean(AddressDao.class);
        System.out.println(customer2);
        System.out.println(customer == customer2);
//        MyConnection.getConnection();
    }
}
