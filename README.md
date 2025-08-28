
# OneKonnect Automation

This repository contains automated tests for the OneKonnect application. The Tests are built using Java, Selenium, TestNG, and Maven. It follows the Page Object Model (POM) design pattern for maintainability and scalability.

## Manual Test Cases

https://docs.google.com/document/d/1qvt_aOJPUdW5zKosJVBA0mZYvdPaBpbDu9c2hk0AOOI/edit?usp=sharing

## Prerequisites

Before you can run the tests, please ensure you have the following software installed on your system:

* **Java Development Kit (JDK)**: Version 11 or higher.
* **Apache Maven**: To manage project dependencies and run tests.
* **A Web Browser**: Google Chrome or Mozilla Firefox.
* **WebDriver**: The test setup in `Base_Tests.java` uses `new ChromeDriver()` and `new FirefoxDriver()` directly. For this to work

## Setup

1.  **Clone the Repository**
    Clone this repository to your local machine using the following command:

    ```bash
    git clone https://github.com/Zeyad-Balal/oneKonnect.git
    ```

2.  **Install Dependencies**
    Navigate into the cloned project directory and use Maven to download all the necessary dependencies defined in the `pom.xml` file.

    ```bash
    cd oneKonnect
    mvn clean install
    ```

## Configuration

Before running the tests, you need to configure your test environment and data in the `src/test/resources` directory.

1.  **`config.properties`**
    Set the browser and the base URL for the application.

    ```properties
    browser=firefox
    baseUrl=https://devtesting.onekonnect.com/eBNPartnerPortal_test2/
    ```

2.  **`login_data.json`**
    Provide valid login credentials. The test will use these to log in before creating an order.

    ```json
    {
      "username": "TestingAutomation",
      "password": "Interview@123!",
      "expectedPath": "PartnerOrders/PartnerDashBoard"
    }
    ```

3.  **`orderData.json`**
    This file contains the data for creating new orders. The test is data-driven, meaning it will run once for each JSON object in this file's array. You can add more objects to create more test cases without changing the code.

## How to Run the Tests

You can run the automated tests using either Maven from the command line or directly from your IDE.

### Option 1: Using Maven (Command Line)

This is the recommended way to run the entire test suite, especially for CI/CD environments.

Navigate to the root directory of the project and run the following command:

```bash
mvn test
```

Maven will automatically find and execute the TestNG tests defined in `CreateOrderTest.java`. Test results will be generated in the `target/surefire-reports` directory.

### Option 2: Using an IDE (IntelliJ or Eclipse)

You can easily run tests directly from your integrated development environment.

1.  **Open the Project**: Open the `pom.xml` file as a project in your IDE.
2.  **Run a Test Class**: Navigate to `src/test/java`, find the `CreateOrderTest.java` file, right-click on it, and select **"Run 'CreateOrderTest'"**.
3.  **Run a Single Test Method**: You can also open the `CreateOrderTest.java` file, right-click on a specific test method (e.g., `createOrderSuccessfully`), and select the run option to execute only that test.