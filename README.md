#  ShopPractice – Selenium Java Automation Framework

##  Project Overview

ShopPractice is a **Selenium-Java Automation Test Framework** built to automate an e-commerce practice application.
The framework is designed using **Page Object Model (POM)** and follows best practices for scalability, maintainability, and reusability.

---

##  Objective

This project was developed as part of a hackathon to demonstrate:

* Selenium WebDriver automation skills
* Page Object Model design
* TestNG framework usage
* Explicit wait strategies
* Screenshot handling
* Extent Report integration

---

## Application Under Test

* URL: https://rahulshettyacademy.com/client

---

### Project Structure

```
ShopPractice-Automation/
│
├── pom.xml
│
├── reports/
│   └── ExtentReport.html
│
├── screenshots/
│   └── (failure screenshots will be saved here)
│
├── src/
│   │
│   ├── main/
│   │   └── java/
│   │       │
│   │       ├── base/
│   │       │   ├── BaseTest.java
│   │       │   └── BasePage.java
│   │       │
│   │       ├── pages/
│   │       │   ├── LoginPage.java
│   │       │   ├── DashboardPage.java
│   │       │   ├── CartPage.java
│   │       │   ├── CheckoutPage.java
│   │       │   └── OrderPage.java
│   │       │
│   │       └── utils/
│   │           ├── ConfigReader.java
│   │           ├── ScreenshotUtil.java
│   │           └── ExtentManager.java
│   │
│   ├── test/
│   │   ├── java/
│   │   │   │
│   │   │   ├── tests/
│   │   │   │   ├── LoginTest.java
│   │   │   │   ├── ProductTest.java
│   │   │   │   ├── CartTest.java
│   │   │   │   └── OrderTest.java
│   │   │   │
│   │   │   └── listeners/
│   │   │       └── TestListener.java
│   │   │
│   │   └── resources/
│   │       │
│   │       ├── config.properties
│   │       ├── testng.xml
│   │      
│   │
│   └── target/   (auto-generated)
│
└── reports/
└── screenshots/
└── README.md

```

---

## Technologies Used

* Java
* Selenium WebDriver
* TestNG
* WebDriverManager
* Extent Reports
* Maven

---

## Key Features

✔ Page Object Model (POM)
✔ Data-driven testing using config.properties
✔ Explicit waits (WebDriverWait)
✔ Screenshot capture on failure
✔ Extent HTML reports
✔ Clean reusable framework design
✔ Thread-safe WebDriver (ThreadLocal)

---

## Test Modules Covered

### Module 1 – Authentication

* Valid login
* Invalid login
* Empty login validation
* Logout

---

### Module 2 – Product Listing

* Product visibility
* Product name & price validation
* Add product to cart
* Add multiple products

---

### Module 3 – Cart & Checkout

* Verify cart items
* Remove items
* Checkout process
* Place order

---

### Module 4 – Order History

* View order history
* Verify order presence
* Validate order details

---

## Wait Strategy

* Used **WebDriverWait + ExpectedConditions**
* Handled dynamic elements like:

  * Loaders (spinner)
  * Dropdowns
  * Async page updates

---

## Screenshot Handling

* Screenshots captured:

  * On test failure (via TestNG Listener)
  * On critical UI interaction failures
* Stored inside `/screenshots/` folder

---

## Reporting

* Integrated **Extent Reports**
* Generates:

  * HTML report
  * Test status (Pass/Fail)
  * Error logs
  * Screenshot attachments

---

## How to Run

### Run via Maven:

```
mvn test
```

### Or:

* Right click `testng.xml`
* Run as TestNG Suite

---

## Configuration

Update values in:

```
src/test/resources/config.properties
```

Example:

```
browser=chrome
baseUrl=https://rahulshettyacademy.com/client
timeout=10

validEmail=your_email
validPassword=your_password
invalidPassword=wrong123
```

---

---

## Key Learning

* Handling dynamic web elements
* Designing scalable automation frameworks
* Synchronization using explicit waits
* Debugging real-world Selenium issues

---

## Author

Bhavya Sree Kasa

---

## Conclusion

This project demonstrates a **complete automation framework** covering real-world testing scenarios with clean architecture and professional reporting.

---
