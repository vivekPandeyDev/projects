package dao;

import entity.Address;

public interface AddressDao {
	boolean addAddress(int studentId, Address address);
	boolean removeAddress(int studentId);
	Address getAddresses(int studentId);
	boolean updateAdress(int studentId, Address address);

}
