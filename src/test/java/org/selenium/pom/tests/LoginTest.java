package org.selenium.pom.tests;

import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Products;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CheckOutPage;
import org.selenium.pom.utils.FakerUtils;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Test
    public void loginDuringCheckOut() throws IOException {
        String username = "sunil" + FakerUtils.generateRandomNumber();
        User user = new User().setUsername(username)
                .setEmail(username+"@gmail.com").setPassword(username);
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        Products products = new Products(1215);
        CartApi cartApi = new CartApi(signUpApi.getCookies());
        cartApi.addToCart(products.getId(),1);
        CheckOutPage checkOutPage = new CheckOutPage(getDriver()).load();
        injectCookieToTheBrowser(signUpApi.getCookies());
        checkOutPage.load();
        Assert.assertTrue(checkOutPage.getProductItem().contains(products.getName()));
    }
}

