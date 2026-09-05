package com.example.batch.exception;

public class RetryableException extends RuntimeException {
    public RetryableException(String m) {
        super(m);
    }
}