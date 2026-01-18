package np.com.moco.seleniumproject.tests.user;

import np.com.moco.seleniumproject.assertions.OperatorAssertions;
import np.com.moco.seleniumproject.base.*;
import np.com.moco.seleniumproject.pages.LoginPage;
import np.com.moco.seleniumproject.pages.OperatorPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OperatorPageTest extends baseAuthenticatedTest {
    private LoginPage loginPage;
    private OperatorPage operatorPage;
    private OperatorAssertions operatorAssertions;

    @BeforeMethod
    public void setUpTest() {
        loginPage = new LoginPage(driver);
        operatorPage = new OperatorPage(driver);
        operatorAssertions = new OperatorAssertions(operatorPage);
        loginPage.login(config.getProperty("username"), config.getProperty("password"));
    }

    @Test(priority = 1)
    public void addOperatorwithInvalidEmail() throws InterruptedException {
        operatorPage.addOperator("Sandesh Thapa Magar", "sandeshmagar.com", "9849719431");
        operatorAssertions.verifyInvalidEmailError();

    }

    @Test(priority = 2)
    public void addOperatorwithEmptyEmail() throws InterruptedException {
        operatorPage.addOperator("Sandesh Thapa Magar", "", "9849719431");
        operatorAssertions.verifyEmptyEmailError();
    }

    @Test(priority = 3)
    public void addOperatorwithInvalidContact() throws InterruptedException {
        operatorPage.addOperator("Sandesh Thapa Magar", "sandeshthapa@moco.com.np", "984.");
        operatorAssertions.verifyInvalidContactError();
    }

    @Test(priority = 4)
    public void addOperatorwithInvalidName() throws InterruptedException {
        operatorPage.addOperator("San,de", "sandeshthapa@moco.com.np", "984971931");
        operatorAssertions.verifyInvalidNameError();
    }

    @Test(priority = 5)
    public void addOperatorwithEmptyName() throws InterruptedException {
        operatorPage.addOperator("", "sandeshthapa@moco.com.np", "9849719431");
        operatorAssertions.verifyEmptyNameError();
    }

    @Test(priority = 6)
    public void addOperatorwithValidInputs() throws InterruptedException {
        operatorPage.addOperator("Sandesh Thapa Magar", "sandeshthapa@moco.com.np", "9849719431");
        operatorAssertions.verifyOperatorAddedSuccessfully();
    }

    @AfterMethod
    public void LogOut() {

    }

}