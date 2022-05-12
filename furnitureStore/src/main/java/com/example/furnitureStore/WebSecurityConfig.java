package com.example.furnitureStore;

import javax.annotation.Resource;

import javax.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource
	private UserDetailsService userDetailsService;

	// here we are going to define which sites are public and private //so let s
	//authorize somesites and others not 
	// let define which one does the login and
	//which on does the logout 
	// besides we MUST users and passwords

	@Override 
	protected void configure (HttpSecurity http) throws Exception {
  
  //auth.authenticationProvider(authProvider()); 
		http 
			.authorizeRequests()
			.antMatchers("/registre", "/apiFurnitureStore/getProducts", "/index", "/js/**", "/css/**").permitAll()
			
				.anyRequest().authenticated()
				.and() 
			.formLogin()
				.loginPage("/login") 
				.permitAll()
				.defaultSuccessUrl("/controlPanel")
				.and() 
			.logout() 
				.logoutSuccessUrl("/login");  
		http.csrf().disable();
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
 