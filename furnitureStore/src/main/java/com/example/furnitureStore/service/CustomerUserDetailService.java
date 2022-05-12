package com.example.furnitureStore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.furnitureStore.model.Customer;
import com.example.furnitureStore.repository.CustomerRepository;

@Service
public class CustomerUserDetailService implements UserDetailsService {

	@Autowired
	private CustomerRepository customerRepository;

	public Customer save(Customer customer) {
		customerRepository.save(customer);
		return customer;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		final Customer customer = customerRepository.findByUsername(username).get();
		if (customer == null) {
			throw new UsernameNotFoundException(username);
		}
		UserDetails user = User.withUsername(customer.getUsername()).password(customer.getPassword())
				.authorities("USER").build();
		return user;
	}

}
 

