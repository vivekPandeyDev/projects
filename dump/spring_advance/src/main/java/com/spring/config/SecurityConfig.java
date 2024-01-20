package com.spring.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	@Qualifier("dataSource2")
	DataSource dataSource;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(
				User.withUsername("vivek").password(passwordEncoder().encode("vivek123")).roles("ADMIN").build());
		manager.createUser(
				User.withUsername("shivam").password(passwordEncoder().encode("shivam123")).roles("USER").build());
		return manager;
	}

	/*
	 * Remove comment this function to use in memory user detail manager
	 */
//	@Override
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder());
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").antMatchers("/customer/**", "/loan/**")
				.hasAnyRole("ADMIN", "USER").antMatchers("/**").permitAll().and().formLogin().and().logout()
				.logoutSuccessUrl("/").and().exceptionHandling().accessDeniedPage("/access-denied");

	}

}
