package com.testautomationcoach.test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class SyntacticSugar {

    @Test
    public void syntacticSugar() {
        RestAssured.get("https://api.github.com")
            .then()
                .assertThat()
                    .statusCode(HttpStatus.SC_OK)
                .and()
                    .header("Server", "GitHub.com");
    }

    @Test
    public void oldFashionedRestAssured() {
        Response response = RestAssured.get("https://api.github.com");
        String actual = response.getHeader("Server");
        assertEquals(actual, "GitHub.com");
    }

}
