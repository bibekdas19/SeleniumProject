package np.com.moco.seleniumproject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators using PageFactory (@FindBy)
    @FindBy(id = "login_user")
    private WebElement emailField;

    @FindBy(id = "login_password")
    private WebElement passwordField;

    @FindBy(xpath = "/html/body/div/div/div/div/div/div/div[3]/div/button")
    private WebElement loginButton;

    @FindBy(xpath = "/html/body/div/div/div[2]/div[2]/div/div[1]/div/div/h3/strong")
    private WebElement dashboardHeader;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // 10s is usually safer
        PageFactory.initElements(driver, this);
    }

    /**
     * Performs login action and returns current page object (useful for chaining)
     */
    public LoginPage login(String email, String password) {
        wait.until(ExpectedConditions.visibilityOf(emailField))
            .sendKeys(email);

        wait.until(ExpectedConditions.visibilityOf(passwordField))
            .sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(loginButton))
            .click();

        return this;  // returns itself → you can chain .isDashboardVisible()
    }

    /**
     * Better version: checks if dashboard header is visible after login
     */
    public boolean isDashboardVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(dashboardHeader))
                       .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Alternative check: more robust way using URL (recommended in many cases)
     */
    public boolean isOnDashboardPage() {
        return wait.until(d -> driver.getCurrentUrl().contains("dashboard"));
    }

    // Optional: if you want to know when login is fully completed
    public boolean isLoginSuccessful() {
        return isDashboardVisible() || isOnDashboardPage();
    }
}