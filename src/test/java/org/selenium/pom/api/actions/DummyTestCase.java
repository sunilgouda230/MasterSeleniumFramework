package org.selenium.pom.api.actions;

import org.selenium.pom.objects.User;
import org.selenium.pom.utils.FakerUtils;

public class DummyTestCase {
    public static void main(String[] args) {
       String username = "sunil" + FakerUtils.generateRandomNumber();
        User user = new User().setUsername(username)
                .setEmail(username+"@gmail.com").setPassword(username);
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        System.out.println("Register Cookies "+signUpApi.getCookies());
        CartApi cartApi = new CartApi(signUpApi.getCookies());
        cartApi.addToCart(1215,1);
        System.out.println("Cart Cookies "+cartApi.getCookies());
    }
}
