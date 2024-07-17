package org.selenium.pom.pages.Components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.CartPage;

public class ProductThumbnail extends BasePage {

    private final By viewCart = By.xpath("//a[@title='View cart']");

    public ProductThumbnail(WebDriver driver) {
        super(driver);

    }

    private By getAddToCartBtn(String productName){
        return By.xpath("//a[@aria-label='Add “"+ productName +"” to your cart']");
    }

    public ProductThumbnail clickOnAddToCartButton(String productName){
        By addToCartBtn = getAddToCartBtn(productName);
        if (wait.until(ExpectedConditions.visibilityOfElementLocated(getAddToCartBtn(productName))).isDisplayed()){
            wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
        }

        return this;
    }

    public CartPage clickViewCart(){
        wait.until(ExpectedConditions.elementToBeClickable(viewCart)).click();
        return new CartPage(driver);
    }
}
