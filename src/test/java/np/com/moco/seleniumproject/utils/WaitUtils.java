package np.com.moco.seleniumproject.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class WaitUtils {

    private static final int DEFAULT_TIMEOUT = 10;

    // Wait for element to be visible
    public static WebElement waitForElementVisible(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementVisible(WebDriver driver, By locator, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Wait for element to be clickable
    public static WebElement waitForElementClickable(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static WebElement waitForElementClickable(WebDriver driver, By locator, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Wait for element presence
    public static WebElement waitForElementPresent(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Wait for element to disappear
    public static boolean waitForElementInvisible(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    // Safe click
    public static void safeClick(WebDriver driver, By locator) {
        waitForElementClickable(driver, locator).click();
    }

    // Clear and type
    public static void sendKeys(WebDriver driver, By locator, String text) {
        WebElement element = waitForElementVisible(driver, locator);
        element.clear();
        element.sendKeys(text);
    }
}
