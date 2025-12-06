package org.selenium.pom.base;

import io.restassured.http.Cookies;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.constants.Constants;
import org.selenium.pom.constants.DriverType;
import org.selenium.pom.factory.abstractFactory.DriverManagerAbstract;
import org.selenium.pom.factory.abstractFactory.DriverManagerFactoryAbstract;
import org.selenium.pom.factory.abstractFactory.RemoteDriverManagerChrome;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.CookieUtils;
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

    /**
     * ThreadLocal DriverManager → Also isolated per thread
     */
    private final ThreadLocal<DriverManagerAbstract> driverManager = new ThreadLocal<>();

    private final ConfigLoader config = ConfigLoader.getInstance();

    protected WebDriver getDriver() {
        return driver.get();
    }

    protected DriverManagerAbstract getDriverManager() {
        return driverManager.get();
    }

    private void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    private void setDriverManager(DriverManagerAbstract manager) {
        this.driverManager.set(manager);
    }

    // ❌ REMOVED synchronized — otherwise parallel execution breaks
    @Parameters("browser")
    @BeforeMethod
    public void startDriver(@Optional String browser) {
        if (browser == null) {
            browser = Constants.CHROME;
        }

        DriverManagerAbstract manager;

        if (config.isGridEnabled()) {
            System.out.println("Grid enabled → Using RemoteDriver");
            manager = new RemoteDriverManagerChrome();
        } else {
            System.out.println("Grid disabled → Using Local Driver");
            manager = DriverManagerFactoryAbstract.getManager(DriverType.valueOf(browser));
        }

        setDriverManager(manager);
        setDriver(manager.getDriver());

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

            takesFullScreenshot(destFile);
        }

        // Always quit driver
        currentDriver.quit();

        // Clean ThreadLocal → prevent memory leaks on CI
        driver.remove();
        driverManager.remove();
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

    private boolean takesFullScreenshot(File destFile) throws IOException {
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(getDriverManager().getDriver());

        destFile.getParentFile().mkdirs();
        if (!destFile.exists()) destFile.createNewFile();

        ImageIO.write(screenshot.getImage(), "PNG", destFile);

        return true;
    }
}
