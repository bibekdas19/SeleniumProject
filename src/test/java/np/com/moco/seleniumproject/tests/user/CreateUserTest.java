package np.com.moco.seleniumproject.tests.user;

import np.com.moco.seleniumproject.base.BaseTest;
import np.com.moco.seleniumproject.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateUserTest extends BaseTest {

    @Test(description = "Verify successful login and dashboard visibility")
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);

        // Login using credentials from config
        loginPage.login(
            config.getProperty("username"),
            config.getProperty("password")
        );

        // Method 1: Check dashboard header visibility (most reliable if element is stable)
        boolean dashboardVisible = loginPage.isDashboardVisible();
        Assert.assertTrue(dashboardVisible, "Dashboard header should be visible after successful login");

        // Method 2: Alternative/stronger check - URL contains dashboard
        boolean onDashboardUrl = loginPage.isOnDashboardPage();
        Assert.assertTrue(onDashboardUrl, "Current URL should contain 'dashboard'");

        // Optional: you can combine both checks
        Assert.assertTrue(
            dashboardVisible || onDashboardUrl,
            "Login failed - neither dashboard header nor dashboard URL pattern found"
        );

        // No Thread.sleep() needed anymore - waits are explicit and smart
        System.out.println("Login Test Passed! Current URL: " + driver.getCurrentUrl());
    }
}