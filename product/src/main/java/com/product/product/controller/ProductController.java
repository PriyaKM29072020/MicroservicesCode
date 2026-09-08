package com.product.product.controller;

import com.product.product.model.Product;
import com.product.product.service.ProductService;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
@Autowired
    ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() throws InterruptedException {
        Thread.sleep(60000);
        productService.processProduct();
        return new ArrayList<>();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
    //    return repository.save(product);
        return new Product();
    }
}
