package np.com.moco.seleniumproject.pages;

import np.com.moco.seleniumproject.utils.WaitUtils;
import np.com.moco.seleniumproject.utils.screenshots;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import org.openqa.selenium.interactions.Actions;

public class OperatorPage {
    private final WebDriver driver;
    private final By addbutton = By.xpath("//a[contains(@href,'operator')]");
    private final By addOperatorpath = By.xpath("//a[contains(@href,'operator/add')]"); // Add Operator path button 
    private final By fullName = By.xpath("//input[@placeholder=\"Full Name\"]");
    private final By emailAddress = By.xpath("//input[@placeholder=\"Email Address\"]");
    private final By contactNumber = By.xpath("//input[@placeholder=\"Contact Number\"]");
    private final By organiDropdown = By.cssSelector("select.form-select");
    private final By checkboxes = By.xpath("//input[@class='form-check-input' and @type='checkbox']");
    private final By submitButton = By.cssSelector("button.btn");
    private final By cancelButton = By.cssSelector("button.btn-dark");
    // alert messages
    private final By errorMessage = By.xpath("//small[@style=\"color: rgb(238, 59, 66);\"]");
    private final By successMessage = By.xpath("//div[@class=\"modal-body\"]");

    public OperatorPage(WebDriver driver) {
        this.driver = driver;
    }
    

    public void openAddOperatorForm() {
        WaitUtils.safeClick(driver, addbutton);
    }

    public void addOperator(String name, String email, String contact) {
        
       openAddOperatorForm();
    WaitUtils.safeClick(driver, addOperatorpath);

    WaitUtils.sendKeys(driver, fullName, name);
    WaitUtils.sendKeys(driver, emailAddress, email);
    WaitUtils.sendKeys(driver, contactNumber, contact);

    Select select = new Select(
            WaitUtils.waitForElementVisible(driver, organiDropdown));
    select.selectByValue("MOCO");

    selectAllPermissions();

    try {
        WebElement submit = WaitUtils.waitForElementVisible(driver, submitButton);
        WaitUtils.scrollIntoView(driver, submit);
        submit.click();

    } catch (Exception e) {
        try {
            WebElement cancel = WaitUtils.waitForElementVisible(driver, cancelButton);
            WaitUtils.scrollIntoView(driver, cancel);
            cancel.click();

        } catch (Exception ex) {
            screenshots.takeScreenshot(driver, "AddOperatorError");
        }
    }
}


        private void selectAllPermissions() {
    List<WebElement> allCheckboxes =
            WaitUtils.waitForElementsVisible(driver, checkboxes);

    Actions actions = new Actions(driver);

    for (WebElement checkbox : allCheckboxes) {
        if (!checkbox.isSelected()) {
            WaitUtils.scrollIntoView(driver, checkbox);
            actions.moveToElement(checkbox).click().perform();
        }
    }
}


    public boolean isErrorMessageDisplayed(String message) {
        return driver.findElements(errorMessage)
                .stream()
                .anyMatch(e -> e.getText().contains(message));
    }

    public boolean isSuccessMessageDisplayed() {
        return driver.findElements(successMessage)
                .stream()
                .anyMatch(e -> e.isDisplayed());
    }

    public void viewOperator(String email) {
        By viewLink = By.xpath(
                "//a[contains(@href,'/operator/view') and contains(@href,'" + email + "')]");
        WaitUtils.safeClick(driver, viewLink);
    }
    
   

}