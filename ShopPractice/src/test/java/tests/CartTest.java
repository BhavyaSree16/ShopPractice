package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import utils.ConfigReader;

public class CartTest extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    String product = "ZARA COAT 3";

    @BeforeMethod
    public void init() {

        System.out.println("Initializing cart test");

        loginPage = new LoginPage(getDriver());
        dashboardPage = new DashboardPage(getDriver());
        cartPage = new CartPage(getDriver());
        checkoutPage = new CheckoutPage(getDriver());

        loginPage.login(
                ConfigReader.get("validEmail"),
                ConfigReader.get("validPassword")
        );

        loginPage.waitForPageLoad();

        dashboardPage.addProductToCart(product);

        System.out.println("Product added before cart tests");
    }

    // Verify cart items
    @Test
    public void testCartItems() {

        System.out.println("Navigating to cart");

        cartPage.goToCart();

        boolean status = cartPage.isProductInCart(product);

        Assert.assertTrue(status, "Product not found in cart");

        System.out.println("Product verified in cart");
    }

    // Remove item
    @Test
    public void testRemoveItem() {

        cartPage.goToCart();

        System.out.println("Removing product from cart");

        cartPage.removeItem();

        boolean status = cartPage.waitForCartToBeEmpty();

        Assert.assertTrue(status, "Item was not removed");

        System.out.println("Product removed successfully");
    }

    // Place order
    @Test
    public void testPlaceOrder() {

        cartPage.goToCart();

        System.out.println("Proceeding to checkout");

        cartPage.clickCheckout();

        checkoutPage.selectCountry("india");

        System.out.println("Country selected");

        checkoutPage.placeOrder();

        String message = checkoutPage.getSuccessMessage();

        Assert.assertTrue(
                message.toLowerCase().contains("thankyou"),
                "Order not placed successfully"
        );

        System.out.println("Order placed successfully");
    }
}