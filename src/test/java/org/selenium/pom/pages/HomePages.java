package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.Components.CommonHeaders;
import org.selenium.pom.pages.Components.ProductThumbnail;

public class HomePages extends BasePage {
    private CommonHeaders commonHeaders;
    private ProductThumbnail productThumbnail;

    public HomePages(WebDriver driver) {
        super(driver);
        commonHeaders = new CommonHeaders(driver);
        productThumbnail = new ProductThumbnail(driver);
    }


    public HomePages load(){
        load("");
        return this;
    }

    public CommonHeaders getCommonHeaders() {
        return commonHeaders;
    }

    public void setCommonHeaders(CommonHeaders commonHeaders) {
        this.commonHeaders = commonHeaders;
    }

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    public void setProductThumbnail(ProductThumbnail productThumbnail) {
        this.productThumbnail = productThumbnail;
    }

}
