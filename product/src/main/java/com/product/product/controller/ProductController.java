package com.product.product.controller;

import com.product.product.model.Product;

@RestController
@RequestMapping("/products")
public class ProductController {


    @GetMapping
    public List<Product> getAllProducts() {
      //  return repository.findAll();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
    //    return repository.save(product);
    }
}
