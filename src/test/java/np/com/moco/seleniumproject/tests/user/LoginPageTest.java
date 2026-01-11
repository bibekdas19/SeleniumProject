/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.CreateUserTest to edit this template
 */
package np.com.moco.seleniumproject.tests.user;

import np.com.moco.seleniumproject.base.BaseTest;
import np.com.moco.seleniumproject.pages.LoginPage;
import np.com.moco.seleniumproject.assertions.LoginAssertions;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test
    public void testSuccessfulLogin() throws InterruptedException {
        
        LoginPage loginPage = new LoginPage(driver);
        // Reading values from config.properties via the 'config' object in BaseTest
        loginPage.login(config.getProperty("username"), config.getProperty("password"));
        
        //Assertion check if dashboard is loaded
        LoginAssertions.assertUserLogged();
    }
}