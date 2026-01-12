package np.com.moco.seleniumproject.assertions; // Package: groups assertion helpers for the project

import np.com.moco.seleniumproject.pages.LoginPage;
import np.com.moco.seleniumproject.utils.WaitUtils; // Utility to wait for elements/conditions
import np.com.moco.seleniumproject.utils.screenshots; // Utility to capture screenshots
import org.openqa.selenium.By; // Selenium locator class
import org.openqa.selenium.WebDriver; // Selenium WebDriver interface
import org.testng.Assert; // TestNG assertions

public class LoginAssertions { // Class: contains assertions related to login behavior
    // Locator for the dashboard header element shown after successful login
    private static final By dashboardHeader = By.xpath("//*[@id='app']/div/div[2]/div[2]/div/div[1]/div/div/h3/strong"); // By: XPath pointing to dashboard heading
    private static final By emptyEmailErrorMessage = By.id("login_user");

    // Method: verifies that the user has successfully logged in by checking dashboard visibility
     public static void assertUserLogged(WebDriver driver) {
        try { // try: attempt to locate the dashboard header within a timeout
            WaitUtils.waitForElementVisible(driver, dashboardHeader, 10); // wait until `dashboardHeader` is visible
                                                                          // (10s timeout)
            screenshots.takeScreenshot(driver, "login_success"); // on success, take a screenshot labeled
                                                                 // "login_success"
        } catch (Exception e) { // catch: any exception (timeout, no such element, etc.) indicates failure
            screenshots.takeScreenshot(driver, "login_failed"); // take a screenshot labeled "login_failed" for
                                                                // debugging
            Assert.fail("Login failed: Dashboard not loaded"); // fail the test with a clear message
        }
    }

    // Method: verifies that the error message for empty email is displayed
    public static void assertEmailErrorIsDisplayed(WebDriver driver) {
        try {
            WaitUtils.waitForElementVisible(driver, emptyEmailErrorMessage, 5); // wait until the error message is visible (5s timeout)
            screenshots.takeScreenshot(driver, "Email_Error_Check"); // take a screenshot labeled "Email_Error_Check"
        } catch (Exception e) {
            screenshots.takeScreenshot(driver, "Email_Error_Missing"); // take a screenshot labeled "Email_Error_Missing" if not found
            Assert.fail("Email error message not displayed"); // fail the test with a clear message
        }       
    }

   

}
