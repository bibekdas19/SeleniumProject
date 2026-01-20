package np.com.moco.seleniumproject.tests.user;

import np.com.moco.seleniumproject.assertions.OperatorAssertions;
import np.com.moco.seleniumproject.base.baseAuthenticatedTest;
//import np.com.moco.seleniumproject.pages.LoginPage;
import np.com.moco.seleniumproject.pages.OperatorPage;
import org.testng.annotations.*;

public class OperatorPageTest extends baseAuthenticatedTest {
    private OperatorPage operatorPage;
    private OperatorAssertions operatorAssertions;

    @BeforeClass
    public void setUpTest() {
        // login handled in baseAuthenticatedTest
        operatorPage = new OperatorPage(driver);
        operatorAssertions = new OperatorAssertions(operatorPage);
    }

    @Test(priority = 1)
    public void addOperatorWithInvalidEmail() {
        operatorPage.addOperator("Sandesh Thapa Magar", "sandeshmagar.com", "9849719431");
        operatorAssertions.verifyInvalidEmailError();
    }

    @Test(priority = 2)
    public void addOperatorWithEmptyEmail() {
        operatorPage.addOperator("Sandesh Thapa Magar", "", "9849719431");
        operatorAssertions.verifyEmptyEmailError();
    }

    @Test(priority = 3)
    public void addOperatorWithInvalidContact() {
        operatorPage.addOperator("Sandesh Thapa Magar", "sandeshthapa@moco.com.np", "984.");
        operatorAssertions.verifyInvalidContactError();
    }

    @Test(priority = 4)
    public void addOperatorWithInvalidName() {
        operatorPage.addOperator("San,de", "sandeshthapa@moco.com.np", "984971931");
        operatorAssertions.verifyInvalidNameError();
    }

    @Test(priority = 5)
    public void addOperatorWithEmptyName() {
        operatorPage.addOperator("", "sandeshthapa@moco.com.np", "9849719431");
        operatorAssertions.verifyEmptyNameError();
    }

    @Test(priority = 6)
    public void addOperatorWithValidInputs() {
        operatorPage.addOperator("Sandesh Thapa Magar", "sandeshthapa@moco.com.np", "9849719431");
        operatorAssertions.verifyOperatorAddedSuccessfully();
    }

    @AfterClass
    public void logOut() {
        // Add logout steps here if needed
        // e.g., new LoginPage(driver).logout();
    }
}
