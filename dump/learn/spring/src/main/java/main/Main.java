package main;

import entity.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    private final static Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        Customer customer =  context.getBean("customer1", Customer.class);
        logger.info("customer: {} " ,customer);

    }
}
