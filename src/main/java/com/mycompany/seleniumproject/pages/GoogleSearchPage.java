package com.mycompany.seleniumproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

public class GoogleSearchPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public GoogleSearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void goToHomePage() {
        driver.get("https://www.google.com");

        // Accept cookies/privacy popup if it appears (depends on region)
        try {
            WebElement acceptBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(text(),'I agree') or contains(text(),'Accept all') or contains(text(),'Accept')]")
            ));
            acceptBtn.click();
        } catch (Exception e) {
            System.out.println("No cookie consent popup displayed.");
        }

        // Wait for the search input box to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("q")));
    }

    public void search(String query) {
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        searchBox.clear();
        searchBox.sendKeys(query);
        searchBox.submit();

        // Optional: wait until search results load
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#search")));
    }

    public boolean resultsContain(String text) {
        try {
            // Wait for search result titles (h3 inside search results)
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#search a h3")));

            // Get all result titles
            List<WebElement> titles = driver.findElements(By.cssSelector("div#search a h3"));
            System.out.println("Number of result titles found: " + titles.size());

            for (WebElement title : titles) {
                String titleText = title.getText();
                System.out.println("Result title: " + titleText);
                if (titleText.toLowerCase().contains(text.toLowerCase())) {
                    return true;
                }
            }

            // Fallback: check full page text
            String bodyText = driver.findElement(By.tagName("body")).getText();
            System.out.println("Fallback check on page body text.");
            return bodyText.toLowerCase().contains(text.toLowerCase());

        } catch (Exception e) {
            System.out.println("Error while checking search results: " + e.getMessage());
            takeScreenshot("results_error.png");
            return false;
        }
    }

    private void takeScreenshot(String fileName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.createDirectories(Paths.get("target"));
            Files.copy(screenshot.toPath(), Paths.get("target", fileName));
            System.out.println("Screenshot saved: target/" + fileName);
        } catch (IOException ioe) {
            System.out.println("Could not save screenshot: " + ioe.getMessage());
        }
    }
}
