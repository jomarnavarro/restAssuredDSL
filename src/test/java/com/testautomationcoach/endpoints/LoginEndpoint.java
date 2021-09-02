package com.testautomationcoach.endpoints;

import com.testautomationcoach.pojo.AuthInfo;
import com.testautomationcoach.pojo.Credentials;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class LoginEndpoint {

    public static AuthInfo login(String user, String password) {
        AuthInfo authInfo =
            given()
                .baseUri("http://localhost:5000")
                .contentType(ContentType.JSON)
                .body(new Credentials(user, password))
            .when()
                .post("/api/login")
                .peek()
            .then()
                .statusCode(200)
            .extract()
                .as(AuthInfo.class);
        return authInfo;
    }
}
