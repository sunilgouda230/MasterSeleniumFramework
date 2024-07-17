package org.selenium.pom.tests;

import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.HomePages;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

    @Test
    public void NavigateFromHomeToStoreUsingMainMenu(){
        StorePage storePage = new HomePages(getDriver()).
                load().getCommonHeaders().
                navigateToStoreUsingMenu();
        Assert.assertEquals(storePage.getTitle(),"Store");
    }

}
