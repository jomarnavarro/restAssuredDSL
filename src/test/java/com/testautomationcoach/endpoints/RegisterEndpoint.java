package com.testautomationcoach.endpoints;

import com.testautomationcoach.pojo.*;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RegisterEndpoint {

    public static BuyResponseBody buyStock(String symbol, int qty, AuthInfo authInfo) {
        return
                given()
                        .header("x-access-token", authInfo.getToken())
                        .body(new BuyBody(symbol, qty))
                        .contentType(ContentType.JSON)
                        .baseUri("http://localhost:5000")
                        .when()
                        .post("/api/buy")
                        .peek()
                        .then()
                        .statusCode(HttpStatus.SC_CREATED)
                        .contentType(ContentType.JSON)
                        .extract()
                        .as(BuyResponseBody.class);

    }


    public static RegisterResponseBody registerNewUser(String user, String password, AuthInfo authInfo) {
        return
        given()
                .header("x-access-token",authInfo.getToken())
                .contentType(ContentType.JSON)
                .body(new Credentials(user,password))
                .baseUri("http://localhost:5000")
                .when()
                .post("/api/register")
                .peek()
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)

                .extract()
                .as(RegisterResponseBody.class)
        ;
    }
}
