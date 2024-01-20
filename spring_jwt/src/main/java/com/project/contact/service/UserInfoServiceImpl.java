package com.project.contact.service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.contact.dto.ErrorResponse;
import com.project.contact.entity.UserInfo;
import com.project.contact.exception.CustomUserException;
import com.project.contact.repo.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private static final String USER_NOT_FOUND = "no user found with given user name : {0}";

	@Override
	public UserInfo save(UserInfo user) {
		Optional<UserInfo> dbUser = userRepository.findByUserName(user.getUserName());
		if (dbUser.isPresent()) {
			ErrorResponse errorResponse = ErrorResponse.builder().status(HttpStatus.FOUND)
					.message(MessageFormat.format("user is already present with given user name: {0}", user.getUserName()))
					.build();
			throw new CustomUserException(errorResponse);
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return user;
	}

	@Override
	public UserInfo get(String userName) {
		Optional<UserInfo> userinfoOptional = userRepository.findByUserName(userName);
		if (userinfoOptional.isEmpty()) {
			ErrorResponse errorResponse = ErrorResponse.builder().status(HttpStatus.NOT_FOUND)
					.message(MessageFormat.format(USER_NOT_FOUND, userName)).build();
			throw new CustomUserException(errorResponse);
		}
		return userinfoOptional.get();
	}
	
	@Override
	public List<UserInfo> getAll() {
		return userRepository.findAll();
		
	}

	@Override
	public UserInfo delete(String userName) {
		Optional<UserInfo> userinfoOptional = userRepository.findByUserName(userName);
		if (userinfoOptional.isEmpty()) {
			ErrorResponse errorResponse = ErrorResponse.builder().status(HttpStatus.NOT_FOUND)
					.message(MessageFormat.format(USER_NOT_FOUND, userName)).build();
			throw new CustomUserException(errorResponse);
		}
		userRepository.delete(userinfoOptional.get());
		return userinfoOptional.get();
	}

}
