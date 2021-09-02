package com.testautomationcoach.test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ValidacionesSimples {
    public static final String url = "http://localhost:5000";

    @Test
    public void getLoginPage() {
        RestAssured.get(url + "/login")
            .peek()
            .then()
                .assertThat()
                .statusCode(200)
            .and()
                .contentType("text/html; charset=utf-8");
    }

    @Test
    public void getRegisterPage() {
        RestAssured.get(url + "/register")
        .peek()
        .then()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
        .and()
            .contentType(ContentType.HTML);
    }

    @Test
    public  void PaginaInexitente() {
        RestAssured.get(url + "/noexite")
                .peek()
        .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .and()
                .contentType(ContentType.HTML)
                .and()
                .header("Server","Werkzeug/1.0.1 Python/3.8.11");

    }
    @Test
    public void getBuyPageNoLogin() {
        RestAssured.get(url + "/buy")
            .peek()
            .then()
            .assertThat()
                .header("Set-Cookie", startsWith("session=;"))
                .body(containsString("Log In"))
                .body(not(containsString("Log Out")))
                .statusCode(lessThan(300))
                .statusCode(equalTo(200));
    }

    @Test
    public void logIntoPage() {
        given()
                .formParam("username", "Pedro")
                .formParam("password", "Pedro")

        .when()
                .post("http://localhost:5000/login")
                .peek()
        .then()
        .statusCode(HttpStatus.SC_MOVED_TEMPORARILY)
        .contentType(ContentType.HTML)
        .header("Set-Cookie", not(startsWith("session=;")));
    }

    @Test
    public void logIntoApi() {
        String token =
                        given()
                                .body("{\n" +
                                        "    \"username\": \"Pedro\",\n" +
                                        "    \"password\": \"Pedro\"\n" +
                                        "}")
                                .header("Content-Type",ContentType.JSON)
                        .when()
                                .post("http://localhost:5000/api/login")
                                .peek()
                        .then()
                                .statusCode(200)
                                .contentType(ContentType.JSON)
                                .body("token", notNullValue())
                                .extract()
                                    .body().jsonPath().get("token");

        System.out.println("Token: " + token);

        given()
                .header("Content-Type", ContentType.JSON)
                .header("x-access-token", token)
                .body("{\n" +
                        "    \"symbol\": \"IBM\",\n" +
                        "    \"qty\": 10\n" +
                        "}")
        .when()
                .post("http://localhost:5000/api/buy")
                .peek()
        .then()
                .statusCode(HttpStatus.SC_CREATED)
                .contentType(ContentType.JSON)
                .body("message", notNullValue())
                .body("message", containsString("10"))
                .body("message", containsString("IBM"));
    }


}
