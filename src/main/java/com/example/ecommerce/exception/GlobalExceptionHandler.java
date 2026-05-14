package com.example.ecommerce.exception;

import com.example.ecommerce.api.dto.response.OrderResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler(InvalidQuantityException.class)
   public ResponseEntity<OrderResponseError> handleInvalidQuantityException(InvalidQuantityException ex) {

       OrderResponseError error = new OrderResponseError(ex.getMessage());
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(StockErrorException.class)
    public ResponseEntity<OrderResponseError> handleStockError(StockErrorException ex) {

       OrderResponseError error = new OrderResponseError(ex.getMessage());
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InvalidProductException.class)
    public ResponseEntity<OrderResponseError> handleInvalidProductException (InvalidProductException ex){

       OrderResponseError error = new OrderResponseError(ex.getMessage());
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
