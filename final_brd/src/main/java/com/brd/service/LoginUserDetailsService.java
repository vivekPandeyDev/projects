package com.brd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.brd.dao.UserDao;
import com.brd.entity.LoginUser;
import com.brd.entity.User;

@Component
public class LoginUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = dao.getUserByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("cannot find user with given name");
		}
		return new LoginUser(user);
	}

}
