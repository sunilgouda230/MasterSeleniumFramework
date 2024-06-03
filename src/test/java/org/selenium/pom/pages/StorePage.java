package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class StorePage extends BasePage {

private final By searchFld = By.xpath("//input[@type='search']");
private final By searchBtn = By.xpath("//button[@value='Search']");
private final By title = By.xpath("//h1[@class='woocommerce-products-header__title page-title']");
private final By viewCart = By.xpath("//a[@title='View cart']");
    public StorePage(WebDriver driver){
        super(driver);
    }

    private StorePage entertextInSearchFld(String text){
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchFld)).sendKeys(text);
        return this;
    }

    public StorePage search(String text){
        entertextInSearchFld(text).clickSearchBtn();
        return this;
    }

    public StorePage load(){
        load("store");
        return this;
    }

    public Boolean waitForTheUrl(String partialurltext){
        return wait.until(ExpectedConditions.urlContains(partialurltext));
    }

    private StorePage clickSearchBtn(){
       // WebElement ele = driver.findElement(searchBtn);
        //js.executeScript("arguments[0].click();", ele);
       wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
        return this;
    }

    public String getTitle(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }

    private By getAddToCartBtn(String productName){
        return By.xpath("//a[@aria-label='Add “"+ productName +"” to your cart']");
    }

    public StorePage clickOnAddToCartButton(String productName){
        By addToCartBtn = getAddToCartBtn(productName);
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
        return this;
    }

    public CartPage clickViewCart(){
       // WebElement ele = driver.findElement(viewCart);
        //js.executeScript("arguments[0].click();", ele);
        wait.until(ExpectedConditions.elementToBeClickable(viewCart)).click();
        return new CartPage(driver);
    }
}
