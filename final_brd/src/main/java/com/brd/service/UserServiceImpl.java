package com.brd.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.brd.dao.UserDao;
import com.brd.dto.UserDto;
import com.brd.entity.User;
import com.brd.exception.CustomUserException;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	private final UserDao dao;
	private final PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserDao dao, PasswordEncoder passwordEncoder) {
		super();
		this.dao = dao;
		this.passwordEncoder = passwordEncoder;
	}

	public void createUser(UserDto dto) {
		User user = new User();
		user.setUsername(dto.getUsername());
		user.setAuthorities(List.of("ROLE_MAKER"));
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user.setEmail(dto.getEmail());
		if (!dao.addUser(user)) {
			throw new CustomUserException("user already exists");
		}
	}

	public void deleteUser(String userName) {
		User user = dao.getUserByName(userName);
		if (user == null) {
			throw new CustomUserException("no user found to delete");
		} else {
			dao.removeUser(user);
		}
	}

	public void updateUser(UserDto dto) {
		User user = dao.getUserByName(dto.getUsername());
		if (user == null) {
			throw new CustomUserException("no user to update");
		}
		user.setUsername(dto.getUsername());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user.setEmail(dto.getEmail());
		dao.updateUser(user);
	}
	
	public void updateUserRole(String username,List<String> roles) {
		dao.updateUserRole(username, roles);
	}

	@Override
	public User getUserByName(String name) {
		return dao.getUserByName(name);
	}

	public List<User> getAllUser() {
		return dao.getAllUsers();
	}
}
