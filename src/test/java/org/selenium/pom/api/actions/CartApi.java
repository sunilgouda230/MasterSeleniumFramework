package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.selenium.pom.utils.ConfigLoader;
import java.util.HashMap;
import static io.restassured.RestAssured.given;

public class CartApi {
    private Cookies cookies;

    public CartApi(){
    }

    public CartApi(Cookies cookies){
        this.cookies=cookies;
    }

    public Cookies getCookies(){
        return cookies;
    }


//    public Response addToCart(){
//        Header header = new Header("Content-Type","application/x-www-form-urlencoded");
//        Headers headers = new Headers(header);
//        HashMap<String , String> formParams = new HashMap<>();
//        formParams.put("username",user.getUsername());
//        formParams.put("email",user.getEmail());
//        formParams.put("password",user.getPassword());
//        formParams.put("woocommerce-register-nonce",fetchRegisterNonceValueUsingJsoup());
//        formParams.put("register","Register");
//
//        Response response = given().
//                baseUri(ConfigLoader.getInstance().getBaseUrl())
//                .headers(headers)
//                .formParams(formParams)
//                .cookies(cookies)
//                .log().all().
//                when().
//                post("/account").
//                then().
//                log().all().
//                extract().
//                response();
//
//        if (response.getStatusCode() != 302) {
//            throw new RuntimeException("Failed to fetch the account, HTTP status code: "+ response.getStatusCode());
//        }
//        return response;
//    }
//    }

}
