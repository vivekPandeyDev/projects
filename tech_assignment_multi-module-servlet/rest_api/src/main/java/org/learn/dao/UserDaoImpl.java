package org.learn.dao;

import lombok.extern.log4j.Log4j2;
import org.learn.configuration.DatabaseConfiguration;
import org.learn.entity.Address;
import org.learn.entity.User;
import org.learn.exception.UserExceptionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Log4j2
public class UserDaoImpl implements UserDao {


    public int addAddress(Address address, int id){
        Connection connection =  DatabaseConfiguration.getConnection();
        int addressCount = 0;
        try(PreparedStatement preparedStatement = connection.prepareStatement("insert into vivek_address(user_id,address_line_1,address_line_2,zipcode,city,state,country) values(?,?,?,?,?,?,?)")){

            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,address.getAddressLine1());
            preparedStatement.setString(3, address.getAddressLine2());
            preparedStatement.setInt(4,address.getZipcode());
            preparedStatement.setString(5, address.getCity());
            preparedStatement.setString(6, address.getState());
            preparedStatement.setString(7, address.getCountry());
            addressCount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new UserExceptionHandler(e.getMessage());
        }
        return addressCount;
    }

    public int addUser(User user){
        int userCount = 0;
        Connection connection =  DatabaseConfiguration.getConnection();
        String insertQueryUser = "insert into vivek_user(user_id,user_name,date_of_birth,email) values(?,?,?,?)";
        try(    PreparedStatement preparedStatementUser = connection.prepareStatement(insertQueryUser)){
            preparedStatementUser.setInt(1,user.getUserId());
            preparedStatementUser.setString(2,user.getUserName());
            preparedStatementUser.setDate(3, Date.valueOf(user.getDate()));
            preparedStatementUser.setString(4,user.getEmail());
            userCount =  preparedStatementUser.executeUpdate();
            log.info("user inserted count, {}",userCount);
        } catch (SQLException e) {
            throw new UserExceptionHandler(e.getMessage());
        }
        return userCount;
    }
    @Override
    public void save(User user) throws UserExceptionHandler {
       Connection connection =  DatabaseConfiguration.getConnection();

        try{
            connection.setAutoCommit(false);
            //saving user details
            int userCount =  addUser(user);
            log.info("address inserted count, {}",userCount);
            //saving address details
            int addressCount =  addAddress(user.getAddress(),user.getUserId());
            log.info("address inserted count, {}",addressCount);
            connection.commit();
        }catch (UserExceptionHandler | SQLException exceptionHandler){
            DatabaseConfiguration.rollback();
            DatabaseConfiguration.close();
            throw new UserExceptionHandler(exceptionHandler.getMessage());
        }
        DatabaseConfiguration.close();
    }

    @Override
    public User get(int userId) {
        User user = null;
        Connection connection = DatabaseConfiguration.getConnection();
        String selectUserQuery = "select user_id,user_name,date_of_birth,email from vivek_user where user_id=" + userId;
        String selectAddressQuery = "select address_line_1,address_line_2,zipcode,city,state,country from vivek_address where user_id=" + userId;
        try(
                PreparedStatement preparedStatementUser = connection.prepareStatement(selectUserQuery);
                PreparedStatement preparedStatementAddress = connection.prepareStatement(selectAddressQuery)
        ) {
            ResultSet resultSet =  preparedStatementUser.executeQuery();
            ResultSet resultSetAddress = preparedStatementAddress.executeQuery();
            if(resultSet.next()) {
                user = getUser(resultSet);
                if(resultSetAddress.next() ) {
                    Address address = getAddress(resultSetAddress);
                    user.setAddress(address);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseConfiguration.close();
        return user;
    }

    private static User getUser(ResultSet resultSet) throws SQLException {
        return User.builder()
                .userId(resultSet.getInt(1))
                .userName(resultSet.getString(2))
                .date(resultSet.getDate(3).toLocalDate())
                .email(resultSet.getString(4))
                .build();
    }

    private static Address getAddress(ResultSet resultSetAddress) throws SQLException {
        return Address.builder()
                .addressLine1(resultSetAddress.getString(1))
                .addressLine2(resultSetAddress.getString(2))
                .zipcode(resultSetAddress.getInt(3))
                .city(resultSetAddress.getString(4))
                .state(resultSetAddress.getString(5))
                .country(resultSetAddress.getString(6))
                .build();
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        Connection connection = DatabaseConfiguration.getConnection();
        String selectUserQuery = "select user_id,user_name,date_of_birth,email from vivek_user";
        String selectAddressQuery = "select address_line_1,address_line_2,zipcode,city,state,country from vivek_address where user_id=?";
        try(
                PreparedStatement preparedStatementUser = connection.prepareStatement(selectUserQuery);
                PreparedStatement preparedStatementAddress = connection.prepareStatement(selectAddressQuery)
        ) {
            ResultSet resultSet =  preparedStatementUser.executeQuery();
            while (resultSet.next()) {
                User user = getUser(resultSet);
                preparedStatementAddress.setInt(1,user.getUserId());
                ResultSet resultSetAddress = preparedStatementAddress.executeQuery();
                if(resultSetAddress.next() ) {
                    Address address = getAddress(resultSetAddress);
                    user.setAddress(address);
                }
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseConfiguration.close();
        return users;
    }
}
