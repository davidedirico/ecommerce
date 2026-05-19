package com.example.ecommerce.stepdefinition;


import com.example.ecommerce.api.dto.request.CreateOrderRequest;
import com.example.ecommerce.api.dto.response.OrderResponse;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.*;
import io.cucumber.junit.CucumberOptions;
import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Optional;

import static io.restassured.RestAssured.*;


@CucumberOptions(glue = "com.example.ecommerce.stepdefinition")
public class OderApiTestWithCucumber {

    private static final String BASE_URI="http://localhost/api";
    private CreateOrderRequest request = new CreateOrderRequest();
    private OrderResponse response;


    @Given("I set uri")
    public void i_set_uri() {
        RestAssured.baseURI=BASE_URI;
    }
    @Given("I create body with {string} data")
    public void i_create_body_with_data(String definition) {
        switch (definition) {
            case "negative" -> {
                request.setUserId(1L);
                request.setQuantity(-1);
                request.setProductId(1L);


            }
            case "correct" -> {
                request.setUserId(1L);
                request.setQuantity(1);
                request.setProductId(1L);

            }
            default -> {
                request.setUserId(1L);
                request.setQuantity(10);
                request.setProductId(1L);
            }
        }
    }
    @When("I create order")
    public void i_create_order() {
        response=
      given()
                .contentType("application/json")
                .body(request)
                .when()
                .post("/orders")
                .then()
                .extract()
                .body()
                .as(OrderResponse.class);
    }
    @Then("I check status code to be equal to {int}")
    public void i_check_status_code_to_be_equal_to(int int1) {

        given()
                .contentType("application/json")
                .body(request)
                .when()
                .post("/orders")
                .then()
                .statusCode(int1);

    }
    @Then("I validate response data")
    public void i_validate_response_data() {

        long value = 1L;
        Assert.assertEquals(Long.valueOf(1),response.getUserId());

    }

}
