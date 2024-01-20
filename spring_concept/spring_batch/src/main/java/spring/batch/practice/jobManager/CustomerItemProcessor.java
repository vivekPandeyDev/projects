package spring.batch.practice.jobManager;

import org.springframework.batch.item.ItemProcessor;
import spring.batch.practice.entity.Customer;

public class CustomerItemProcessor  implements ItemProcessor<Customer,Customer> {
    @Override
    public Customer process(Customer customer) throws Exception {
        customer.setGender(customer.getGender().toUpperCase());
        return customer;
    }
}
