/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package np.com.moco.seleniumproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By emailField = By.id("login_user"); 
    private By passwordField = By.id("login_password");
    private By loginButton = By.xpath("/html/body/div/div/div/div/div/div/div[3]/div/button");
    
    private By dashboardHeader = By.xpath("/html/body/div/div/div[2]/div[2]/div/div[1]/div/div/h3/strong");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void login(String email, String pass) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(pass);
        driver.findElement(loginButton).click();
    }
    
    public boolean isDashBoardVisible(){
       try{
//           return.wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader)).isDisplayed();
       return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader)).isDisplayed();
       }catch (Exception e){
       return false;
       }
    }
    
}
