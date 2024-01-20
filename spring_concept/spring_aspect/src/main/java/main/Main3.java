package main;

import app_config.BeanConfig;
import app_config.JavaConfig;
import entity.Address;
import entity.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.CustomerService;

import java.time.LocalDate;
import java.util.List;

public class Main3 {
    public static final Logger logger = LogManager.getLogger(Main3.class);

    public static void main(String[] args) {
        ApplicationContext context2 = new AnnotationConfigApplicationContext(BeanConfig.class);
        Customer customer = context2.getBean("customer", Customer.class);
        Customer customer1 = context2.getBean("customer1", Customer.class);
        CustomerService serviceLayer = context2.getBean("customerService", CustomerService.class);
//        insert customer
        serviceLayer.save(customer);
        serviceLayer.save(customer1);
        //        all customer
        logger.info("getting all customers");
        List<Customer> customerList = serviceLayer.getAllEntity();
        customerList.stream().forEach(logger::info);
//        get single customer
        try {
            logger.info("getting single customer");
            Customer customer3 = serviceLayer.getSingleEntity(101);
            Customer customer2 = serviceLayer.getSingleEntity(100);

            logger.info(customer2);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

//      update single customer

        Customer customer3 = new Customer(101, "vivek pandey", 50000, "developer", "software developer", "abc company");
        customer3.setAddresses(new Address(103, "updated address1", " updated address2", "faridabad", "haryana", 121003));
        customer3.setDob(LocalDate.of(2002, 9, 03));
        logger.info("is customer updated: {}", serviceLayer.update(customer3));
//       delete customer
//        logger.info("is customer delete: {}", serviceLayer.delete(100));
//        get all customer

        List<Customer> customers = serviceLayer.getAllEntityByName("vivek pandey");
        customers.stream().forEach(logger::info);

    }
}
