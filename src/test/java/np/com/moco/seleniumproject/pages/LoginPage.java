/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package np.com.moco.seleniumproject.pages;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// import dev.failsafe.internal.util.Assert;
import np.com.moco.seleniumproject.utils.WaitUtils;



/**
*
* @author Dell
*/
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
       // clear existing values and enter credentials
       driver.findElement(username).clear();
       driver.findElement(username).sendKeys(user);
       driver.findElement(password).clear();
       driver.findElement(password).sendKeys(pass);


       try {
           org.openqa.selenium.WebElement btn = driver.findElement(loginBtn);
           // click only if enabled/clickable to avoid intercepted click on disabled button
           if (btn.isEnabled()) {
               WaitUtils.waitForElementClickable(driver, loginBtn, 5).click();
           }
       } catch (org.openqa.selenium.ElementClickInterceptedException e) {
           // If click is intercepted (e.g., overlay or disabled state), ignore — tests will assert post-conditions instead
       }
   }

   public String getErrorMessage() {
        // Wait for the error to appear before grabbing text
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorDescription)).getText();
    }


   public boolean isUserDisplayed() {
       return driver.getPageSource().contains(" Welcome, Vivek");
   }
}
