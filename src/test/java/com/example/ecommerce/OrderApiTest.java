package com.example.ecommerce;

import com.example.ecommerce.api.dto.request.CreateOrderRequest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class OrderApiTest {

    private static final String BASE_URI = "http://localhost:8080/api";
    private CreateOrderRequest orderRequestDTO;


    @BeforeAll
    public static void init() {
        RestAssured.baseURI = BASE_URI;
        System.out.println("BeforeAll");
    }

    @BeforeEach
    public void createOrderRequest() {
        orderRequestDTO = new CreateOrderRequest();
        System.out.println("BeforeEach");
    }

    @Test
    public void orderCreatedStatusCodeTest () {
        orderRequestDTO.setUserId(1L);
        orderRequestDTO.setProductId(2L);
        orderRequestDTO.setQuantity(1);

        given()
                .contentType("application/json")
                .body(orderRequestDTO)
                .when()
                .post("/orders")
                .then()
                .statusCode(200);
    }
}
