package com.example.ecommerce;

import com.example.ecommerce.api.dto.request.CreateOrderRequest;
import com.example.ecommerce.api.dto.response.OrderResponse;
import com.example.ecommerce.exception.InvalidProductException;
import com.example.ecommerce.exception.InvalidQuantityException;
import com.example.ecommerce.exception.StockErrorException;
import io.restassured.RestAssured;
import org.aspectj.weaver.ast.Var;
import org.junit.jupiter.api.*;
import org.springframework.util.Assert;

import java.math.BigDecimal;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    //java doc description

    @Test
    public void TC_OC_SC_002() {
        orderRequestDTO.setUserId(1L);
        orderRequestDTO.setProductId(2L);
        orderRequestDTO.setQuantity(1);

        var orderResponse = given()
                .contentType("application/json")
                .body(orderRequestDTO)
                .when()
                .post("/orders")
                .then()
                .statusCode(200)
                .and()
                .extract()
                .body()
                .as(OrderResponse.class);

        BigDecimal price = new BigDecimal("140.00");
        orderResponse.getItems().forEach(item-> {
            Assertions.assertEquals(2L, item.getProductId());
            Assertions.assertEquals(1, item.getQuantity());
            Assertions.assertEquals("Sample Product 2", item.getProductName());
            Assertions.assertEquals(price, item.getPrice());
        });

        Assertions.assertEquals(price, orderResponse.getTotalPrice());
        Assertions.assertEquals(1L, orderResponse.getUserId());
        Assertions.assertEquals("PENDING_PAYMENT", orderResponse.getStatus());
    }

    @Test
    public void TC_OC_SC_001() {
       given()
                .contentType("application/json")
                .body("orderRequestDTO")
                .when()
                .post("/orders")
                .then()
                .statusCode(400);
    }

    @Test
    public void TC_OC_IV_001() {
        orderRequestDTO.setUserId(1L);
        orderRequestDTO.setProductId(2L);
        orderRequestDTO.setQuantity(0);

        InvalidQuantityException ex = given()
                .contentType("application/json")
                .body(orderRequestDTO)
                .when()
                .post("/orders")
                .then()
                .extract()
                .body()
                .as(InvalidQuantityException.class);

        Assertions.assertEquals("quantity must be greater than 0", ex.getMessage());
    }

    @Test
    public void TC_OC_IV_002() {
        orderRequestDTO.setUserId(1L);
        orderRequestDTO.setProductId(2L);
        orderRequestDTO.setQuantity(0);

        InvalidProductException ex = given()
                .contentType("application/json")
                .body(orderRequestDTO)
                .when()
                .post("/orders")
                .then()
                .extract()
                .body()
                .as(InvalidProductException.class);

        Assertions.assertEquals("Product id does not exists", ex.getMessage());
    }

    @Test
    public void TC_OC_BR_001() {
        orderRequestDTO.setUserId(1L);
        orderRequestDTO.setProductId(2L);
        orderRequestDTO.setQuantity(0);

        StockErrorException ex = given()
                .contentType("application/json")
                .body(orderRequestDTO)
                .when()
                .post("/orders")
                .then()
                .extract()
                .body()
                .as(StockErrorException.class);

        Assertions.assertEquals("Product id does not exists", ex.getMessage());
    }
}
