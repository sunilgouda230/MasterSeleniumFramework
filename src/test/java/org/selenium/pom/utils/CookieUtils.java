package org.selenium.pom.utils;

import io.restassured.http.Cookie;
import io.restassured.http.Cookies;

import java.util.ArrayList;
import java.util.List;

public class CookieUtils {

    public List<org.openqa.selenium.Cookie> convertRestAssuredCookieToSeleniumCookie(Cookies cookies){
        List<Cookie> restAssuredCookie = new ArrayList<>();
        restAssuredCookie = cookies.asList();
        List<org.openqa.selenium.Cookie> seleniumCookie = new ArrayList<>();

        for (Cookie cookie: restAssuredCookie) {
            seleniumCookie.add(new org.openqa.selenium.Cookie(cookie.getName(),cookie.getValue(),
                    cookie.getDomain(),cookie.getPath(),cookie.getExpiryDate(),cookie.isSecured(),cookie.isHttpOnly(),
                    cookie.getSameSite()));
        }

        return seleniumCookie;
    }
}
