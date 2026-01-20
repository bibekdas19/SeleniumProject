/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package np.com.moco.seleniumproject.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import np.com.moco.seleniumproject.utils.WaitUtils;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    private final By chooseDate= By.xpath("//input[@placeholder=\"Request Date\"]");
    
    public FlightTickets(WebDriver driver) {
        this.driver = driver;
    }

    public void searchTickets(String userMocoID, String UserContName) {
        WaitUtils.safeClick(driver, flightTicketLoc);
        WaitUtils.sendKeys(driver, mocoId, userMocoID);
        WaitUtils.sendKeys(driver, ContactName, UserContName);
        WaitUtils.safeClick(driver, SearchButton);

    }

    public String getErrorPopMessage() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(invalidContNamePopMsg))
                .getText();
    }

}
