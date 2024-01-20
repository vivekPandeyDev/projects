package com.vivek.myauth.social.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                        .anyRequest().authenticated())
                .oauth2Login(withDefaults());
        return http.build();
    }

}
