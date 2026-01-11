package np.com.moco.seleniumproject.assertions;

import np.com.moco.seleniumproject.utils.WaitUtils;
import np.com.moco.seleniumproject.utils.screenshots;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginAssertions {
   private static final By dashboardHeader = By.xpath("//*[@id='app'/div/div[2]/div[2]/div/div[1]/div/div/h3/strong");

    public static void assertUserLogged(WebDriver driver) {
        try {
            WaitUtils.waitForElementVisible(driver, dashboardHeader, 10);
            screenshots.takeScreenshot(driver, "login_success");
        } catch (Exception e) {
            screenshots.takeScreenshot(driver, "login_failed");
            Assert.fail("Login failed: Dashboard not loaded");
        }
}
}
