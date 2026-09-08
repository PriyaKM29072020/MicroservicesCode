package com.product.product.service;

import io.micrometer.core.annotation.Timed;
import org.springframework.stereotype.Component;

@Component
public class ProductService {

    @Timed(
            value = "product.processing.time",
            description = "Product Processing Time"
    )
    public void processProduct()
            throws InterruptedException {
        Thread.sleep(500);
    }
}
