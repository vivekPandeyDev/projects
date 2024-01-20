package dao;

import org.springframework.stereotype.Component;

import entity.Address;

@Component
public interface AddressDao {
    boolean save(Address t, int id);
    Address getSingleEntity(int id);
    boolean update(Address t,int id);
    boolean delete(int id);
}
