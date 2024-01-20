package utility;

import dto.CustomerDto;
import entity.Address;
import entity.Customer;

import java.util.Random;

public class CustomerDtoToCustomer {

    public static Customer getCustomer(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setCustomerId(customerDto.getCustomerId());
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setDesignation(customerDto.getDesignation());
        customer.setDob(customerDto.getDob());
        customer.setProfession(customerDto.getProfession());
        customer.setMonthlyIncome(customerDto.getMonthlyIncome());
        customer.setCompanyName(customerDto.getCompanyName());
        customer.setAddress(new Address(new Random().nextInt(1000),"address1", "address2","city","state",123));
        return customer;
    }


}
