package com.hry.persistence.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.hry.persistence.mongo.document.ProductModel;

public interface ProductRepository extends MongoRepository<ProductModel,String>{
	
	@Query("{$or:[{brand : {$regex : ?0}}, {description : {$regex : ?1}}]}")
	List<ProductModel> findByPalindromeCondition(String brand, String condition);
}
