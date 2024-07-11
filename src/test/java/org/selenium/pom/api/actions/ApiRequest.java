package org.selenium.pom.api.actions;

import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.selenium.pom.utils.ConfigLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class ApiRequest extends SpecBuilder{

    public static Response post(String endpoint, Headers headers, HashMap<String,Object> formParams,
                                Cookies cookies){
        return given(getRequestSpec())
                .headers(headers)
                .formParams(formParams)
                .cookies(cookies)
                .log().all().
        when().
                post(endpoint).
        then().
                spec(getResponseSpec()).
                extract().
                response();
    }

    public static Response get(String endpoint, Cookies cookies){
        return given(getRequestSpec()).
                cookies(cookies).
        when().
                get(endpoint).
        then().
                spec(getResponseSpec()).
                extract().
                response();
    }
}
