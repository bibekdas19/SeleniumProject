
package np.com.moco.seleniumproject.assertions;

import np.com.moco.seleniumproject.pages.OperatorPage;
import org.testng.Assert;

public class OperatorAssertions {

    private final OperatorPage operatorPage;

    public OperatorAssertions(OperatorPage operatorPage) {
        this.operatorPage = operatorPage;
    }

    public void verifyInvalidEmailError() {
        Assert.assertTrue(
                operatorPage.isErrorMessageDisplayed("Email is not valid"),
                "Email is not valid."
        );
    }

    public void verifyEmptyEmailError() {
        Assert.assertTrue(
                operatorPage.isErrorMessageDisplayed("required"),
                "Empty email validation message not displayed"
        );
    }

    public void verifyInvalidContactError() {
        Assert.assertTrue(
                operatorPage.isErrorMessageDisplayed("Invalid"),
                "Invalid contact validation message not displayed"
        );
    }

    public void verifyInvalidNameError() {
        Assert.assertTrue(
                operatorPage.isErrorMessageDisplayed("Invalid"),
                "Invalid name validation message not displayed"
        );
    }

    public void verifyEmptyNameError() {
        Assert.assertTrue(
                operatorPage.isErrorMessageDisplayed("required"),
                "Empty name validation message not displayed"
        );
    }

    public void verifyOperatorAddedSuccessfully() {
        Assert.assertTrue(
                operatorPage.isSuccessMessageDisplayed(),
                "Operator success message not displayed"
        );
    }
}

