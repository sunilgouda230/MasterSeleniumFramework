package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.selenium.pom.constants.EndPoint;
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


    public Response addToCart(int productid, int quantity){
        Header header = new Header("Content-Type","application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        HashMap<String , Object> formParams = new HashMap<>();
        formParams.put("product_sku","");
        formParams.put("product_id",productid);
        formParams.put("quantity",quantity);
        formParams.put("register","Register");

        if (cookies == null){
            cookies = new Cookies();
        }

        Response response = ApiRequest.post(EndPoint.ADD_TO_CART.url,headers,formParams,cookies);

        if (response.getStatusCode() != 200) {
            throw new RuntimeException("Failed to fetch the account, HTTP status code: "+ response.getStatusCode());
        }
        this.cookies = response.detailedCookies();
        return response;
    }
}
