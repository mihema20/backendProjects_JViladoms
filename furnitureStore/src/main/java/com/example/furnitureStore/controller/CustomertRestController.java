package com.example.furnitureStore.controller;

  import org.springframework.beans.factory.annotation.Autowired; 
  import org.springframework.http.HttpHeaders; 
  import org.springframework.http.ResponseEntity; 
  import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; 
  import org.springframework.ui.Model; 
  import org.springframework.web.bind.annotation.GetMapping; 
  import org.springframework.web.bind.annotation.ModelAttribute; 
  import org.springframework.web.bind.annotation.PostMapping; 
  import org.springframework.web.bind.annotation.RequestBody; 
  import org.springframework.web.bind.annotation.RequestMapping; 
  import org.springframework.web.bind.annotation.RequestMethod; 
  import org.springframework.web.bind.annotation.RestController; 
  import org.springframework.web.servlet.ModelAndView;
  import com.example.furnitureStore.model.Customer;
  import com.example.furnitureStore.service.CustomerUserDetailService;

	@RestController
	@RequestMapping("/apiCustomer") public class CustomertRestController{

	@Autowired 
	CustomerUserDetailService customerUserDetailService;

	@RequestMapping("/register")
	public String register(){
		return"formRegister";
	}

	@GetMapping("/register")public String showRegister(Model model){
		model.addAttribute("customer",new Customer());
		return"formRegister";
	}

	@PostMapping("/addUser")public String submissionResult(@ModelAttribute("customerForm")Customer person){
		System.out.println("*************************"+person.getUsername());
		return"result";
	}
  
  
  
  
  @RequestMapping(value= "/addCustomer", method =RequestMethod.POST) 
  public ResponseEntity<Customer> createCustomer( @RequestBody Customer customer) {
	  
	  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	  System.out.println("customer" + customer); Customer newCustomer = customer;
	  newCustomer.setPassword(encoder.encode(customer.getPassword())); Customer
	  customerSaved = customerUserDetailService.save(customer);
	  
	  var headers = new HttpHeaders(); headers.add("ResponseCreate",
	  "save Customer executed"); headers.add("version",
	  "1.0 Api Rest Customer Object"); headers.add("Executed Output",
	  "customer created");
  
	  return ResponseEntity.accepted().headers(headers).body(customerSaved);
  
  }
  
  
  }
 
