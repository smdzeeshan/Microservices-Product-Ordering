package com.mac.productservice.repository;

import com.mac.productservice.dto.ProductResponse;
import com.mac.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    Optional<ProductResponse> findByName(String name);
}
