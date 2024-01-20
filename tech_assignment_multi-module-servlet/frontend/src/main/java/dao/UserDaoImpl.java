package dao;

import configuration.DatabaseConfiguration;
import entity.Address;
import entity.Education;
import entity.SecondaryAddress;
import entity.User;
import exception.UserExceptionHandler;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Log4j2
public class UserDaoImpl implements UserDao {

    @Override
    public int getUserId() {
        Connection connection =  DatabaseConfiguration.getConnection();
        int userId = 0;
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT NVL(MAX(user_Id), 1) AS max_value FROM vivek_user")){

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                userId =  resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new UserExceptionHandler(e.getMessage());
        }
        return userId + 1;
    }

    public int addSecondaryAddress(SecondaryAddress address, int id){
        Connection connection =  DatabaseConfiguration.getConnection();
        int addressCount;
        try(PreparedStatement preparedStatement = connection.prepareStatement("insert into vivek_address(user_id,address_line_1,address_line_2,zipcode,city,state,country) values(?,?,?,?,?,?,?)")){

            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,address.getSecAddressLine1());
            preparedStatement.setString(3, address.getSecAddressLine2());
            preparedStatement.setInt(4,address.getSecZipcode());
            preparedStatement.setString(5, address.getSecCity());
            preparedStatement.setString(6, address.getSecState());
            preparedStatement.setString(7, address.getSecCountry());
            addressCount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new UserExceptionHandler(e.getMessage());
        }
        return addressCount;
    }

    public int addAddress(Address address, int id){
        Connection connection =  DatabaseConfiguration.getConnection();
        int addressCount ;
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

    public void addEducation(Education education, int id){
        Connection connection =  DatabaseConfiguration.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement("insert into vivek_education(USERID,degreeType,institution,year,percentage) values(?,?,?,?,?)")){

            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,education.getDegreeType());
            preparedStatement.setString(3, education.getInstitution());
            preparedStatement.setInt(4,education.getYear());
            preparedStatement.setInt(5,education.getPercentage());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserExceptionHandler(e.getMessage());
        }
    }



    public int addUser(User user){
        if(isUserExist(user.getEmail())){
            throw  new UserExceptionHandler("user email already exist");
        }
        int userCount ;
        Connection connection =  DatabaseConfiguration.getConnection();
        String insertQueryUser = "insert into vivek_user(user_id,user_name,date_of_birth,email) values(?,?,?,?)";
        try(    PreparedStatement preparedStatementUser = connection.prepareStatement(insertQueryUser)){
            preparedStatementUser.setInt(1,user.getUserId());
            preparedStatementUser.setString(2,user.getUserName());
            preparedStatementUser.setDate(3, Date.valueOf(user.getDateOfBirth()));
            preparedStatementUser.setString(4,user.getEmail());
            userCount =  preparedStatementUser.executeUpdate();
            log.info("user inserted count, {}",userCount);
        } catch (SQLException e) {
            throw new UserExceptionHandler(e.getMessage());
        }
        return userCount;
    }

    private boolean isUserExist(String email) {
        Connection connection =  DatabaseConfiguration.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vivek_user WHERE email =?")){
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            throw new UserExceptionHandler(e.getMessage());
        }
        return false;

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
            int addressCount =  addAddress(user.getPrimaryAddress(),user.getUserId());
            log.info("address inserted count, {}",addressCount);
            if(user.getSecondaryAddress() != null) {
                addressCount = addSecondaryAddress(user.getSecondaryAddress(), user.getUserId());
                log.info("sec address inserted count, {}", addressCount);
            }
            //saving education details
            user.getEducationList().forEach(education -> addEducation(education, user.getUserId()));
            log.info("education list inserted count, {}", user.getEducationList().size());
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
        String selectEducationQuery = "select degreeType,institution,year,percentage from vivek_education where USERID=" + userId;
        try(
                PreparedStatement preparedStatementUser = connection.prepareStatement(selectUserQuery);
                PreparedStatement preparedStatementAddress = connection.prepareStatement(selectAddressQuery);
                PreparedStatement preparedStatementEducation = connection.prepareStatement(selectEducationQuery)
        ) {
            ResultSet resultSet =  preparedStatementUser.executeQuery();
            ResultSet resultSetAddress = preparedStatementAddress.executeQuery();
            ResultSet resultSetEducation = preparedStatementEducation.executeQuery();
            if(resultSet.next()) {
                user = getUser(resultSet);
                if(resultSetAddress.next() ) {
                    Address address = getAddress(resultSetAddress);
                    user.setPrimaryAddress(address);
                }
                if(resultSetAddress.next() ) {
                    SecondaryAddress address = getSecAddress(resultSetAddress);
                    user.setSecondaryAddress(address);
                }
            }
            List<Education> educationList = new ArrayList<>();
            while(resultSetEducation.next()){
                Education education = getEducation(resultSetEducation);
                educationList.add(education);
            }
            if(user != null)
                user.setEducationList(educationList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseConfiguration.close();
        return user;
    }

    private Education getEducation(ResultSet resultSetEducation) throws SQLException {
        return Education.builder()
                .degreeType(resultSetEducation.getString(1))
                .institution(resultSetEducation.getString(2))
                .year(resultSetEducation.getInt(3))
                .percentage(resultSetEducation.getInt(4))
                .build();
    }

    private static User getUser(ResultSet resultSet) throws SQLException {
        return User.builder()
                .userId(resultSet.getInt(1))
                .userName(resultSet.getString(2))
                .dateOfBirth(resultSet.getDate(3).toLocalDate())
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

    private static SecondaryAddress getSecAddress(ResultSet resultSetAddress) throws SQLException {
        return SecondaryAddress.builder()
                .secAddressLine1(resultSetAddress.getString(1))
                .secAddressLine2(resultSetAddress.getString(2))
                .secZipcode(resultSetAddress.getInt(3))
                .secCity(resultSetAddress.getString(4))
                .secState(resultSetAddress.getString(5))
                .secCountry(resultSetAddress.getString(6))
                .build();
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        Connection connection = DatabaseConfiguration.getConnection();
        String selectUserQuery = "select user_id,user_name,date_of_birth,email from vivek_user";

        try(
                PreparedStatement preparedStatementUser = connection.prepareStatement(selectUserQuery)
        ) {
            ResultSet resultSet =  preparedStatementUser.executeQuery();
            while (resultSet.next()) {
                User tempUser =  getUser(resultSet);
                User user =  get(tempUser.getUserId());
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DatabaseConfiguration.close();
        return userList;
    }
}
