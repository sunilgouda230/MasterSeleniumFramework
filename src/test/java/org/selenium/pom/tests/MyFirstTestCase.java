package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Products;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.CheckOutPage;
import org.selenium.pom.pages.HomePages;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyFirstTestCase extends BaseTest {


    @Test
    public void guestUserAndCheckoutUsingDirectBankTransfer() throws IOException {
       /* BillingAddress billingAddress = new BillingAddress().
                setEmail("sunil@gmail.com").setFirstName("demo").
                setAdressLineOne("San Francisco")
                .setAdressLineTwo("New Bay").
                setLastName("user").
                setCity("San Francisco").
                setPostalCode("94188");*/
       /* BillingAddress billingAddress = new BillingAddress("demo","user","94188",
                "San Franciso","New Bay","san Francisco","sunil@gmail.com"); */
        String search = "blue";
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);
        Products products = new Products(1215);
        StorePage storePage = new HomePages(getDriver()).
                load().
                navigateToStoreUsingMenu()
                .search(search);
        Assert.assertTrue(storePage.getTitle().contains(search));
        storePage.clickOnAddToCartButton(products.getName());
        storePage.clickViewCart();
        CartPage cartPage = new CartPage(getDriver());
        Assert.assertEquals(cartPage.viewProductTitle(),products.getName());
        cartPage.checkout();
        CheckOutPage checkOutPage = new CheckOutPage(getDriver());
        checkOutPage.setBillingAddress(billingAddress).
                selectDirectBank().
                placeOrder();
        Assert.assertEquals(checkOutPage.viewOrderReceiveMessage(), "Thank you. Your order has been received.");

    }

    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);
        Products products = new Products(1215);
        User user = new User(ConfigLoader.getInstance().getUsername(), ConfigLoader.getInstance().getPassword());
        String search = "blue";
        StorePage storePage = new HomePages(getDriver()).
                load().
                navigateToStoreUsingMenu()
                .search(search);
        Assert.assertTrue(storePage.getTitle().contains(search));
        storePage.clickOnAddToCartButton(products.getName());
        storePage.clickViewCart();
        CartPage cartPage = new CartPage(getDriver());
        Assert.assertEquals(cartPage.viewProductTitle(),products.getName());
        cartPage.checkout();
        CheckOutPage checkOutPage = new CheckOutPage(getDriver());
        checkOutPage.checkOutPageLoginBtn().
                login(user).
                setBillingAddress(billingAddress).
                selectDirectBank().
                placeOrder();
        Assert.assertEquals(checkOutPage.viewOrderReceiveMessage(), "Thank you. Your order has been received.");
    }
}
