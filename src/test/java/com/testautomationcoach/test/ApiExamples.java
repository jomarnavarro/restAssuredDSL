package com.testautomationcoach.test;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiExamples {

    @Test
    public void apiLogin() {
        String token =
                        given()
                            .baseUri("http://localhost:5000")
                            .contentType(ContentType.JSON)
                            .body("{\"username\": \"Pedro\", \"password\": \"Pedro\"}")
                        .when()
                            .post("/api/login")
                            .peek()
                        .then()
                            .assertThat()
                            .statusCode(HttpStatus.SC_OK)
                            .contentType(ContentType.JSON)
                            .body("token", notNullValue())
                        .extract()
                            .body().jsonPath().get("token");
    }

    @Test
    public void portFolioTest() {
        String token = getToken();

        given()
            .baseUri("http://localhost:5000")
            .header("x-access-token", token)
        .when()
            .get("/api/portfolio")
            .peek()
        .then()
            .assertThat()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("cash", notNullValue())
            .body("position", notNullValue())
            .body("stocks", notNullValue())
            .body("stocks[0].symbol", equalTo("AMZN"))
            .body("stocks[1].symbol", equalTo("MSFT"))
            .body("stocks.symbol", containsInAnyOrder("MSFT", "AMZN"));
    }

    public String getToken() {
        return
            given()
                .baseUri("http://localhost:5000")
                .contentType(ContentType.JSON)
                .body("{\"username\": \"Pedro\", \"password\": \"Pedro\"}")
            .when()
                .post("/api/login")
                .peek()
            .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("token", notNullValue())
                .extract()
                .body().jsonPath().get("token");
    }
}
