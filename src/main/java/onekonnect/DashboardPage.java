package onekonnect;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DashboardPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // --- Locators ---
    @FindBy(xpath = "//li[contains(@class,'greenboxlink')]")
    private WebElement addBtn;

    @FindBy(id = "AddEDI")
    private WebElement addEDI;

    // --- Constructor ---
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // --- Actions ---
    /**
     * Navigates from the dashboard to the order creation form.
     * @return An instance of CreateOrderPage.
     */
    public CreateOrderPage navigateToAddOrderPage() {
        wait.until(ExpectedConditions.elementToBeClickable(addBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(addEDI)).click();
        return new CreateOrderPage(driver);
    }
}