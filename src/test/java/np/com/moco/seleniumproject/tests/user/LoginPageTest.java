package np.com.moco.seleniumproject.tests.user;

import np.com.moco.seleniumproject.base.BaseTest;
import np.com.moco.seleniumproject.pages.LoginPage;
import np.com.moco.seleniumproject.assertions.LoginAssertions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test
    public void testSuccessfulLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        // Reading values from config.properties via the 'config' object in BaseTest
        loginPage.login(config.getProperty("username"), config.getProperty("password"));

        // Assertion check if dashboard is loaded
        LoginAssertions.assertUserLogged(driver);
    }

    @Test
    public void testEmptyEmail() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", config.getProperty("password"));
        // Verify user is not logged in and appropriate error is shown
        Assert.assertFalse(loginPage.isUserDisplayed(), "User should not be logged in with empty email");
        LoginAssertions.assertEmailErrorIsDisplayed(driver);
    }

    @Test
    public void testEmptyPass() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(config.getProperty("username"), "");
        // Verify user is not logged in when password is empty
        Assert.assertFalse(loginPage.isUserDisplayed(), "User should not be logged in with empty password");
    }

    @Test
    public void testInvalidEmail() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
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
}