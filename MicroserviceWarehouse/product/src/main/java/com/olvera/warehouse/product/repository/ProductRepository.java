package com.olvera.warehouse.product.repository;

import com.olvera.warehouse.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
