package com.vanlife.manage.controller;

import com.vanlife.manage.dto.JwtAuthenticationResponse;
import com.vanlife.manage.dto.LoginRequest;
import com.vanlife.manage.entity.Role;
import com.vanlife.manage.entity.User;
import com.vanlife.manage.repository.RoleRepository;
import com.vanlife.manage.repository.UserRepository;
import com.vanlife.manage.service.JwtTokenProvider;
import com.vanlife.manage.service.UserDetailsServiceImpl;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token
        String jwt = jwtTokenProvider.generateToken(authentication);

        // Return the JWT token as a response
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody LoginRequest registrationRequest) {
        if (userDetailsService.existsByUsername(registrationRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }

        // Create a new user
        User user = new User();
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        Role role1 = new Role("ROLE_ADMIN");
        roleRepository.save(role1);
        Set<Role> roles = new HashSet<>();
        roles.add(role1);
        user.setRoles(roles);
        userRepository.save(user);


        // Authenticate the newly registered user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(registrationRequest.getUsername(), registrationRequest.getPassword()));

        // Generate JWT token
        String jwt = jwtTokenProvider.generateToken(authentication);

        // Return the JWT token as a response
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }
}
