package com.kth.springwebflux.service;

import com.kth.springwebflux.model.Product;
import com.kth.springwebflux.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public void save(Product item) {
        repository.save(item);
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public List<Product> findName(String name) {
        return repository.findByName(name);
    }
}
