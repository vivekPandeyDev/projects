package com.project.contact.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.contact.dto.UserInfoUserDetails;
import com.project.contact.entity.UserInfo;
import com.project.contact.repo.UserRepository;


@Service
public class UserInfoDetailService implements UserDetailsService {
	@Autowired
	private  UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserInfo>  userInfoOptional = userRepository.findByUserName(username);
		return userInfoOptional.map(UserInfoUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("no user find with given user name"));
	}
}
