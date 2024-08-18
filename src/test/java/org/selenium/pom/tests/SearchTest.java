package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.HomePages;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    private StorePage storePage;

    @Test
    public void SearchWithPartialTest(){
        String searchFor = "blue";
        storePage = new StorePage(getDriver());
        boolean page = new StorePage(getDriver()).
                load().
                search(searchFor).
                waitForTheUrl("blue&post_type=product");
        System.out.println(storePage.getTitle());
        Assert.assertTrue(storePage.getTitle().contains(searchFor));
    }

    @Test
    public void dummytest(){
        System.out.println("testing the test method for webhook ");
    }

}
