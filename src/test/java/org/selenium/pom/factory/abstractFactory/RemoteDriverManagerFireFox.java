package org.selenium.pom.factory.abstractFactory;

import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.selenium.pom.constants.Constants;
import org.selenium.pom.utils.ConfigLoader;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteDriverManagerFireFox extends DriverManagerAbstract{
    @Override
    protected void startDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--headless"); // Add options as needed

        try {
            driver = new RemoteWebDriver(new URL(ConfigLoader.getInstance().getValue(Constants.GRID_URL)), options);
        } catch (MalformedURLException e) {
            System.err.println("The Selenium Grid URL is malformed.");
            throw new RuntimeException(e);
        } catch (SessionNotCreatedException e) {
            System.err.println("Session not created: Check Selenium Grid configuration.");
            throw new RuntimeException(e);
        }
    }
}
