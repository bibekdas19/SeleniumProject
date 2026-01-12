/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package np.com.moco.seleniumproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

// import dev.failsafe.internal.util.Assert;
import np.com.moco.seleniumproject.utils.WaitUtils;
import np.com.moco.seleniumproject.utils.screenshots;
import org.testng.Assert; // TestNG assertions

/**
 *
 * @author Dell
 */
public class LoginPage {
    private final WebDriver driver;

    private final By username = By.id("login_user");
    private final By password = By.id("login_password");
    private final By loginBtn = By.xpath("//button[contains(@class,'btn-primary')]");
    private static final By dashboardHeader = By.xpath("//*[@id='app']/div/div[2]/div[2]/div/div[1]/div/div/h3/strong"); // By:
                                                                                                                         // XPath
                                                                                                                         // pointing
                                                                                                                         // to
                                                                                                                         // dashboard
                                                                                                                         // heading

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String user, String pass) {
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginBtn).click();
    }

    public boolean isUserDisplayed() {
        return driver.getPageSource().contains(" Welcome, Vivek");
    }
}
