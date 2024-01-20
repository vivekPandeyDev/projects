package spring.batch.practice.repo;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.batch.practice.entity.Customer;

@Repository
public class CustomerRepo {
    @Autowired
    private SessionFactory sessionFactory;

    public Customer save(Customer customer){
        sessionFactory.getCurrentSession().save(customer);
        return customer;
    }
}
