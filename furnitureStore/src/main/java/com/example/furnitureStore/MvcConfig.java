package com.example.furnitureStore;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addViewControllers (ViewControllerRegistry registry) {
		
		registry.addViewController("/registre").setViewName("registreForm");
		registry.addViewController("/controlPanel").setViewName("controlPanel");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/logout").setViewName("logout");
		registry.addViewController("/apiFurnitureStore/getProducts").setViewName("/apiFurnitureStore/getProducts");
	}
}