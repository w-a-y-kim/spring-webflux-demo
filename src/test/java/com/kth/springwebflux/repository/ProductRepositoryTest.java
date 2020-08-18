package com.kth.springwebflux.repository;

import com.kth.springwebflux.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository repository;

    @Test
    public void should_be_success() {
        repository.save(Product.builder().name("product1").build());
        List<Product> products = repository.findAll();
        assertTrue(products.size() == 1);
    }
}
