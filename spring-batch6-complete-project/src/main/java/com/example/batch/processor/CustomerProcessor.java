package com.example.batch.processor;

import com.example.batch.exception.RetryableException;
import com.example.batch.exception.ValidationException;
import com.example.batch.model.Customer;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CustomerProcessor implements ItemProcessor<Customer, Customer> {
    private int retries = 0;

    public Customer process(Customer c) {
        if ("error".equalsIgnoreCase(c.getName())) throw new ValidationException("bad");
        if ("retry".equalsIgnoreCase(c.getName()) && retries < 2) {
            retries++;
            throw new RetryableException("temp");
        }
        c.setName(c.getName().toUpperCase());
        return c;
    }
}