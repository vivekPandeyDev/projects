package com.microsoft.hackthon.service.impl;

import java.util.Optional;

import com.microsoft.hackthon.dao.UserRepository;
import com.microsoft.hackthon.entity.UserInfo;
import com.microsoft.hackthon.entity.UserInfoUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserInfoDetailService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo> userInfoOptional = userRepository.findByUserName(username);
		return userInfoOptional.map(UserInfoUserDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("no user find with given user name"));
	}
}
