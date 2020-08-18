package com.kth.springwebflux.controller;

import com.kth.springwebflux.model.Product;
import com.kth.springwebflux.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ProductController {
    private final ProductService service;

    @GetMapping("/product")
    public Flux<Product> getProducts() {
        log.debug("call select products");
        return Flux.fromIterable(service.findAll());
    }

    @GetMapping("/product/name/{name}")
    public Flux<Product> getProductsByName(@PathVariable String name) {
        return Flux.fromIterable(service.findName(name));
    }
}
