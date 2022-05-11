package com.example.furnitureStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.furnitureStore.model.Customer;
import com.example.furnitureStore.repository.CustomerRepository;

@Controller
public class RegistreController {	
	
	@Autowired
	CustomerRepository customerRepository;
	
	@RequestMapping("/registre")
	public String registre (Model model) {
		Customer customer = new Customer(); 
		model.addAttribute("customer", customer);
		return "registreForm";
	}

	@PostMapping("/addCustomer")
	public String  createCount(@ModelAttribute("customer") Customer customer){
		Customer newCustomer = customer;
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//newCustomer.setPassword(encoder.encode(customer.getPassword()));
		
		customerRepository.save(newCustomer);
		return "loginForm";
	}
}
