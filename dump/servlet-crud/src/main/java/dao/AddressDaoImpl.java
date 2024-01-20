package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import connection.MyConnection;
import entity.Address;

public class AddressDaoImpl implements AddressDao {
	private static final Logger logger = LogManager.getLogger(AddressDaoImpl.class);

	@Override
	public boolean addAddress(int studentId, Address address) {
		String query = "insert into vivek_address(studentId,houseNo,mainAddress,city,state,country,pincode) values(?,?,?,?,?,?,?)";
		try (Connection connection = MyConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, studentId);
			preparedStatement.setInt(2, address.getHouseNo());
			preparedStatement.setString(3, address.getMainAddress());
			preparedStatement.setString(4, address.getCity());
			preparedStatement.setString(5, address.getState());
			preparedStatement.setString(6, address.getCountry());
			preparedStatement.setInt(7, address.getPinCode());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			logger.error("cannot add address {}", e.getMessage());
			return false;
		}

		return true;
	}

	@Override
	public boolean removeAddress(int studentId) {
		String query = "delete from vivek_address where studentId=?";
		try (Connection connection = MyConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, studentId);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			logger.error("cannot remove address {}", e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public Address getAddresses(int studentId) {
		Address address = null;
		String query = "select houseNo,mainAddress,city,state,country,pincode from vivek_address where studentId=? ";
		try (Connection connection = MyConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, studentId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				int houseNo = resultSet.getInt(1);
				String mainAddress = resultSet.getString(2);
				String city = resultSet.getString(3);
				String state = resultSet.getString(4);
				String country = resultSet.getString(5);
				int pincode = resultSet.getInt(6);
				address = new Address(houseNo, mainAddress, pincode, city, state, country);
			}
		} catch (Exception e) {
			logger.error("cannot get address {}", e.getMessage());

		}

		return address;

	}

	@Override
	public boolean updateAdress(int studentId, Address address) {
		String query = "update   vivek_address  set houseNo=?, mainAddress=?, city =?, state=?, country=?, pincode=? where studentId=?";
		try (Connection connection = MyConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, address.getHouseNo());
			preparedStatement.setString(2, address.getMainAddress());
			preparedStatement.setString(3, address.getCity());
			preparedStatement.setString(4, address.getState());
			preparedStatement.setString(5, address.getCountry());
			preparedStatement.setInt(6, address.getPinCode());
			preparedStatement.setInt(7, studentId);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			logger.error("cannot update address {}", e.getMessage());
			return false;
		}

		return true;
	}

}
