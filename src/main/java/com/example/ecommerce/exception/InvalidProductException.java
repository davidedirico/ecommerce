package com.example.ecommerce.exception;

public class InvalidProductException extends RuntimeException{
    public InvalidProductException () {
        super ("Product id does not exists");
    }
}
