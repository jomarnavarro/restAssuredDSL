package com.testautomationcoach.test;

import com.testautomationcoach.endpoints.BuyEndpoint;
import com.testautomationcoach.endpoints.LoginEndpoint;
import com.testautomationcoach.pojo.*;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertTrue;

public class ApiExamples {

    @Test
    public void apiLogin() {
        String token =
                        given()
                            .baseUri("http://localhost:5000")
                            .contentType(ContentType.JSON)
                            .body(new Credentials("Pedro", "Pedro"))
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
    PortfolioPayload pp =
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
//            .body("stocks[0].symbol", equalTo("AMZN"))
//            .body("stocks[1].symbol", equalTo("MSFT"))
//            .body("stocks.symbol", containsInAnyOrder("MSFT", "AMZN"))
        .extract()
            .as(PortfolioPayload.class);

        System.out.println("Hello World!");
    }

    @Test
    public void loginHappierPath() {
        AuthInfo authInfo = LoginEndpoint.login("Pedro", "Pedro");
        System.out.println(authInfo.getToken());
    }

    @Test
    public void buyStockHappierPath() {
        AuthInfo authInfo = LoginEndpoint.login("Pedro", "Pedro");
        BuyResponseBody brb = BuyEndpoint.buyStock("MSFT", 1, authInfo );
        System.out.println(brb.getMessage());
    }



    @Test
    public void buyHappyPath() {
        String token = getToken();
        given()
                .header("x-access-token", token)
                .contentType(ContentType.JSON)
                .body(new BuyBody("MSFT", 10))
                .baseUri("http://localhost:5000")
        .when()
                .post("/api/buy")
                .peek()
        .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("message",notNullValue())
                .body("message",equalTo("You bought 10 share(s) of MSFT."));
        ;


    }

    @Test
    public void getCellHapyPath(){
        String token = getToken();
        given()
                .header("x-access-token",token)
                .contentType(ContentType.JSON)
                .body(new SellBody("MSFT", 10))
                .baseUri("http://localhost:5000")
        .when()
                .post("/api/sell")
                .peek()
        .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("message", notNullValue())
                .body("message", containsString("MSFT"))
                .body("message",containsString("10"))
        ;

    }

    @Test
    public void getQuoteHappyPath(){
        String token = getToken();
        QuoteResponseBody quote =
        given()
                .header("x-access-token",token)
                .contentType(ContentType.JSON)
                .body(new QuoteBody("MSFT"))
                .baseUri("http://localhost:5000")
        .when()
                .post("/api/quote")
                .peek()
        .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("name", notNullValue())
                .body("name", equalTo("Microsoft Corporation"))
                .body("price", notNullValue())
//                .body("price", greaterThan(300.0))
                .body("symbol", notNullValue())
                .body("symbol", equalTo("MSFT"))
            .extract()
                .as(QuoteResponseBody.class)
        ;
        assertTrue(quote.getName().equalsIgnoreCase("Microsoft Corporation"));
        assertTrue(quote.getSymbol().equalsIgnoreCase("MSFT"));
        assertTrue(quote.getPrice() > 300.00);

    }

    public String getToken() {
        AuthInfo authInfo =
            given()
                .baseUri("http://localhost:5000")
                .contentType(ContentType.JSON)
                .body(new Credentials("Pedro", "Pedro"))
            .when()
                .post("/api/login")
                .peek()
            .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("token", notNullValue())
                .extract()
                    .as(AuthInfo.class);
        return authInfo.getToken();
    }
}
