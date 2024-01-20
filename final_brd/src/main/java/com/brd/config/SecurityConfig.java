package com.brd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.brd.service.LoginUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	protected LoginUserDetailsService loginUserDetailsService() {
		return new LoginUserDetailsService();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/user/admin/**").hasRole("ADMIN")
			.antMatchers("/maker/**").hasRole("MAKER")
			.antMatchers("/checker/**").hasAnyRole("CHECKER")
			.antMatchers("/user/action/**").hasAnyRole("MAKER","CHECKER")
			.antMatchers("/**").permitAll()
			.and()
			.formLogin()
			.loginPage("/user").loginProcessingUrl("/login").successForwardUrl("/").failureForwardUrl("/loginFailed")
			.and().logout().logoutUrl("/logout")
			.and().exceptionHandling().accessDeniedPage("/access-denied");

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginUserDetailsService()).passwordEncoder(passwordEncoder());
	}

}
