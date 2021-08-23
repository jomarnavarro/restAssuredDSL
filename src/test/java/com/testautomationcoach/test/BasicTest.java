package com.testautomationcoach.test;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

public class BasicTest {

    @Test
    public void someGithubTest(){
        RestAssured.get("https://api.github.com")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
