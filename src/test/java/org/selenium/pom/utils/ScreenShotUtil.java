package org.selenium.pom.utils;

import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ScreenShotUtil {

        public static void captureFullPage(WebDriver driver, File destFile) throws IOException {
            Screenshot screenshot = new AShot()
                    .shootingStrategy(ShootingStrategies.viewportPasting(100))
                    .takeScreenshot(driver);

            destFile.getParentFile().mkdirs();
            ImageIO.write(screenshot.getImage(), "PNG", destFile);
        }
}
