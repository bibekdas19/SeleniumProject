package np.com.moco.seleniumproject.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import np.com.moco.seleniumproject.utils.WaitUtils;

public class LoginPage {

    private final WebDriver driver;

    private final By username = By.id("login_user");
    private final By password = By.id("login_password");
    private final By loginBtn = By.xpath("//button[contains(@class,'btn-primary')]");
    private final By errorDescription = By.className("modal-title");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String user, String pass) {

        // Ensure login page is ready
        WaitUtils.waitForElementVisible(driver, username, 10);

        WaitUtils.sendKeys(driver, username, user);
        WaitUtils.sendKeys(driver, password, pass);

        WaitUtils.waitForElementClickable(driver, loginBtn, 10).click();
    }

    public String getErrorMessage() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(errorDescription))
                .getText();
    }

    public void logout() {
        WaitUtils.safeClick(driver, By.xpath("//img[@class='avatar-img']"));
        WaitUtils.safeClick(driver,
                By.xpath("//*[@id='app']/div/div[2]/div[1]/div/ul[4]/div/div/a[2]"));
    }

    public boolean isUserDisplayed() {
        return driver.getPageSource().contains("Welcome");
    }
}
