package onekonnect;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // --- Locators ---
    @FindBy(id = "UserName")
    private WebElement usernameField;

    @FindBy(id = "Password")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@class='submitbtn']")
    private WebElement loginBtn;

    @FindBy (id = "loginForm")
    private WebElement loginForm;

    // --- Constructor ---
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // --- Actions ---
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginBtn)).click();
    }

    public WebElement getLoginForm() {
        wait.until(ExpectedConditions.visibilityOf(loginForm));
        return loginForm;
    }

    /**
     * Performs the full login action and returns the next page (Dashboard).
     * This is called "Page Chaining".
     * @param username The username to enter.
     * @param password The password to enter.
     * @return An instance of the DashboardPage.
     */
    public DashboardPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();

        return new DashboardPage(driver);
    }
}