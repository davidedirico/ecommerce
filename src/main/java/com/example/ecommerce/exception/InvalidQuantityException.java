package com.example.ecommerce.exception;

public class InvalidQuantityException extends RuntimeException {
    public InvalidQuantityException() {
        super("quantity must be greater than 0");
    }
}
