package app_config;


import entity.Address;
import entity.Customer;
import entity.LoanAgreement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.*;

@Configuration
@ComponentScan(basePackages = {"entity","service","dao","temp_entity","aspect"})
public class BeanConfig {
    private static final Logger logger = LogManager.getLogger(BeanConfig.class);
    @Bean("address")
    @Scope("prototype")
    public Address getAddress(){
        return new Address(100,"address1","address2","faridabad","haryana",121003);
    }

    @Bean("customer")
    @Scope("prototype")
    public Customer getCustomer(){
        Customer customer = new Customer(100,"vivek pandey",2000,"engineer","developer","xyz company");
        Set<String> emails = new HashSet<>();
        List<String> phoneNumbers = new ArrayList<>();
        emails.add("1@gmail.com");
        emails.add("2@gmail.com");
        emails.add("3@gmail.com");
        phoneNumbers.add("93245235");
        phoneNumbers.add("+913422452435");
        phoneNumbers.add("2532452435");
        phoneNumbers.add("+912532452435");
        customer.setDob(LocalDate.now());
        customer.setEmailAddresses(emails);
        customer.setPhoneNumbers(phoneNumbers);
        customer.setAddresses( new Address(100,"address1","address2","faridabad","haryana",121003));
        return customer;
    }

    @Bean("customer1")
    @Scope("prototype")
    public Customer getCustomer1(){
        Customer customer = new Customer(101,"vishal pandey",2000000,"mbbs","developer","xyz company");
        Set<String> emails = new HashSet<>();
        List<String> phoneNumbers = new ArrayList<>();
        emails.add("1@gmail.com");
        emails.add("2@gmail.com");
        emails.add("3@gmail.com");
        phoneNumbers.add("93245235");
        phoneNumbers.add("+913422452435");
        phoneNumbers.add("2532452435");
        phoneNumbers.add("+912532452435");
        customer.setDob(LocalDate.of(1990,03,12));
        customer.setEmailAddresses(emails);
        customer.setPhoneNumbers(phoneNumbers);
        customer.setAddresses(new Address(101,"address1","address2","faridabad","haryana",121003) );
        return customer;
    }

    @Bean("loanAgreement")
    @Scope("prototype")
    public List<LoanAgreement> getLoanAgreement(){
        List<LoanAgreement> loanAgreements = new ArrayList<>();
        LoanAgreement loanAgreement1 = new LoanAgreement(1000,"home Loan",2000);
        LoanAgreement loanAgreement2 = new LoanAgreement(1001,"consumer Loan",5000);
        LoanAgreement loanAgreement3 = new LoanAgreement(1002,"vehicle Loan",4000);
        loanAgreements.add(loanAgreement1);
        loanAgreements.add(loanAgreement2);
        loanAgreements.add(loanAgreement3);
        return loanAgreements;

    }

    @Bean("connection")
    public Connection getConnection(){
        Connection connection= null;
        try (FileInputStream file = new FileInputStream("src/main/resources/dbconfig.properties")) {
            Properties properties = new Properties();
            properties.load(file);
            Class.forName(properties.getProperty("driver"));
            connection = DriverManager.getConnection(properties.getProperty("uri"), properties);
            logger.info("connection created successfully!!");
        } catch (Exception e) {
            logger.error("error connecting to database: {}",e.getMessage());
        }
        return connection;

    }
}
