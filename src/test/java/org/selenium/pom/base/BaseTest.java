package org.selenium.pom.base;

import io.restassured.http.Cookies;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.constants.Constants;
import org.selenium.pom.constants.DriverType;
import org.selenium.pom.factory.abstractFactory.DriverFactory;
import org.selenium.pom.factory.abstractFactory.DriverManager;
import org.selenium.pom.factory.abstractFactory.RemoteDriverChromeProvider;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.CookieUtils;
import org.selenium.pom.utils.ScreenShotUtil;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BaseTest {

    /**
     * ThreadLocal WebDriver → Each thread gets its own browser instance
     */
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    protected WebDriver getDriver() {
        return driver.get();
    }

    private void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    @Parameters("browser")
    @BeforeMethod
    public void startDriver(@Optional String browser) {
        if (browser == null) {
            browser = Constants.CHROME;
        }

        WebDriver webDriver = DriverManager.createDriver(browser);

        setDriver(webDriver);

        System.out.println(
                "THREAD: " + Thread.currentThread().getId() +
                        " | DRIVER: " + getDriver()
        );
    }

    // ❌ REMOVED synchronized — required for real parallel execution
    @AfterMethod
    public void quitDriver(@Optional String browser, ITestResult result) throws IOException {
        WebDriver currentDriver = getDriver();

        if (currentDriver == null) return;

        System.out.println(
                "QUIT THREAD: " + Thread.currentThread().getId() +
                        " | DRIVER: " + currentDriver
        );

        // Screenshot on failure
        if (result.getStatus() == ITestResult.FAILURE) {

            File destFile = new File("scr" + File.separator + browser + File.separator +
                    result.getTestClass().getRealClass().getSimpleName() + "_" +
                    result.getMethod().getMethodName() + "_" + getCurrentTimeDate() + ".png");

            ScreenShotUtil.captureFullPage(driver.get(),destFile);
        }

        // Always quit driver
        currentDriver.quit();

        // Clean ThreadLocal → prevent memory leaks on CI
        driver.remove();
    }

    public void injectCookieToTheBrowser(Cookies cookies) {
        List<Cookie> seleniumCookies =
                new CookieUtils().convertRestAssuredCookieToSeleniumCookie(cookies);

        for (Cookie c : seleniumCookies) {
            getDriver().manage().addCookie(c);
        }
    }

    private static String getCurrentTimeDate() {
        return new SimpleDateFormat("HH_mm_ss_yyyy_MM_dd").format(new Date());
    }
}
