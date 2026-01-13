# SeleniumProject AI Coding Instructions

## Project Overview
SeleniumProject is a **Selenium-based UI testing framework** for testing a Flight Ticket System. It follows the **Page Object Model (POM)** architecture with a clear separation between page interactions, test logic, and assertions.

**Tech Stack:** Java 19, Selenium 4.27.0, TestNG 7.10.2, Maven, WebDriverManager

## Architecture & Key Patterns

### Page Object Model (POM)
- **Location:** `src/test/java/np/com/moco/seleniumproject/pages/`
- Pages encapsulate locators and interaction methods; test classes consume them
- Example: `LoginPage` contains username/password locators and `login(user, pass)` method
- **Critical Pattern:** Pages accept WebDriver in constructor; tests instantiate pages with `driver` from BaseTest

### Test Structure
- **Base Class:** `BaseTest.java` - provides `protected WebDriver driver` and `protected ConfigReader config` to all tests
- **Tests Location:** `src/test/java/np/com/moco/seleniumproject/tests/user/`
- Tests extend BaseTest and access configuration via `config.getProperty("key")`
- TestNG annotations (`@BeforeMethod`, `@AfterMethod`, `@Test`) manage setup/teardown

### Assertions Pattern
- **Location:** `src/test/java/np/com/moco/seleniumproject/assertions/`
- Static assertion methods (e.g., `LoginAssertions.assertUserLogged(driver)`) separate assertion logic from tests
- Assertions use `WaitUtils` for element visibility checks before asserting
- On failure, capture debug screenshots automatically

## Configuration & Environment

### Config Management
- **File:** `src/test/resources/config/config.properties`
- **Reader:** `src/main/java/np/com/moco/seleniumproject/utils/ConfigReader.java`
- Properties accessed via `config.getProperty("url")`, `config.getProperty("username")`, etc.
- Current test URL: `https://test0.moco.com.np/flight/#/login`

### Implicit/Explicit Waits
- BaseTest sets 10-second implicit wait in `setUp()`
- `WaitUtils` provides explicit waits: `waitForElementVisible()` and `waitForElementClickable()` (use 5-10s timeouts)
- Use explicit waits in assertion helpers before checking element state

## Build & Test Execution

### Maven Commands
- `mvn clean test` - runs all tests via TestNG suite defined in pom.xml
- TestNG suite file: `src/test/resources/testng.xml`
- Surefire plugin configured to run `src/test/resources/testng.xml`

### TestNG Configuration
- Suite includes test classes: currently `GoogleTest` (add `LoginPageTest` for login tests)
- Tests run in Chrome browser (managed by WebDriverManager)

## Test Data Management

### Configuration-Based Test Data
Most test data is loaded from **properties files** at runtime:
```properties
# src/test/resources/config/config.properties
url=https://test0.moco.com.np/flight/#/login
username=vivek@moco.com.np
password=Lionhill1999@
```
**Usage in Tests:**
```java
loginPage.login(config.getProperty("username"), config.getProperty("password"));
```
This approach keeps credentials and URLs out of test code and allows environment-specific overrides.

### JSON Test Data (Future Pattern)
- **Location:** `src/test/resources/testdata/user.json`
- **Jackson dependency** is available for parsing JSON
- When implementing JSON data providers:
  1. Structure test data in `user.json` as JSON array/objects
  2. Create a data provider utility that parses JSON using Jackson's `ObjectMapper`
  3. Use TestNG's `@DataProvider` to pass JSON objects to parameterized tests
- Example pattern:
```java
@DataProvider(name = "loginUsers")
public Object[][] getLoginData() {
    // Parse user.json and return 2D array for parameterized @Test
}

@Test(dataProvider = "loginUsers")
public void testLoginWithData(UserDTO user) {
    loginPage.login(user.getEmail(), user.getPassword());
    // assertions...
}
```

## Screenshot & Debugging Workflow

### Automatic Screenshot Capture
- **Utility:** `screenshots.takeScreenshot(driver, "descriptive_name")`
- **Location:** Screenshots saved to `target/screenshots/` and also `screenshots/` directory
- **Automatic on Failure:** Called in assertion methods when `Assert.fail()` is triggered
- **Example from LoginAssertions:**
```java
try {
    WaitUtils.waitForElementVisible(driver, dashboardHeader, 10);
} catch (Exception e) {
    screenshots.takeScreenshot(driver, "login_failed");
    Assert.fail("Login failed: Dashboard not loaded");
}
```

### Debugging Workflow
1. **Run failing test:** `mvn clean test -Dtest=LoginPageTest#testSuccessfulLogin`
2. **Check screenshot output:**
   - Look in `target/screenshots/` or `screenshots/` for `.png` files
   - Console logs show: `Screenshot saved: [absolute_path]`
3. **Analyze:** Compare actual vs. expected UI state in screenshots
4. **Fix:** Update locators, waits, or page logic based on findings
5. **Rerun:** Execute same test to verify fix

### Screenshot Best Practices
- Use **descriptive names** for screenshots: `"login_failed"`, `"email_error_missing"`, `"dashboard_loaded"`
- Capture on **both success and failure** for critical assertions (currently only on failure)
- Filenames automatically get `.png` extension; don't include it in the parameter

## Common Workflows

### Adding a New Test
1. Create test class in `src/test/java/np/com/moco/seleniumproject/tests/user/`
2. Extend `BaseTest` to inherit `driver` and `config`
3. Instantiate required page objects: `LoginPage loginPage = new LoginPage(driver)`
4. Call page methods and assert results using either TestNG `Assert` or dedicated assertion classes
5. Example from `LoginPageTest.testSuccessfulLogin()`:
```java
loginPage.login(config.getProperty("username"), config.getProperty("password"));
LoginAssertions.assertUserLogged(driver);
```

### Adding a New Page Object
1. Create class in `src/test/java/np/com/moco/seleniumproject/pages/`
2. Define locators as `private final By` fields using appropriate selectors:
```java
private final By username = By.id("login_user");
private final By loginBtn = By.xpath("//button[contains(@class,'btn-primary')]");
```
3. Implement interaction methods that accept necessary parameters (avoid exposing WebDriver)
4. Use `WaitUtils` for explicit waits when clicking or waiting for visibility
5. Example from `LoginPage.login()`:
```java
public void login(String user, String pass) {
    driver.findElement(username).clear();
    driver.findElement(username).sendKeys(user);
    driver.findElement(password).clear();
    driver.findElement(password).sendKeys(pass);
    WaitUtils.waitForElementClickable(driver, loginBtn, 5).click();
}
```

### Handling Element Interactions
- **Before clicking:** Check `isEnabled()` to avoid `ElementClickInterceptedException`
```java
WebElement btn = driver.findElement(loginBtn);
if (btn.isEnabled()) {
    WaitUtils.waitForElementClickable(driver, loginBtn, 5).click();
}
```
- **On intercepted click:** Catch exception and let post-condition assertions verify outcome
```java
try {
    btn.click();
} catch (ElementClickInterceptedException e) {
    // Click failed due to overlay/disabled state — tests will assert post-conditions
}
```
- **Text input:** Always clear before sending new text to avoid duplicate data
```java
driver.findElement(username).clear();
driver.findElement(username).sendKeys(user);
```

## Project-Specific Conventions

### Naming Conventions with Examples
- **Test classes:** `*Test.java` (e.g., `LoginPageTest.java`, `GoogleTest.java`)
- **Page classes:** `*Page.java` (e.g., `LoginPage.java`, `GoogleSearchPage.java`)
- **Assertion classes:** `*Assertions.java` (e.g., `LoginAssertions.java`)
- **Locators:** camelCase short names reflecting purpose:
  - `username` not `usernameInputField`
  - `loginBtn` not `login_button_primary`
  - `dashboardHeader` for dashboard-specific elements

### Assertion Strategy with Examples
- **Dedicated assertion classes** isolate verification logic from test execution:
```java
// LoginAssertions.java
public static void assertUserLogged(WebDriver driver) {
    try {
        WaitUtils.waitForElementVisible(driver, dashboardHeader, 10);
    } catch (Exception e) {
        screenshots.takeScreenshot(driver, "login_failed");
        Assert.fail("Login failed: Dashboard not loaded");
    }
}

// LoginPageTest.java
@Test
public void testSuccessfulLogin() {
    loginPage.login(config.getProperty("username"), config.getProperty("password"));
    LoginAssertions.assertUserLogged(driver);  // reusable assertion
}
```
- **Assertion methods are static** for easy invocation across tests
- **Always wrap waits in try-catch** to capture diagnostic screenshots on failure

### Error Handling Patterns
- **Don't fail on `ElementClickInterceptedException`** — overlay/disabled state is temporary:
```java
try {
    WaitUtils.waitForElementClickable(driver, loginBtn, 5).click();
} catch (ElementClickInterceptedException e) {
    // Silently continue; post-condition assertions will catch actual issues
}
```
- **Assertion failures should provide context:**
```java
// Good: describes expected state and reason
Assert.assertFalse(loginPage.isUserDisplayed(), 
    "User should not be logged in with empty credentials");

// Bad: generic message
Assert.assertFalse(loginPage.isUserDisplayed());
```
- **Use descriptive screenshot names** for root cause analysis:
```java
screenshots.takeScreenshot(driver, "login_failed");      // ✓ Clear intent
screenshots.takeScreenshot(driver, "screenshot1");       // ✗ Not descriptive
```

## Dependencies & Utilities

| Utility | Location | Purpose |
|---------|----------|---------|
| `WaitUtils` | `src/test/java/.../utils/` | Explicit wait helpers (visible, clickable) |
| `ConfigReader` | `src/main/java/.../utils/` | Load properties from config.properties |
| `screenshots` | `src/test/java/.../utils/` | Capture debug screenshots on failure |

## Integration Points

- **WebDriverManager:** Auto-manages ChromeDriver binary; no manual driver download needed
- **Jackson:** Available for JSON parsing (test data in `testdata/user.json`)
- **Commons-IO:** Available for file operations if needed

## Testing Best Practices for This Project

- Always extend `BaseTest` instead of managing WebDriver manually
- Use `config.getProperty()` for test data (avoid hardcoding credentials)
- Capture screenshots on assertion failures for root cause analysis
- Run `mvn clean test` before committing to verify all tests pass
- Add new test cases to `src/test/resources/testng.xml` to include them in suite runs
