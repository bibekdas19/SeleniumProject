package np.com.moco.seleniumproject.assertions;

import np.com.moco.seleniumproject.pages.OperatorPage;
import org.testng.Assert;

public class OperatorAssertions {

    private final OperatorPage operatorPage;

    // ===== COMMON ERROR MESSAGES =====
    private static final String REQUIRED_ERROR = "required";
    private static final String INVALID_ERROR = "Invalid";
    private static final String INVALID_EMAIL_ERROR = "Email is not valid";

    public OperatorAssertions(OperatorPage operatorPage) {
        this.operatorPage = operatorPage;
    }

    // ===== GENERAL ERROR VERIFICATION =====
    private void verifyErrorMessage(String expectedMessage, String failMessage) {
        Assert.assertTrue(operatorPage.isErrorMessageDisplayed(expectedMessage), failMessage);
    }

    // ===== SPECIFIC ERROR VERIFICATIONS =====
    public void verifyInvalidEmailError() {
        verifyErrorMessage(INVALID_EMAIL_ERROR, "Email is not valid.");
    }

    public void verifyEmptyEmailError() {
        verifyErrorMessage(REQUIRED_ERROR, "Empty email validation message not displayed");
    }

    public void verifyInvalidContactError() {
        verifyErrorMessage(INVALID_ERROR, "Invalid contact validation message not displayed");
    }

    public void verifyInvalidNameError() {
        verifyErrorMessage(INVALID_ERROR, "Invalid name validation message not displayed");
    }

    public void verifyEmptyNameError() {
        verifyErrorMessage(REQUIRED_ERROR, "Empty name validation message not displayed");
    }

    // ===== SUCCESS VERIFICATION =====
    public void verifyOperatorAddedSuccessfully() {
        Assert.assertTrue(operatorPage.isSuccessMessageDisplayed(),
                "Operator success message not displayed");
    }
}
