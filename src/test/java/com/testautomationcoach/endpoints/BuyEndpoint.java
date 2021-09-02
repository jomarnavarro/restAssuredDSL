package com.testautomationcoach.endpoints;

import com.testautomationcoach.pojo.AuthInfo;
import com.testautomationcoach.pojo.BuyBody;
import com.testautomationcoach.pojo.BuyResponseBody;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class BuyEndpoint {

    public static BuyResponseBody buyStock(String symbol, int qty, AuthInfo authInfo) {
        return given()
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
}
