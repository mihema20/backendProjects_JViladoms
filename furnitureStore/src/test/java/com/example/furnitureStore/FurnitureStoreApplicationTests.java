package com.example.furnitureStore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.furnitureStore.model.Customer;
import com.example.furnitureStore.repository.CustomerRepository;

@SpringBootTest
class FurnitureStoreApplicationTests {
	
	@Autowired
	CustomerRepository customerRepository;

	@Test
	public void createCustomer() {
		/*BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		customerRepository.save(new Customer("Daniel ","LaRusso","the Miyagi-Do@gmail.com","admin",encoder.encode("admin")));*/
	}
	
	@Test
	void contextLoads() {
	}

}
