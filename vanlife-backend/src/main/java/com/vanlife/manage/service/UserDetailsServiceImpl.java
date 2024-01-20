package com.vanlife.manage.service;


import com.vanlife.manage.entity.User;
import com.vanlife.manage.entity.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vanlife.manage.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return UserPrincipal.create(user);
    }

    public UserDetails loadUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));
        return UserPrincipal.create(user);
    }

    public boolean existsByUsername(String userName){
       return  userRepository.findByUsername(userName).isPresent();
    }
}
