package com.brd.dao;

import java.util.List;

import com.brd.entity.User;

public interface UserDao {
	User getUserByName(String name);
	boolean addUser(User user);
	void removeUser(User user);
	void updateUser(User user);
	List<User> getAllUsers();
	void updateUserRole(String username,List<String> roles);
}
