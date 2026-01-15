package np.com.moco.seleniumproject.tests.user; // package declares the namespace for this test class

import np.com.moco.seleniumproject.base.BaseTest; // BaseTest provides WebDriver setup/teardown and shared config
import np.com.moco.seleniumproject.pages.LoginPage; // Page Object representing the login page; accepts a WebDriver
import np.com.moco.seleniumproject.assertions.LoginAssertions; // Reusable assertion helpers for login flows
import org.testng.Assert; // TestNG assertions used in test methods
import org.testng.annotations.BeforeMethod; // TestNG annotation to run setup before each test
import org.testng.annotations.Test; // TestNG annotation to mark test methods

public class LoginPageTest extends BaseTest { // extends BaseTest to inherit `driver` and `config`
    LoginPage loginPage; // instance of the Page Object; will be constructed using the inherited `driver`

    @BeforeMethod
    public void setUpTest() {
        // `driver` here is the protected field from `BaseTest`.
        // BaseTest.setUp() runs before each test (via TestNG) and creates the browser/driver.
        // We pass that driver into the LoginPage so the page object can interact with the same browser instance.
        loginPage = new LoginPage(driver);
    }
    
    

        @Test
        public void testSuccessfulLogin() throws InterruptedException {
                // NOTE: We could reuse the `loginPage` field initialized in @BeforeMethod.
                // Creating a new LoginPage with the same `driver` is redundant but harmless — both reference the same browser.
                // LoginPage loginPage = new LoginPage(driver);
                // `config` is provided by BaseTest (it reads config.properties). We use it to avoid hardcoding credentials.
                loginPage.login(config.getProperty("username"), config.getProperty("password"));

                // `LoginAssertions.assertUserLogged(driver)` uses the same `driver` to verify the dashboard is visible.
                // This demonstrates the pattern: test -> page actions -> assertions, all using the shared WebDriver.
                LoginAssertions.assertUserLogged(driver);
                // Additional test-level assertion using the page object's helper `isUserDisplayed()`.
                Assert.assertTrue(loginPage.isUserDisplayed(), "User should be logged in successfully");
        }

    @Test
    public void testEmptyEmail() throws InterruptedException {
        // Attempt login with empty email and a valid password from config
        loginPage.login("", config.getProperty("password"));
        // Verify via page object that user is not shown as logged in
        Assert.assertFalse(loginPage.isUserDisplayed(), "User should not be logged in with empty email");
        // Use assertion helper which uses `driver` to check the specific email validation message
        LoginAssertions.assertEmailErrorIsDisplayed(driver);
    }

    @Test
    public void testEmptyPass() throws InterruptedException {
        // Attempt login with username from config and empty password
        loginPage.login(config.getProperty("username"), "");
        // Confirm user is not logged in
        Assert.assertFalse(loginPage.isUserDisplayed(), "User should not be logged in with empty password");
    }

    @Test
    public void testInvalidEmail() throws InterruptedException {
        // Attempt login with a wrong email and valid password
        loginPage.login("invalid@example.com", config.getProperty("password"));
        // The page object reflects the current browser state (same `driver`), so check login failed
        Assert.assertFalse(loginPage.isUserDisplayed(), "User should not be logged in with invalid email");
    }

    @Test
    public void testEmptyPassword() throws InterruptedException {
        // Creating a new LoginPage again references the same `driver` from BaseTest
        // both fields empty
        loginPage.login("", "");
        Assert.assertFalse(loginPage.isUserDisplayed(), "User should not be logged in with empty credentials");
    }

    @Test
    public void checkPageTitle() throws InterruptedException {
        String expectedTitle = "Flight Ticket System";
        // `driver.getTitle()` reads the browser's current page title using the same WebDriver instance
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Page title should match expected value");
    }

    @Test
    public void checkPageURL() throws InterruptedException {
        // Compare the browser's current URL with the value from config (BaseTest navigated to this URL in setUp())
        String expectedURL = config.getProperty("url");
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL, "Page URL should match expected value");
    }

    @Test
    public void testWhitespaceInCredentials() throws InterruptedException {
        // Send credentials with extra whitespace to validate trimming/validation behavior
        loginPage.login("  " + config.getProperty("username") + "  ", "  " + config.getProperty("password") + "  ");
        // Expect failure if the app doesn't trim inputs; test helps document expected behavior
        Assert.assertFalse(loginPage.isUserDisplayed(), "User should not be logged in.");
    }

    @Test
    public void testLoginWithNonExistentUser() throws InterruptedException {
        // Attempt login with a user that shouldn't exist in the system
        loginPage.login("abc@gmail.com", config.getProperty("password"));
        // Retrieve the error message via the page object (uses same `driver` internally)
        String actualErrorMessage = loginPage.getErrorMessage();
        String expectedErrorMessage = "User does not exist";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message should indicate non-existent user");
    }
    
       
 
    
}