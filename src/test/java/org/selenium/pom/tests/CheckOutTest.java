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

public class CheckOutTest extends BaseTest {

    @Test
    public void guestCheckOutUsingDirectBankTransfer() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);
        CheckOutPage checkOutPage = new CheckOutPage(getDriver()).load();
        Products products = new Products(1215);
        CartApi cartApi = new CartApi();
        cartApi.addToCart(products.getId(),1);
        injectCookieToTheBrowser(cartApi.getCookies());

        checkOutPage.load().
                setBillingAddress(billingAddress).
                selectDirectBank().
                placeOrder();
        Assert.assertEquals(checkOutPage.viewOrderReceiveMessage(), "Thank you. Your order has been received.");
    }

    @Test
    public void loginAndCheckOutUsingDirectBankTransfer() throws IOException, InterruptedException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);
        String username = "sunil" + FakerUtils.generateRandomNumber();
        User user = new User()
                .setUsername(username)
                .setEmail(username+"@gmail.com")
                .setPassword(username);
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi(signUpApi.getCookies());
        Products products = new Products(1215);
        cartApi.addToCart(products.getId(),1);

        CheckOutPage checkOutPage = new CheckOutPage(getDriver()).load();
        injectCookieToTheBrowser(signUpApi.getCookies());
        checkOutPage.load();
        checkOutPage.
               setBillingAddress(billingAddress).
                selectDirectBank().
                placeOrder();
        Assert.assertTrue(checkOutPage.getProductItem().contains(products.getName()));

    }


}
