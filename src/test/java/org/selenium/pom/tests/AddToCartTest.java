package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.constants.EndPoint;
import org.selenium.pom.dataprovider.CustomDataProvider;
import org.selenium.pom.objects.Products;
import org.selenium.pom.objects.StoreProducts;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePages;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;


public class AddToCartTest extends BaseTest {

    @Test
    public void addToCartFromStorePage() throws IOException {
        Products products = new Products(1215);
        CartPage cartPage = new StorePage(getDriver()).
                load().getProductThumbnail()
                .clickOnAddToCartButton(products.getName())
                .clickViewCart();
        Assert.assertEquals(cartPage.viewProductTitle(),products.getName());
    }

    @Test(dataProvider = "getFeaturedProducts", dataProviderClass = CustomDataProvider.class)
    public void addToCartFeaturedProduct(Products products ){
        CartPage cartPage = new HomePages(getDriver()).load().getProductThumbnail().
                clickOnAddToCartButton(products.getName()).
                clickViewCart();
        Assert.assertEquals(cartPage.viewProductTitle(),products.getName());
    }

    @Test(dataProvider = "getProducts", dataProviderClass = CustomDataProvider.class)
    public void addToCartProductFromStore(List<StoreProducts> StoreProducts) throws IOException {
        StorePage storePage = new StorePage(getDriver()).load();
        for (StoreProducts product : StoreProducts) {
            storePage.getProductThumbnail().clickOnAddToCartButton(product.getName());
        }
        storePage.getProductThumbnail().clickViewCart();
        CartPage cartPage = new CartPage(getDriver())
                .verifyProductInCart();
    }

}
