package spring.batch.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import spring.batch.practice.entity.Customer;
import spring.batch.practice.repo.CustomerRepo;

import javax.transaction.Transactional;

@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    public Customer save(Customer customer){
        return customerRepo.save(customer);
    }
}
