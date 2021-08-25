package com.testautomationcoach.test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class HeaderTest {


    public static final String baseURL = "http://vamonos-finance.herokuapp.com";

    @Test
    public void getLoginPage() {
        RestAssured.get(baseURL + "/login")
                .prettyPeek()
                .then()
                .assertThat()
                    .statusCode(equalTo(200))
                    .statusCode(anyOf(equalTo(200), equalTo(201)))
                    .contentType(ContentType.HTML);
    }

    @Test
    public void getBuyPageNoLogin2() {
        RestAssured.get(baseURL + "/buy")
            .peek()
            .then()
            .assertThat()
                .statusCode(equalTo(200))
            .and()
                .contentType(is("text/html; charset=utf-8"))
            .and()
                .header("Set-Cookie", containsStringIgnoringCase("session=;"));
    }

    @Test
    public void getMainPage() {
        RestAssured.get(baseURL)
                .prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.HTML);
    }

    @Test
    public void getRegisterPage() {
        RestAssured.get(baseURL + "/register")
                .prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.HTML);
    }

    @Test
    public void getQuotePageNoLogin() {
        RestAssured.get(baseURL + "/quote")
                .prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.HTML)
                .and()
                .header("Set-Cookie", containsStringIgnoringCase("session=;"))
                .and()
                .body(containsStringIgnoringCase("Log In"));
    }

    @Test
    public void getBuyPageNoLogin() {
        RestAssured.get(baseURL + "/buy")
                .prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.HTML)
                .and()
                .body(containsStringIgnoringCase("Log In"));
    }
    @Test
    public void extractHeaders() {
        Headers headers = RestAssured.get(baseURL)
                .prettyPeek()
                .then()
                .extract()
                .headers();

        if(headers.hasHeaderWithName("Set-Cookie")) {
            String cookieHeader = headers.getValue("Set-Cookie");
            System.out.println(cookieHeader);
        }

    }

    @Test
    public void getAuthCookie() {
        String authCookie =
            given()
                .formParam("username", "Pedro")
                .formParam("password", "Pedro")
            .when()
                .post("http://vamonos-finance.herokuapp.com/login")
                .prettyPeek()
            .then()
                .statusCode(302)
            .extract()
                .header("Set-Cookie");

        System.out.println("##########################");
        System.out.println(authCookie);
        System.out.println("##########################");

        given()
            .header("Set-Cookie", authCookie)
        .when()
            .get("http://vamonos-finance.herokuap.com/quote")
            .prettyPeek()
        .then()
            .statusCode(200)
            .contentType(ContentType.HTML);
    }
}
