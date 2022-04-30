package com.example.furnitureStore.controller;

import java.io.IOException;
import java.util.Optional;

import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.example.furnitureStore.model.Product;
import com.example.furnitureStore.service.ProductService;

@RestController
@RequestMapping("/apiFurnitureStore")
public class ProductRestController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/addProduct")
	public ResponseEntity<Product> createProduct(
								 @RequestParam String location,
								 @RequestParam String title,
								 @RequestParam String description,
								 @RequestParam double price, @RequestParam MultipartFile file) throws IOException{
		Product product = new Product();
		product.setLocation(location);
		product.setTitle(title);
		product.setDescription(description);
		product.setImage( new Binary(file.getBytes() ));
		product.setPrice(price);
		productService.save(product);
		
		var headers = new HttpHeaders();
		headers.add("ResponseCreate", "save Product executed");
		headers.add("version", "1.0 Api Rest Product Object");
		headers.add("Executed Output", "product created");
		
		return ResponseEntity.accepted().headers(headers).body(product);
	}
	
	@GetMapping("/getProducts")
	public ResponseEntity<Iterable<Product>> getAllProduct() {
		var headers = new HttpHeaders();
		headers.add("ResponseGet", "findAll products executed");
		headers.add("version", "1.0 Api Rest Product Object");
		
		return ResponseEntity.accepted().headers(headers).body(productService.findAll());
	}
	
	@GetMapping("/getProducts/{location}")
	public ResponseEntity<Iterable<Product>> getAllProductLocation(@PathVariable String location) {
		var headers = new HttpHeaders();
		headers.add("ResponseGet", "findAll products executed");
		headers.add("version", "1.0 Api Rest Product Object");
		
		return ResponseEntity.accepted().headers(headers).body(productService.findProductLocation(location));
	}
	
	@GetMapping("/getProductImage")
	public ResponseEntity<byte[]> getEmployeeImage (@RequestParam String productId) {
		
		Optional<Product> product = productService.findByProductId(productId);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		
		return new ResponseEntity<>( product.get().getImage().getData(), headers, HttpStatus.OK );
	}
	
	@PostMapping("/updateProduct/{productId}")
	public ResponseEntity<Product> updateProduct (@PathVariable String productId, 
											   @RequestParam String location,
											   @RequestParam String title,
											   @RequestParam String description,
											   @RequestParam double price, @RequestParam MultipartFile file) throws IOException {
		
		String responseUpdate = "";
		Optional<Product> productoUpdate = productService.findByProductId(productId);
		Product productToUpdate =null;
		
		if ( productoUpdate.isPresent() ) {
		
			productToUpdate = productoUpdate.get();
			
			Product productFromRest = new Product();
			productFromRest.setProductId(productId);
			productFromRest.setLocation(location);
			productFromRest.setTitle(title);
			productFromRest.setDescription(description);
			productFromRest.setImage(new Binary(file.getBytes()));
			productFromRest.setPrice(price);
			
			responseUpdate += "product found";
			boolean updated = false;
			
			if  (productFromRest.getLocation() != null) {
				responseUpdate += " - product location value updated: " + productFromRest.getLocation() +  "( old value: " + productToUpdate.getLocation() + ")" ;
				productToUpdate.setLocation(productFromRest.getLocation());
				updated = true;
			}
			if  (productFromRest.getTitle() != null) {
				responseUpdate += " - product title value updated: " + productFromRest.getTitle() +  "( old value: " + productToUpdate.getTitle() + ")" ;
				productToUpdate.setTitle(productFromRest.getTitle());
				updated = true;
			}
			if  (productFromRest.getDescription() != null) {
				responseUpdate += " - product description value updated: " + productFromRest.getDescription() +  "( old value: " + productToUpdate.getDescription() + ")" ;
				productToUpdate.setDescription(productFromRest.getDescription());
				updated = true;
			}
			if  (productFromRest.getImage() != null) {
				responseUpdate += " - product image updated: ";
				productToUpdate.setImage(productFromRest.getImage());
				updated = true;
			}
			if  (productFromRest.getPrice() != 0) {
				responseUpdate += " - price double value updated: " + productFromRest.getPrice() +  "( old value: " + productToUpdate.getPrice() + ")" ;
				productToUpdate.setPrice(productFromRest.getPrice());
				updated = true;
			}
			
			if (!updated) responseUpdate += " - try to update but any field updated - something wrong happened";
			else productService.save(productToUpdate);
		
		} else {
			
			responseUpdate = responseUpdate + "product not found";}
		
		var headers = new HttpHeaders();
		headers.add("ResponseUpdate", "updateProduct executed");
		headers.add("version", "1.0 Api Rest Product Object");
		headers.add("Executed Output", responseUpdate);
		
		return ResponseEntity.accepted().headers(headers).body(productToUpdate);	
	}
	
	@DeleteMapping ("/deleteProduct/{id}")
	public ResponseEntity<Product> deleteProduct  (@PathVariable String id ) {
		
		Optional<Product> productToDelete = productService.findByProductId(id);
		String responsedelete = productService.findAndDeleteById(id);

		var headers = new HttpHeaders();
		headers.add("ResponseDeleted", "deleteProduct executed");
		headers.add("version", "1.0 Api Rest Product Object");
		headers.add("Executed Output", responsedelete);
		
		return ResponseEntity.accepted().headers(headers).body(productToDelete.get());
	}
}
