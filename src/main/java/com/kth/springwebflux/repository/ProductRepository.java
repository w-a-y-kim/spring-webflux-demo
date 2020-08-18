package com.kth.springwebflux.repository;

import com.kth.springwebflux.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    /**
     * find by name
     * @param name
     * @return
     */
    List<Product> findByName(String name);
}
