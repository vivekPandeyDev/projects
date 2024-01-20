package dao;

import entity.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDaoImpl implements Dao<Customer> {

    @Autowired
    private Connection connection;

    private static final Logger logger = LogManager.getLogger(CustomerDaoImpl.class);

    @Override
    public boolean save(Customer customer) {
        String query = "insert into spring_vivek_customer(customerId,customerName,monthlyIncome,profession,designation,companyName,dateOfBirth) values(?,?,?,?,?,?,?)";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, customer.getCustomerId());
            preparedStatement.setString(2, customer.getCustomerName());
            preparedStatement.setDouble(3, customer.getMonthlyIncome());
            preparedStatement.setString(4, customer.getProfession());
            preparedStatement.setString(5, customer.getDesignation());
            preparedStatement.setString(6, customer.getCompanyName());
            preparedStatement.setDate(7, Date.valueOf(customer.getDob()));
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            logger.error("cannot insert customer {}", e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Customer getSingleEntity(int id) {
        Customer customer = null;
        String query = "select customerId,customerName,monthlyIncome,profession,designation,companyName,dateOfBirth from spring_vivek_customer where customerId=? ";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customer = new Customer();
                customer.setCustomerId(resultSet.getInt(1));
                customer.setCustomerName(resultSet.getString(2));
                customer.setMonthlyIncome(resultSet.getDouble(3));
                customer.setProfession(resultSet.getString(4));
                customer.setDesignation(resultSet.getString(5));
                customer.setCustomerName(resultSet.getString(6));
                customer.setDob(resultSet.getDate(7).toLocalDate());
            }

        } catch (Exception e) {
            logger.error("cannot get customer {}", e.getMessage());

        }

        return customer;
    }

    @Override
    public List<Customer> getAllEntity()  {
        List<Customer> customers = null;
        String query = "select customerId,customerName,monthlyIncome,profession,designation,companyName,dateOfBirth from spring_vivek_customer  ";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {

            ResultSet resultSet = preparedStatement.executeQuery();
            customers = new ArrayList<>();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(resultSet.getInt(1));
                customer.setCustomerName(resultSet.getString(2));
                customer.setMonthlyIncome(resultSet.getDouble(3));
                customer.setProfession(resultSet.getString(4));
                customer.setDesignation(resultSet.getString(5));
                customer.setCustomerName(resultSet.getString(6));
                customer.setDob(resultSet.getDate(7).toLocalDate());
                customers.add(customer);
            }

        } catch (Exception e) {
            logger.error("cannot get all customer {}", e.getMessage());

        }
        return customers;
    }

    @Override
    public List<Customer> getAllEntityByName(String name) {
        List<Customer> customers = null;
        String query = "select customerId,customerName,monthlyIncome,profession,designation,companyName,dateOfBirth from spring_vivek_customer where customerName=? ";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            Customer customer = new Customer();
            customers = new ArrayList<>();
            while (resultSet.next()) {
                customer.setCustomerId(resultSet.getInt(1));
                customer.setCustomerName(resultSet.getString(2));
                customer.setMonthlyIncome(resultSet.getDouble(3));
                customer.setProfession(resultSet.getString(4));
                customer.setDesignation(resultSet.getString(5));
                customer.setCustomerName(resultSet.getString(6));
                customer.setDob(resultSet.getDate(7).toLocalDate());
                customers.add(customer);
            }

        } catch (Exception e) {
            logger.error("cannot get customer by name {}", e.getMessage());

        }
        return customers;
    }

    @Override
    public boolean update(Customer customer) {
        int rowUpdated=0;
        String query = "update  spring_vivek_customer set  customerName=?, monthlyIncome=? ,profession=? ,designation=? ,companyName=? ,dateOfBirth=? where customerId=?";
        try (

                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, customer.getCustomerName());
            preparedStatement.setDouble(2, customer.getMonthlyIncome());
            preparedStatement.setString(3, customer.getProfession());
            preparedStatement.setString(4, customer.getDesignation());
            preparedStatement.setString(5, customer.getCompanyName());
            preparedStatement.setDate(6, Date.valueOf(customer.getDob()));
            preparedStatement.setInt(7, customer.getCustomerId());
            rowUpdated = preparedStatement.executeUpdate();
        } catch (Exception e) {
            logger.error("cannot insert customer {}", e.getMessage());
            return false;
        }
        return rowUpdated == 1;
    }

    @Override
    public boolean delete(int id) {
        int rowUpdated = 0;
        String query = "delete from  spring_vivek_customer where customerId =?";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);
            rowUpdated =  preparedStatement.executeUpdate();
        } catch (Exception e) {
            logger.error("cannot delete customer {}", e.getMessage());
            return false;
        }
        return rowUpdated == 1;
    }

    public Connection getConnection() {
        return connection;
    }

    public CustomerDaoImpl setConnection(Connection connection) {
        this.connection = connection;
        return this;
    }


}
