package np.com.moco.seleniumproject.base;

import np.com.moco.seleniumproject.pages.LoginPage;
import org.testng.annotations.BeforeClass;

public class baseAuthenticatedTest extends BaseTest {

    protected LoginPage loginPage;

    @BeforeClass(alwaysRun = true)
    public void loginOnce() {
        loginPage = new LoginPage(driver);

        loginPage.login(
            config.getProperty("username"),
            config.getProperty("password")
        );
    }
}
