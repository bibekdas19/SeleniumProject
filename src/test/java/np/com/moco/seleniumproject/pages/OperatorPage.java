package np.com.moco.seleniumproject.pages;

import np.com.moco.seleniumproject.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
//import java.util.List;



public class OperatorPage {
    private final WebDriver driver;
    private final By operatorSection = By.xpath("//*[@id=\"app\"]/div/div[1]/div[2]/div[1]/div[2]/div/div/div/div[2]/a");
    private final By addbutton = By.xpath("//*[@id=\"app\"]/div/div[2]/div[2]/div/div[1]/div/div/div[2]/a");
    private final By fullName = By.xpath("//input[@placeholder=\"Full Name\"]");
    private final By emailAddress = By.xpath("//input[@placeholder=\"Email Address\"]");
    private final By contactNumber = By.xpath("//input[@placeholder=\"Contact Number\"]");
    private final By organiDropdown = By.cssSelector("select.form-select");
    private final By checkboxes = By.xpath("//input[@class='form-check-input' and @type='checkbox']");
    private final By submitButton = By.cssSelector("button.btn");
    //alert messages
    private final By errorMessage = By.xpath("//small[@style=\"color: rgb(238, 59, 66);\"]");
    private final By successMessage = By.xpath("//div[@class=\"modal-body\"]");    

    public OperatorPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openAddOperatorForm() {
        WaitUtils.safeClick(driver, operatorSection);
        WaitUtils.safeClick(driver, addbutton);
    }
    
    public void addOperator(String name, String email, String contact) {
        openAddOperatorForm();

        WaitUtils.sendKeys(driver, fullName, name);
        WaitUtils.sendKeys(driver, emailAddress, email);
        WaitUtils.sendKeys(driver, contactNumber, contact);

        Select select = new Select(
                WaitUtils.waitForElementVisible(driver, organiDropdown)
        );
        select.selectByValue("MOCO");

        selectAllPermissions();

        WaitUtils.safeClick(driver, submitButton);
    }

    private void selectAllPermissions() {
    for (WebElement checkbox : WaitUtils.waitForElementVisible (driver, checkboxes)) {
        WaitUtils.scrollIntoView(driver, (WebElement) checkboxes);

        if (!checkbox.isSelected()) {
            WaitUtils.safeClick(driver, checkboxes);
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
                "//a[contains(@href,'/operator/view') and contains(@href,'" + email + "')]"
        );
        WaitUtils.safeClick(driver, viewLink);
    }
    
    
    
    
    
    
}

