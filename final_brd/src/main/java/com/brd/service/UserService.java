package com.brd.service;

import java.util.List;

import com.brd.dto.UserDto;
import com.brd.entity.User;

public interface UserService {
	void createUser(UserDto dto);
	void deleteUser(String userName);
	void updateUser(UserDto dto);
	List<User> getAllUser();
	User getUserByName(String name);
	void updateUserRole(String username,List<String> roles);
	
}
