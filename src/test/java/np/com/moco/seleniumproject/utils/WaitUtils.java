package np.com.moco.seleniumproject.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

public class WaitUtils {

    private static final int DEFAULT_TIMEOUT = 10;

    /* ================= CORE WAIT ================= */

    private static WebDriverWait wait(WebDriver driver, int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    private static WebDriverWait wait(WebDriver driver) {
        return wait(driver, DEFAULT_TIMEOUT);
    }

    /* ================= ELEMENT VISIBILITY ================= */

    public static WebElement waitForElementVisible(WebDriver driver, By locator) {
        return wait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementVisible(WebDriver driver, By locator, int seconds) {
        return wait(driver, seconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static List<WebElement> waitForElementsVisible(WebDriver driver, By locator) {
        return wait(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    /* ================= ELEMENT CLICKABLE ================= */

    public static WebElement waitForElementClickable(WebDriver driver, By locator) {
        return wait(driver).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitForElementClickable(WebDriver driver, By locator, int seconds) {
        return wait(driver, seconds).until(ExpectedConditions.elementToBeClickable(locator));
    }

    /* ================= ELEMENT PRESENCE ================= */

    public static WebElement waitForElementPresent(WebDriver driver, By locator) {
        return wait(driver).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /* ================= ELEMENT INVISIBILITY ================= */

    public static boolean waitForElementInvisible(WebDriver driver, By locator) {
        return wait(driver).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /* ================= SAFE ACTIONS ================= */

    public static void safeClick(WebDriver driver, By locator) {
        WebElement element = waitForElementClickable(driver, locator);
        scrollIntoView(driver, element);
        element.click();
    }

    public static void sendKeys(WebDriver driver, By locator, String text) {
        WebElement element = waitForElementVisible(driver, locator);
        element.clear();
        element.sendKeys(text);
    }

    /* ================= SCROLL & UTILS ================= */

    public static void scrollIntoView(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    public static boolean isElementDisplayed(WebDriver driver, By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /* ================= TEXT VALIDATION ================= */

    public static boolean waitForTextPresent(WebDriver driver, By locator, String text) {
        try {
            return wait(driver).until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        } catch (TimeoutException e) {
            return false;
        }
    }

    /* ================= PAGE LOAD ================= */

    public static void waitForPageLoad(WebDriver driver) {
        wait(driver).until(d ->
                ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
    }
}
