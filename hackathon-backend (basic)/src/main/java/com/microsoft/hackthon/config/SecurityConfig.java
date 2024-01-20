package com.microsoft.hackthon.config;

import com.microsoft.hackthon.filter.JwtAuthenticationFilter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@PropertySource("classpath:my-config.properties")
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final UserDetailsService userDetailsService;

	@Bean
	ModelMapper mapper() {
		return new ModelMapper();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authz) -> authz
				.requestMatchers("/api/user/**", "/h2-console/**", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
				.anyRequest().authenticated()

		).httpBasic(withDefaults()).csrf(AbstractHttpConfigurer::disable)
				.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		http.headers(headers -> headers.frameOptions().disable());
		return http.build();
	}

	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}

}
