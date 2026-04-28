package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import utils.ConfigReader;

public class OrderTest extends BaseTest {

    LoginPage loginPage;
    OrderPage orderPage;

    @BeforeMethod
    public void init() {

        System.out.println("Initializing order test");

        loginPage = new LoginPage(getDriver());
        orderPage = new OrderPage(getDriver());

        // Only login (no order creation)
        loginPage.login(
                ConfigReader.get("validEmail"),
                ConfigReader.get("validPassword")
        );

        loginPage.waitForPageLoad();

        System.out.println("Login completed for order module");
    }

    // Verify order history
    @Test
    public void testOrderHistory() {

        System.out.println("Navigating to orders page");

        orderPage.goToOrders();

        boolean status = orderPage.isOrderPresent();

        Assert.assertTrue(status, "No orders found");

        System.out.println("Orders are present");
    }

    // Verify order details
    @Test
    public void testOrderDetails() {

        orderPage.goToOrders();

        System.out.println("Opening first order");

        orderPage.openFirstOrder();

        String product = orderPage.getOrderProductName();

        Assert.assertTrue(
                product.length() > 0,
                "Order details not displayed"
        );

        System.out.println("Order details verified");
    }
}