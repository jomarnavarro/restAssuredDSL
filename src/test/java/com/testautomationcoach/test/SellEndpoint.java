package com.testautomationcoach.test;

import com.testautomationcoach.pojo.AuthInfo;
import com.testautomationcoach.pojo.SellBody;
import com.testautomationcoach.pojo.SellResponseBody;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;

public class SellEndpoint {
    public static SellResponseBody sellStock(String symbol, int qty, AuthInfo authInfo) {
        return given()
                .header("x-access-token",authInfo.getToken())
                .contentType(ContentType.JSON)
                .body(new SellBody(symbol, qty))
                .baseUri("http://localhost:5000")
                .when()
                .post("/api/sell")
                .peek()
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .extract()
                .as(SellResponseBody.class)
                ;
    }
}
