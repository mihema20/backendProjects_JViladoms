package com.example.furnitureStore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.furnitureStore.model.Product;
import com.example.furnitureStore.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;

	// CRUD basic operations
	public Iterable<Product> findAll() {
		return productRepository.findAll();
	}

	public Product save(Product product) {
		productRepository.save(product);
		return product;
	}

	public String findAndDeleteById(String productId) {

		String response = "";
		Optional<Product> productFound = productRepository.findById(productId);

		if (productFound.isPresent()) {

			productRepository.delete(productFound.get());
			response += "productFound deleted";
		} else {

			response += "productFound not found";
		}

		return response;
	}

	public void deleteById(String productId) {
		productRepository.deleteById(productId);
	}
	
	// other options
	public long count() {
		long quantity = productRepository.count();
		return quantity;
	}

	public boolean existsById(String productId) {
		boolean isProduct = productRepository.existsById(productId);
		return isProduct;
	}
	
	public List<Product>findItemByTitle(String title){
		return productRepository.findItemByTitle(title);
	}

	public Optional<Product> findByProductId(String productId) {
		return productRepository.findById(productId);
	}

	public Iterable<Product> findProductLocation(String location) {
		
		return productRepository.findProductLocation(location);
	}

	
	
}
