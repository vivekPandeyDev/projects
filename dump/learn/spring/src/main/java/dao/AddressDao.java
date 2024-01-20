package dao;

import entity.Address;

public interface AddressDao {
    boolean save(Address t, int id);
    Address getSingleEntity(int id);
    boolean update(Address t,int id);
    boolean delete(int id);
}
