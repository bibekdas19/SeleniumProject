
package np.com.moco.seleniumproject.tests.user;

import np.com.moco.seleniumproject.base.BaseTest;
import np.com.moco.seleniumproject.pages.LoginPage;
import np.com.moco.seleniumproject.assertions.LoginAssertions;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test
    public void testSuccessfulLogin() throws InterruptedException {
        
        LoginPage loginPage = new LoginPage(driver);
        // Reading values from config.properties via the 'config' object in BaseTest
        loginPage.login(config.getProperty("username"), config.getProperty("password"));
        
        //Assertion check if dashboard is loaded
        LoginAssertions.assertUserLogged(driver);
    }
    
    @Test
    public void testEmptyEmail() throws InterruptedException {
        
    }
    
    @Test
    public void testEmptyPass() throws InterruptedException {
        
    }
    
    @Test
    public void testInvalidEmail() throws InterruptedException {
        
    }
    
    @Test
    public void testEmptyPassword() throws InterruptedException {
        
    }
}