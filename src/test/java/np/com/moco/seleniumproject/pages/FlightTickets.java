/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package np.com.moco.seleniumproject.pages;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import np.com.moco.seleniumproject.utils.WaitUtils;
import np.com.moco.seleniumproject.utils.screenshots;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author sandesh
 */
public class FlightTickets {

    private final WebDriver driver;
    private final By flightTicketLoc = By.xpath("//a[contains(@href,'flighttickets')]");
    private final By mocoId = By.xpath("//*[@placeholder=\"MOCO ID\" and @id=\"searchBar\"]");
    private final By SearchButton = By.xpath("//button[@class=\'btn btn-undefined\']");
    private final By ContactName = By.xpath("//input[@id='searchBar' and @placeholder=\'Contact Name\']");
    private final By notFoundPopMsg = By.xpath("//*[@class=\"modal-body\" and contains(text(), 'Purchase not found')]");
    private final By invalidContNamePopMsg = By.xpath("//div[@class=\"modal-body\"  and contains(text(),\"Invalid contact name data found.\")]");
//    private final By invalidContNum= By.xpath(xpathExpression)

    //locator for Request Date
    private final By chooseDate = By.xpath("//input[@placeholder=\"Request Date\"]");
    private final By prevMonth = By.xpath("//button[@aria-label=\"Previous month\"]");
    private final By nextMonth = By.xpath("//button[@aria-label=\"Next month\"]");
    private final By MonthSelect = By.xpath("//button[@data-test-id=\"month-toggle-overlay-0\"]");
    private final String dayXpath = "//div[contains(@class, 'dp__cell_inner') and text()='%s']";
//    private final String FindDayXpath = String.format(dayXpath, 10);

    //locator for select status
    private final By selectStatus = By.xpath("//div[contains(@class, 'col-sm-2')]//select[@class='form-select']");
    private final By selectSuccessStatus = By.xpath("//div[contains(@class, 'col-sm-2')]//select[@class='form-select']//option[@value=\"SUCCESS\"]");

    //locator for success result
    public FlightTickets(WebDriver driver) {
        this.driver = driver;
    }

    public void searchTickets(String userMocoID, String UserContName, String Day) {
        WaitUtils.safeClick(driver, flightTicketLoc);
        WaitUtils.sendKeys(driver, mocoId, userMocoID);

        //chosing date
        try {
            WaitUtils.safeClick(driver, chooseDate);
            WaitUtils.safeClick(driver, prevMonth);
            By specificsDay = By.xpath(String.format(dayXpath, Day));
            WaitUtils.safeClick(driver, specificsDay);
            screenshots.takeScreenshot(driver, "success screenshot");

        } catch (Exception e) {
            System.out.println("Unabel to screenshot");
        }

        //choose the status
        Select select = new Select(WaitUtils.waitForElementVisible(driver, selectStatus));
        select.selectByValue("SUCCESS");

        WaitUtils.sendKeys(driver, ContactName, UserContName);
        WaitUtils.safeClick(driver, SearchButton);

    }

    public void searchTicketWithIdOnly(String userMocoID) {
        WaitUtils.safeClick(driver, flightTicketLoc);
        WaitUtils.sendKeys(driver, mocoId, userMocoID);

        Select select = new Select(WaitUtils.waitForElementVisible(driver, selectStatus));
        select.selectByValue("SUCCESS");

        WaitUtils.safeClick(driver, SearchButton);

        // 1. ADD AN EXPLICIT WAIT HERE
        // This waits up to 10 seconds for the specific row to appear in the DOM
        By resultLocator = By.xpath("//tr[td[contains(., 'SUCHIT BANIYA')]]//td[contains(., 'SUCCESS')]");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(resultLocator));

            // 2. SUCCESS PATH
            screenshots.takeScreenshot(driver, "success_screenshot");
            System.out.println("Screenshot captured for user: " + userMocoID);

        } catch (TimeoutException e) {
            // 3. FAILURE PATH
            screenshots.takeScreenshot(driver, "failure_not_found");
            System.out.println("Timeout: Result row not visible after 10 seconds.");
        }
    }

//    public void searchTicketWithIdOnly(String userMocoID) {
//
//        WaitUtils.safeClick(driver, flightTicketLoc);
//        WaitUtils.sendKeys(driver, mocoId, userMocoID);
//        
//        //chose the status
//        Select select = new Select(WaitUtils.waitForElementVisible(driver, selectStatus));
//        select.selectByValue("SUCCESS");
//        
//        WaitUtils.safeClick(driver, SearchButton);
//
//        List<WebElement> results = driver.findElements(By.xpath("//tr[td[contains(text(), 'SUCHIT BANIYA')]]//td[text()='SUCCESS']"));
//
//        if (!results.isEmpty() && results.get(0).isDisplayed()) {
//            // Code to execute if the condition is true
//            screenshots.takeScreenshot(driver, "success screenshot");
//
//        } else {
//            // Code to execute if the condition is false
//            System.out.println("could not print the screenshot");
//        }
//
//    }
    public String getErrorPopMessage() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(invalidContNamePopMsg))
                .getText();
    }

}
