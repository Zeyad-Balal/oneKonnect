package onekonnect_test;

import base.Base_Tests;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import onekonnect.CreateOrderPage;
import onekonnect.DashboardPage;
import onekonnect.LoginPage;
import onekonnect.OrdersListPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CreateOrderTest extends Base_Tests {

    private String username;
    private String password;

    @BeforeClass
    public void loadLoginData() throws IOException {
        String jsonPath = System.getProperty("user.dir") + "/src/test/resources/login_data.json";
        ObjectMapper mapper = new ObjectMapper();
        var data = mapper.readValue(new File(jsonPath), new TypeReference<Map<String, String>>() {});
        username = data.get("username");
        password = data.get("password");
    }

    /**
     * This DataProvider now reads the JSON into a List of Maps.
     */
    @DataProvider(name = "orderDataProvider")
    public Object[][] getOrderData() throws IOException {
        String jsonPath = System.getProperty("user.dir") + "/src/test/resources/orderData.json";
        ObjectMapper mapper = new ObjectMapper();

        // ** KEY CHANGE #1 **
        List<Map<String, String>> orderDataList = mapper.readValue(new File(jsonPath), new TypeReference<>() {});

        Object[][] data = new Object[orderDataList.size()][1];
        for (int i = 0; i < orderDataList.size(); i++) {
            data[i][0] = orderDataList.get(i);
        }
        return data;
    }

    /**
     * The @Test method now accepts a Map as its parameter.
     */
    @Test(dataProvider = "orderDataProvider")
    public void createOrderSuccessfully(Map<String, String> data) { // ** KEY CHANGE #2 **
        // --- Test Steps ---
        LoginPage loginPage = new LoginPage(driver);

        Assert.assertTrue(loginPage.getLoginForm().isDisplayed());
        DashboardPage dashboardPage = loginPage.login(username, password);

        //Assert on Dashboard
        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = prop.getProperty("baseUrl")+"PartnerOrders/PartnerDashBoard";
        Assert.assertEquals(actualUrl, expectedUrl, "The URL did not match what was expected.");

        System.out.println("dashboard page : " + expectedUrl);

        CreateOrderPage createOrderPage = dashboardPage.navigateToAddOrderPage();

        // ** KEY CHANGE #3 **
        // Access data using the map's get() method with the JSON key as a string.
        createOrderPage.fillNewOrderForm(
                data.get("pContactName"), data.get("pContactPhone"), data.get("pContactEmail"),
                data.get("customerName"), data.get("customerCode"), data.get("contactName"), data.get("contactEmail"),
                data.get("customerAddress"), data.get("customerCity"), data.get("customerState"), data.get("primaryCode"),
                data.get("companyName"), data.get("companyCity"), data.get("companyState"), data.get("federalTaxId"),
                data.get("companyAddress"), data.get("connectionsNumber"), data.get("employeesNumber"), data.get("monthYear") , data.get("day"),
                data.get("carrierName"), data.get("carrierContact"), data.get("carrierEmail"), data.get("cobraMembers")
        );

        OrdersListPage ordersListPage = createOrderPage.submitOrderAndConfirm();
        ordersListPage.navigateToOrdersTab();

        // --- Verification ---
        String customerName = data.get("customerName");
        Assert.assertTrue(ordersListPage.isCustomerPresent(customerName),
                "Test Case '" + data.get("testCaseName") + "' Failed: The customer '" + customerName + "' was not found.");

        System.out.println("customer name : " + customerName);
        System.out.println("Test Case '" + data.get("testCaseName") + "' Passed.");
    }
}