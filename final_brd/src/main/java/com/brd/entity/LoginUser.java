package com.brd.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class LoginUser implements UserDetails {
	
	private static final long serialVersionUID = 5758705075775721402L;
	private User user;
	
	public LoginUser(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for(String role : user.getAuthorities()) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public User getUser() {
		return user;
	}
	
	

	
}
