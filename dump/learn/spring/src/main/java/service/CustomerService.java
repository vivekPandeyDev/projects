package service;

import dao.AddressDao;
import dao.Dao;
import entity.Address;
import entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utility.CustomerNotFoundException;

import java.time.LocalDate;
import java.util.List;


@Service
public class CustomerService implements  ServiceLayer<Customer> {
    @Autowired
    private Dao<Customer> customerDao;
    @Autowired
    private AddressDao addressDao;

    @Override
    public boolean save(Customer customer) {
        customerDao.save(customer);
        addressDao.save(customer.getAddresses(),customer.getCustomerId());
        return true;
    }


    @Override
    public Customer getSingleEntity(int id) {
        Customer customer =  customerDao.getSingleEntity(id);
        if(customer == null){
            throw new CustomerNotFoundException("customer not found!!!");
        }
        Address address =  addressDao.getSingleEntity(customer.getCustomerId());
        customer.setAddresses(address);
        return customer;
    }

    @Override
    public List<Customer> getAllEntity() {
        List<Customer> customers =  customerDao.getAllEntity();
        for(Customer customer : customers){
            Address address =  addressDao.getSingleEntity(customer.getCustomerId());
            customer.setAddresses(address);
        }
        return customers;
    }

    @Override
    public List<Customer> getAllEntityByName(String name) {
        List<Customer> customers =  customerDao.getAllEntity();
        for(Customer customer : customers){
            Address address =  addressDao.getSingleEntity(customer.getCustomerId());
            customer.setAddresses(address);
        }
        return customers;
    }

    @Override
    public boolean update(Customer customer) {
        return customerDao.update(customer) && addressDao.update(customer.getAddresses(),customer.getCustomerId());

    }

    @Override
    public boolean delete(int id) {
        return customerDao.delete(id) && addressDao.delete(id);
    }

    public Dao<Customer> getCustomerDao() {
        return customerDao;
    }

    public CustomerService setCustomerDao(Dao<Customer> customerDao) {
        this.customerDao = customerDao;
        return this;
    }

    public AddressDao getAddressDao() {
        return addressDao;
    }

    public CustomerService setAddressDao(AddressDao addressDao) {
        this.addressDao = addressDao;
        return this;
    }
}
