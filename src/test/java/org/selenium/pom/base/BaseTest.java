package org.selenium.pom.base;

import io.restassured.http.Cookies;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.selenium.pom.constants.DriverType;
import org.selenium.pom.factory.DriverManager;
import org.selenium.pom.factory.abstractFactory.DriverManagerAbstract;
import org.selenium.pom.factory.abstractFactory.DriverManagerFactoryAbstract;
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
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BaseTest {

    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private final ThreadLocal<DriverManagerAbstract> driverManager = new ThreadLocal<>();

    private void setDriver(WebDriver driver){
        this.driver.set(driver);
    }

    protected WebDriver getDriver(){
        return this.driver.get();
    }

    public DriverManagerAbstract getDriverManager() {
        return driverManager.get();
    }

    public void setDriverManager(DriverManagerAbstract driverManager) {
        this.driverManager.set(driverManager);
    }


    @Parameters("browser")
    @BeforeMethod
    public synchronized void startDriver(@Optional String browser){
             // browser = System.getProperty("browser", browser);
        if (browser == null) {
            browser = "CHROME";
        }
       // setDriver(new DriverManager().initializeDriver(browser));
        setDriverManager(DriverManagerFactoryAbstract.getManager(DriverType.valueOf(browser)));
        setDriver(getDriverManager().getDriver());
        System.out.println("CURRENT THREAD "+ Thread.currentThread().getId() + " ,"+ getDriverManager().getDriver());
    }

    @Parameters("browser")
    @AfterMethod
    public synchronized void quitDriver(@Optional String browser , ITestResult result) throws IOException {
        System.out.println("CURRENT THREAD "+ Thread.currentThread().getId() + " , "+ getDriverManager().getDriver());
        //getDriver().quit();
        if (result.getStatus() == ITestResult.FAILURE){
            File desFile = new File("scr" + File.separator + browser + File.separator + result
                    .getTestClass().getRealClass().getSimpleName() + "_" + result.getMethod().getMethodName()
            + getCurrentTimeDate()+ ".png");
            takesfullcreenshot(desFile);
        }
        getDriverManager().getDriver().quit();
    }

    public void injectCookieToTheBrowser(Cookies cookies){
        List<Cookie> seleniumCookie = new CookieUtils().convertRestAssuredCookieToSeleniumCookie(cookies);
        for (Cookie c: seleniumCookie
             ) {
            getDriver().manage().addCookie(c);
        }
    }

    private static String getCurrentTimeDate(){
        String date = new SimpleDateFormat("HH_mm_ss_yyyy_MM_dd").format(new Date());
        return date;
    }

    private boolean takescreenshot(File desfile) throws IOException {
        try {
            FileUtils.copyFile(((TakesScreenshot) getDriverManager().getDriver()).getScreenshotAs(OutputType.FILE),desfile);
        } catch (IOException e) {
            System.out.println("something went wrong"+e.getMessage());
        }
        return false;
    }

    private boolean takesfullcreenshot(File desfile) throws IOException {
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(getDriverManager().getDriver());
        try {
            // Ensure the directories exist
            desfile.getParentFile().mkdirs();

            // Create the file if it doesn't exist
            if (!desfile.exists()) {
                desfile.createNewFile();
            }
            ImageIO.write(screenshot.getImage(),"PNG",desfile);
        } catch (IOException e){
            e.printStackTrace();
        }

        return false;
    }
}
