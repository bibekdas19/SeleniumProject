package np.com.moco.seleniumproject.pages;

import np.com.moco.seleniumproject.utils.WaitUtils;
import np.com.moco.seleniumproject.utils.screenshots;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class OperatorPage {

    private final WebDriver driver;

    // ===== LOCATORS =====
    private final By addButton = By.xpath("//a[contains(@href,'operator')]");
    private final By addOperatorPath = By.xpath("//a[contains(@href,'operator/add')]");
    private final By fullName = By.xpath("//input[@placeholder='Full Name']");
    private final By emailAddress = By.xpath("//input[@placeholder='Email Address']");
    private final By contactNumber = By.xpath("//input[@placeholder='Contact Number']");
    private final By organiDropdown = By.cssSelector("select.form-select");
    private final By checkboxes = By.xpath("//input[@class='form-check-input' and @type='checkbox']");
    private final By submitButton = By.cssSelector("button.btn");
    private final By cancelButton = By.cssSelector("button.btn-dark");

    // alert messages
    private final By errorMessage = By.xpath("//small[@style='color: rgb(238, 59, 66);']");
    private final By successMessage = By.xpath("//div[@class='modal-body']");

    // ===== CONSTRUCTOR =====
    public OperatorPage(WebDriver driver) {
        this.driver = driver;
    }

    // ===== NAVIGATION =====
    public void openAddOperatorForm() {
        WaitUtils.safeClick(driver, addButton);
        WaitUtils.safeClick(driver, addOperatorPath);
    }

    // ===== OPERATOR ACTIONS =====
    public void addOperator(String name, String email, String contact) {
        try {
            openAddOperatorForm();

            WaitUtils.sendKeys(driver, fullName, name);
            WaitUtils.sendKeys(driver, emailAddress, email);
            WaitUtils.sendKeys(driver, contactNumber, contact);

            // Select organization
            Select select = new Select(WaitUtils.waitForElementVisible(driver, organiDropdown));
            select.selectByValue("MOCO");

            selectAllPermissions();
            clickSubmit();

        } catch (Exception e) {
            screenshots.takeScreenshot(driver, "AddOperatorError");
            // fallback: try clicking cancel if submit fails
            try {
                WaitUtils.safeClick(driver, cancelButton);
            } catch (Exception ignored) {}
        }
    }

    private void selectAllPermissions() {
        List<WebElement> allCheckboxes = WaitUtils.waitForElementsVisible(driver, checkboxes);
        Actions actions = new Actions(driver);

        for (WebElement checkbox : allCheckboxes) {
            if (!checkbox.isSelected()) {
                WaitUtils.scrollIntoView(driver, checkbox);
                actions.moveToElement(checkbox).click().perform();
            }
        }
    }

    private void clickSubmit() {
        WebElement submit = WaitUtils.waitForElementClickable(driver, submitButton);
        WaitUtils.scrollIntoView(driver, submit);
        submit.click();
    }

    // ===== VALIDATIONS =====
    public boolean isErrorMessageDisplayed(String message) {
        try {
            List<WebElement> errors = WaitUtils.waitForElementsVisible(driver, errorMessage);
            return errors.stream().anyMatch(e -> e.getText().contains(message));
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    public boolean isSuccessMessageDisplayed() {
        try {
            return WaitUtils.waitForElementVisible(driver, successMessage).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    // ===== VIEW OPERATOR =====
    public void viewOperator(String email) {
        By viewLink = By.xpath("//a[contains(@href,'/operator/view') and contains(@href,'" + email + "')]");
        WaitUtils.safeClick(driver, viewLink);
    }
}
