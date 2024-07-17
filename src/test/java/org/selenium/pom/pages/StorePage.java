package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.constants.EndPoint;
import org.selenium.pom.pages.Components.ProductThumbnail;

import java.util.List;

public class StorePage extends BasePage {
    private ProductThumbnail productThumbnail;

private final By searchFld = By.xpath("//input[@type='search']");
private final By searchBtn = By.xpath("//button[@value='Search']");
private final By title = By.xpath("//h1[@class='woocommerce-products-header__title page-title']");


    public StorePage(WebDriver driver){
        super(driver);
        productThumbnail = new ProductThumbnail(driver);
    }

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    public void setProductThumbnail(ProductThumbnail productThumbnail) {
        this.productThumbnail = productThumbnail;
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
        load(EndPoint.STORE.url);
        return this;
    }


    public Boolean waitForTheUrl(String partialurltext){
        return wait.until(ExpectedConditions.urlContains(partialurltext));
    }

    private StorePage clickSearchBtn(){
       wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
        return this;
    }

    public String getTitle(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(title)).getText();
    }

}
