/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.CreateUserTest to edit this template
 */
package np.com.moco.seleniumproject.tests.user;

import np.com.moco.seleniumproject.base.BaseTest;
import np.com.moco.seleniumproject.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateUserTest extends BaseTest {

    @Test
    public void testSuccessfulLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        
        // Reading values from config.properties via the 'config' object in BaseTest
        loginPage.login(config.getProperty("username"), config.getProperty("password"));
        
        //Assertion check if dashboard is loaded
        boolean islogged = loginPage.isDashBoardVisible();
        Assert.assertTrue(islogged, "Dashboaard Visible");
        Thread.sleep(5000);
        
        // check is the current url contain dhasboard
 
         String checkUrl = driver.getCurrentUrl();
        System.out.println("Login Test Passed!");
    }
}