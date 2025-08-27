package onekonnect;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CreateOrderPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // --- Locators ---
    @FindBy(id = "partnerContactName")
    private WebElement partnerContactName;
    @FindBy(id = "partnerContactPhone")
    private WebElement partnerContactPhone;
    @FindBy(id = "partnerContactEmail")
    private WebElement partnerContactEmail;
    @FindBy(id = "CustomerName")
    private WebElement customerName;
    @FindBy(id = "CustomerCode")
    private WebElement customerCode;
    @FindBy(id = "ContactName")
    private WebElement contactName;
    @FindBy(id = "ContactEmail")
    private WebElement contactEmail;
    @FindBy(id = "CustomerAdd")
    private WebElement customerAddress;
    @FindBy(id = "CustomerCity")
    private WebElement customerCity;
    @FindBy(id = "StateID")
    private WebElement customerStateDropdown;
    @FindBy(id = "PrimaryCode")
    private WebElement primaryCode;
    @FindBy(id = "CompName_0")
    private WebElement companyName;
    @FindBy(id = "CompCity_0")
    private WebElement companyCity;
    @FindBy(id = "CompStateID_0")
    private WebElement companyStateDropdown;
    @FindBy(id = "CompFedralTaxID_0")
    private WebElement federalTaxId;
    @FindBy(id = "CompAddress_0")
    private WebElement companyAddress;
    @FindBy(id = "ConnectionsNumber")
    private WebElement connectionsNumber;
    @FindBy(id = "EmployeesNumber")
    private WebElement employees_number;
    @FindBy(id = "PlanYearStartDate")
    private WebElement planStartDate;
    @FindBy(id = "CarrierName_0")
    private WebElement carrierName;
    @FindBy(id = "ContactName_0")
    private WebElement contactName0;
    @FindBy(id = "ContactEmail_0")
    private WebElement contactEmail0;
    @FindBy(id = "CobraMembers_0")
    private WebElement cobraMembers0;
    @FindBy(id = "UASub")
    private WebElement submitOrderBtn;
    @FindBy(id = "confirm_id")
    private WebElement confirm_order;
    @FindBy(id = "success_id")
    private WebElement success_order_btn;

    // --- Constructor ---
    public CreateOrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    // --- High-Level Action Method ---
    public void fillNewOrderForm(String pContactName, String pContactPhone, String pContactEmail,
                                 String custName, String custCode, String cName, String cEmail,
                                 String custAddress, String custCity, String custState, String pCode,
                                 String compName, String compCity, String compState, String fedTaxId,
                                 String compAddress, String connNum, String empNum, String monthYear, String day,
                                 String carrier, String carrierContact, String carrierEmail, String cobra) {
        setPartnerContactName(pContactName);
        setPartnerContactPhone(pContactPhone);
        setPartnerContactEmail(pContactEmail);
        setCustomerName(custName);
        setCustomerCode(custCode);
        setContactName(cName);
        setContactEmail(cEmail);
        setCustomerAddress(custAddress);
        setCustomerCity(custCity);
        selectCustomerState(custState);
        setPrimaryCode(pCode);
        setCompanyName(compName);
        setCompanyCity(compCity);
        selectCompanyState(compState);
        setFederalTaxId(fedTaxId);
        setCompanyAddress(compAddress);
        setConnectionsNumber(connNum);
        setEmployeesNumber(empNum);
        selectPlanStartDate(monthYear, day);
        setCarrierName(carrier);
        setContactName0(carrierContact);
        setContactEmail0(carrierEmail);
        setCobraMembers0(cobra);
        selectPlanByLabel("1");
    }

    public OrdersListPage submitOrderAndConfirm() {
        clickSubmitOrder();
        clickConfirmOrder();
        clickSuccessOrder();
        return new OrdersListPage(driver);
    }

    // --- Private Helper and Individual Action Methods ---
    private void typeInto(WebElement element, String text) {
        WebElement field = wait.until(ExpectedConditions.visibilityOf(element));
        field.clear();
        field.sendKeys(text);
    }

    private void selectFromDropdownByText(WebElement dropdown, String text) {
        scrollIntoElement(dropdown);
        WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOf(dropdown));
        new Select(dropdownElement).selectByVisibleText(text);
    }

    private void scrollIntoElement(WebElement ele) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
    }

    // Individual field setters
    public void setPartnerContactName(String name) { typeInto(partnerContactName, name); }
    public void setPartnerContactPhone(String phone) { typeInto(partnerContactPhone, phone); }
    public void setPartnerContactEmail(String email) { typeInto(partnerContactEmail, email); }
    public void setCustomerName(String name) { typeInto(customerName, name); }
    public void setCustomerCode(String code) { typeInto(customerCode, code); }
    public void setContactName(String name) { typeInto(contactName, name); }
    public void setContactEmail(String email) { typeInto(contactEmail, email); }
    public void setCustomerAddress(String address) { typeInto(customerAddress, address); }
    public void setCustomerCity(String city) { typeInto(customerCity, city); }
    public void setPrimaryCode(String code) { typeInto(primaryCode, code); }
    public void setCompanyName(String name) { typeInto(companyName, name); }
    public void setCompanyCity(String city) { typeInto(companyCity, city); }
    public void setFederalTaxId(String taxId) { typeInto(federalTaxId, taxId); }
    public void setCompanyAddress(String address) { typeInto(companyAddress, address); }
    public void setConnectionsNumber(String number) { typeInto(connectionsNumber, number); }
    public void setEmployeesNumber(String number) { typeInto(employees_number, number); }
    public void setCarrierName(String name) { typeInto(carrierName, name); }
    public void setContactName0(String name) { typeInto(contactName0, name); }
    public void setContactEmail0(String email) { typeInto(contactEmail0, email); }
    public void selectCustomerState(String stateName) { selectFromDropdownByText(customerStateDropdown, stateName); }
    public void selectCompanyState(String stateName) { selectFromDropdownByText(companyStateDropdown, stateName); }
    public void setCobraMembers0(String members) { selectFromDropdownByText(cobraMembers0, members); }

    public void selectPlanStartDate(String monthYear, String day) {
        scrollIntoElement(planStartDate);
        wait.until(ExpectedConditions.elementToBeClickable(planStartDate)).click();
        By datePickerSwitchLocator = By.cssSelector(".datepicker-days .datepicker-switch");
        By nextButtonLocator = By.cssSelector(".datepicker-days .next");
        WebElement datePickerSwitch = wait.until(ExpectedConditions.visibilityOfElementLocated(datePickerSwitchLocator));
        while (!datePickerSwitch.getText().equals(monthYear)) {
            wait.until(ExpectedConditions.elementToBeClickable(nextButtonLocator)).click();
            datePickerSwitch = wait.until(ExpectedConditions.visibilityOfElementLocated(datePickerSwitchLocator));
        }
        By dayLocator = By.xpath("//td[@class='day' and text()='" + day + "']");
        wait.until(ExpectedConditions.elementToBeClickable(dayLocator)).click();
    }

    public void selectPlanByLabel(String plan_index) {
        By checkboxLocator = By.id("Carrier_0_PlanChecked_" + plan_index);
        WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(checkboxLocator));
        scrollIntoElement(checkbox);
        wait.until(ExpectedConditions.elementToBeClickable(checkboxLocator));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void clickSubmitOrder() {
        scrollIntoElement(submitOrderBtn);
        wait.until(ExpectedConditions.elementToBeClickable(submitOrderBtn)).click();
    }

    public void clickConfirmOrder() {
        scrollIntoElement(confirm_order);
        wait.until(ExpectedConditions.elementToBeClickable(confirm_order)).click();
    }

    public void clickSuccessOrder() {
        scrollIntoElement(success_order_btn);
        wait.until(ExpectedConditions.elementToBeClickable(success_order_btn)).click();
    }
}