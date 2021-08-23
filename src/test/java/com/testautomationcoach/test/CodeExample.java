package com.testautomationcoach.test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class CodeExample {

    @Test
    public void example() {

        RestAssured.get("http://localhost:5000/login")
                .then()
                .assertThat()
                    .statusCode(200)
                .and()
                    .contentType(ContentType.HTML);
    }
}
