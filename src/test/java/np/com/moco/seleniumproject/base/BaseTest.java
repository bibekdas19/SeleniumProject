package np.com.moco.seleniumproject.base;

import np.com.moco.seleniumproject.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public class BaseTest {
    // `driver` is created once per test method in `setUp()` and is shared with test classes via inheritance.
    // Tests extend `BaseTest` and can access this `protected` field directly.
    protected WebDriver driver;

    // `config` reads values from config.properties and is available to tests.
    protected ConfigReader config;

    @BeforeMethod
    public void setUp() {
        // initialize config reader (reads test URL, credentials, etc.)
        config = new ConfigReader();

        // WebDriverManager downloads/sets up the matching ChromeDriver binary for your Chrome version.
        WebDriverManager.chromedriver().setup();

        // create a new browser instance. This is the single WebDriver object tests will use.
        driver = new ChromeDriver();

        // maximize the browser window for consistent viewport
        driver.manage().window().maximize();

        // implicit wait applied to all findElement calls (keeps examples simple; prefer explicit waits in page objects)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // navigate to the base URL from config
        driver.get(config.getProperty("url"));
    }

    @AfterMethod
    public void tearDown() {
        // close the browser after each test method to avoid side effects between tests
        if (driver != null) {
            driver.quit();
        }
    }
}