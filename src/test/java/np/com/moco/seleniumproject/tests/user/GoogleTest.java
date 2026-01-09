package np.com.moco.seleniumproject.tests.user;

import np.com.moco.seleniumproject.pages.GoogleSearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class GoogleTest {

    private WebDriver driver;
    private GoogleSearchPage googleSearchPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        googleSearchPage = new GoogleSearchPage(driver);
    }

    @Test
    public void testSearchFunctionality() {
        googleSearchPage.goToHomePage();
        googleSearchPage.search("Selenium WebDriver");

        boolean resultFound = googleSearchPage.resultsContain("Selenium");
        Assert.assertTrue(resultFound, "Expected search result to contain 'Selenium'");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
