package np.com.moco.seleniumproject.tests.user;

import np.com.moco.seleniumproject.base.BaseTest;
import np.com.moco.seleniumproject.pages.LoginPage;
import np.com.moco.seleniumproject.assertions.LoginAssertions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {
    LoginPage loginPage;

    @BeforeMethod
    public void setUpTest() {
        loginPage = new LoginPage(driver);
    }
    
    

    @Test
    public void testSuccessfulLogin() throws InterruptedException {
        //  LoginPage loginPage = new LoginPage(driver);
        // Reading values from config.properties via the 'config' object in BaseTest
        loginPage.login(config.getProperty("username"), config.getProperty("password"));

        // Assertion check if dashboard is loaded
        LoginAssertions.assertUserLogged(driver);
        Assert.assertTrue(loginPage.isUserDisplayed(), "User should be logged in successfully");
    }

    @Test
    public void testEmptyEmail() throws InterruptedException {
        // LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", config.getProperty("password"));
        // Verify user is not logged in and appropriate error is shown
        Assert.assertFalse(loginPage.isUserDisplayed(), "User should not be logged in with empty email");
        LoginAssertions.assertEmailErrorIsDisplayed(driver);
    }

    @Test
    public void testEmptyPass() throws InterruptedException {
        // LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getProperty("username"), "");
        // Verify user is not logged in when password is empty
        Assert.assertFalse(loginPage.isUserDisplayed(), "User should not be logged in with empty password");
        
    }

    @Test
    public void testInvalidEmail() throws InterruptedException {
        // LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invalid@example.com", config.getProperty("password"));
        // Verify invalid credentials do not allow login
        Assert.assertFalse(loginPage.isUserDisplayed(), "User should not be logged in with invalid email");
    }

    @Test
    public void testEmptyPassword() throws InterruptedException {
         LoginPage loginPage = new LoginPage(driver);
        // both fields empty
        loginPage.login("", "");
        Assert.assertFalse(loginPage.isUserDisplayed(), "User should not be logged in with empty credentials");
    }

    @Test
    public void checkPageTitle() throws InterruptedException {
        String expectedTitle = "Flight Ticket System";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Page title should match expected value");
    }

    @Test
    public void checkPageURL() throws InterruptedException {
        String expectedURL = config.getProperty("url");
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL, "Page URL should match expected value");
    }

    @Test
    public void testWhitespaceInCredentials() throws InterruptedException {
        // LoginPage loginPage = new LoginPage(driver);
        loginPage.login("  " + config.getProperty("username") + "  ", "  " + config.getProperty("password") + "  ");
        // Verify that leading/trailing whitespace is handled correctly
        Assert.assertFalse(loginPage.isUserDisplayed(), "User should not be logged in.");
    }   

    @Test
    public void testLoginWithNonExistentUser() throws InterruptedException {
        loginPage.login("abc@gmail.com", config.getProperty("password"));
        String actualErrorMessage = loginPage.getErrorMessage();
        String expectedErrorMessage = "User does not exist";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message should indicate non-existent user");
    }
    
   
    
       
 
    
}