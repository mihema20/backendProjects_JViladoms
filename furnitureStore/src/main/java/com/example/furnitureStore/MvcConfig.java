package com.example.furnitureStore;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	
	//let s configure paths and routes of my project
	//to define home and hello
	@Override
	public void addViewControllers (ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/registre").setViewName("registreForm");
		registry.addViewController("/controlPanel").setViewName("controlPanel");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/logout").setViewName("logout");
		registry.addViewController("/apiFurnitureStore/getProducts").setViewName("/apiFurnitureStore/getProducts");
		registry.addViewController("/index").setViewName("/index");
	}
	

}
