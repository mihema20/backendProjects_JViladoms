package com.example.furnitureStore.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


import com.example.furnitureStore.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

	List<Product> findItemByTitle(String title);
	
	@Query("{location: ?0 }")
	Iterable<Product> findProductLocation(String location);
}
