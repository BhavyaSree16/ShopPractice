package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.DashboardPage;
import utils.ConfigReader;

public class ProductTest extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;

    @BeforeMethod
    public void init() {

        System.out.println("Initializing product test");

        loginPage = new LoginPage(getDriver());
        dashboardPage = new DashboardPage(getDriver());

        // Login first
        loginPage.login(
                ConfigReader.get("validEmail"),
                ConfigReader.get("validPassword")
        );

        loginPage.waitForPageLoad();

        System.out.println("Login successful, dashboard opened");
    }

    // Verify dashboard loads
    @Test
    public void testProductDashboard() {

        System.out.println("Checking if products are displayed");

        Assert.assertTrue(
                dashboardPage.isProductDisplayed(),
                "No products found on dashboard"
        );

        System.out.println("Products are displayed");
    }

    // Verify product details
    @Test
    public void testProductDetails() {

        System.out.println("Checking product name and price");

        Assert.assertTrue(
                dashboardPage.verifyProductDetails(),
                "Product details missing"
        );

        System.out.println("All products have name and price");
    }

    // Add single product
    @Test
    public void testAddProductToCart() {

        System.out.println("Adding product to cart");

        dashboardPage.addProductToCart("ZARA COAT 3");

        int count = dashboardPage.getCartCount();

        System.out.println("Cart count after adding product is " + count);

        Assert.assertEquals(count, 1);

        System.out.println("Product added to cart successfully");
    }

    // Add multiple products
    @Test
    public void testAddMultipleProducts() {

        System.out.println("Adding multiple products");

        dashboardPage.addProductToCart("ZARA COAT 3");
        dashboardPage.addProductToCart("ADIDAS ORIGINAL");

        int count = dashboardPage.getCartCount();

        System.out.println("Cart count after adding multiple products is " + count);

        Assert.assertTrue(count >= 2);

        System.out.println("Multiple products added successfully");
    }
}