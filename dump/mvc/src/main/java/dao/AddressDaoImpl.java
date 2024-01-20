package dao;

import entity.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Repository
@Component
public class AddressDaoImpl implements AddressDao {

    @Autowired
    private Connection connection;

    private static final Logger logger = LogManager.getLogger(AddressDaoImpl.class);

    @Override
    public boolean save(Address address, int id) {
        int rowUpdated = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement("Insert into spring_vivek_address values(?,?,?,?,?,?,?)")) {
            preparedStatement.setInt(1, address.getAddressId());
            preparedStatement.setString(2, address.getAddressLine1());
            preparedStatement.setString(3, address.getAddressLine2());
            preparedStatement.setString(4, address.getCity());
            preparedStatement.setString(5, address.getState());
            preparedStatement.setInt(6, address.getZip());
            preparedStatement.setInt(7, id);
            rowUpdated = preparedStatement.executeUpdate();

        } catch (Exception e) {
            logger.error("cannot add address {}", e.getMessage());
        }
        return rowUpdated == 1;
    }

    @Override
    public Address getSingleEntity(int id) {
        Address address = null;
        try (
                PreparedStatement preparedStatement = connection.prepareStatement("Select * from spring_vivek_address where customerId=?")
        ) {
            preparedStatement.setInt(1, id);
            ResultSet executeQuery = preparedStatement.executeQuery();
            while (executeQuery.next()) {
                address = new Address();
                address.setAddressId(executeQuery.getInt(1));
                address.setAddressLine1(executeQuery.getString(2));
                address.setAddressLine2(executeQuery.getString(3));
                address.setCity(executeQuery.getString(4));
                address.setState(executeQuery.getString(5));
                address.setZip(executeQuery.getInt(6));
            }
        } catch (Exception e) {
            logger.error("cannot get the address {}", e.getMessage());
        }
        return address;
    }

    @Override
    public boolean update(Address address, int id) {
        int rowUpdated=0;
        try (PreparedStatement preparedStatement = connection.prepareStatement("update  spring_vivek_address set addressId=?, addressLine1=?, addressLine2=?, city=?, state=?, zip=? where customerId=?")) {
            preparedStatement.setInt(1, address.getAddressId());
            preparedStatement.setString(2, address.getAddressLine1());
            preparedStatement.setString(3, address.getAddressLine2());
            preparedStatement.setString(4, address.getCity());
            preparedStatement.setString(5, address.getState());
            preparedStatement.setInt(6, address.getZip());
            preparedStatement.setInt(7, id);
            rowUpdated =  preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            logger.error("cannot update address {}", e.getMessage());
        }
        return rowUpdated >= 1;
    }

    @Override
    public boolean delete(int id) {
        int rowUpdated = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement("delete from spring_vivek_address where customerId=?")) {
            preparedStatement.setInt(1, id);
            rowUpdated = preparedStatement.executeUpdate();
        } catch (Exception e) {
            logger.error("cannot delete address {}", e.getMessage());
        }
        return rowUpdated >=1;
    }

    public Connection getConnection() {
        return connection;
    }

    public AddressDaoImpl setConnection(Connection connection) {
        this.connection = connection;
        return this;
    }
}
