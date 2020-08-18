package com.kth.springwebflux.controller;


import com.kth.springwebflux.model.Product;
import com.kth.springwebflux.service.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@AutoConfigureWebTestClient
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductControllerTest {
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private ProductService service;

    private final int productSize = 10;

    @BeforeAll
    public void setUp() {
        for(int i = 0; i < productSize; i++) {
            service.save(Product.builder().name("product" + i).build());
        }
    }

    @Test
    public void should_success_findAll() {
        webTestClient.get().uri("/api/v1/product").exchange()
                .expectStatus().isOk()
                .expectBodyList(Product.class).hasSize(productSize);
    }

    @Test
    public void should_success_findName() {
        final String productName = "product1";
        webTestClient.get().uri("/api/v1/product/name/" + productName).exchange()
                .expectStatus().isOk()
                .expectBodyList(Product.class).consumeWith(response -> {
                    Set<String> nameSet = response.getResponseBody().stream().map(i -> i.getName()).collect(Collectors.toSet());
                    assertTrue(nameSet.contains(productName));
        });
    }
}
