package onekonnect;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class OrdersListPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // --- Locators ---
    @FindBy(id = "order")
    private WebElement ordersTab;

    // --- Constructor ---
    public OrdersListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // --- Actions ---
    public void navigateToOrdersTab() {
        wait.until(ExpectedConditions.elementToBeClickable(ordersTab)).click();
    }

    /**
     * Checks if a customer is visible in the orders table.
     * @param customerName The name to search for.
     * @return true if found, false otherwise.
     */
    public boolean isCustomerPresent(String customerName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("tbody")));
        String xpath = String.format("//a[normalize-space()='%s']", customerName);
        List<WebElement> elements = driver.findElements(By.xpath(xpath));
        return !elements.isEmpty(); // More concise check
    }
}