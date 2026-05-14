package com.example.ecommerce.exception;

public class StockErrorException extends RuntimeException{
    public StockErrorException() {
        super("Product id does not exists");
    }
}
