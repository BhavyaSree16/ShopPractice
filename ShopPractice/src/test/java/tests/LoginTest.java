package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import utils.ConfigReader;
import utils.ScreenshotUtil;

public class LoginTest extends BaseTest {

    LoginPage loginPage;

    @BeforeMethod
    public void init() {
        System.out.println("Initializing login page");
        loginPage = new LoginPage(getDriver());
    }

    // Valid Login
    @Test
    public void testValidLogin() {

        System.out.println("Starting valid login test");

        loginPage.login(
                ConfigReader.get("validEmail"),
                ConfigReader.get("validPassword")
        );

        System.out.println("Entered valid credentials");

        loginPage.waitForPageLoad();

        boolean status = loginPage.isDashboardLoaded();

        System.out.println("Checking dashboard visibility");

        Assert.assertTrue(
                status,
                "Login failed with valid credentials"
        );

        System.out.println("Valid login test passed");
    }

    // Invalid Password
    @Test
    public void testInvalidPassword() {

        System.out.println("Starting invalid login test");

        loginPage.login(
                ConfigReader.get("validEmail"),
                ConfigReader.get("invalidPassword")
        );

        String error = loginPage.getErrorMessage();

        System.out.println("Captured error message");

        ScreenshotUtil.captureScreenshot(getDriver(), "InvalidLogin");

        System.out.println("Screenshot captured for invalid login");

        Assert.assertTrue(
                error.toLowerCase().contains("incorrect"),
                "Expected error message not displayed"
        );

        System.out.println("Invalid login test passed");
    }

    // Empty Fields
    @Test
    public void testEmptyLogin() {

        System.out.println("Starting empty login test");

        loginPage.login("", "");

        System.out.println("Submitted empty login form");

        ScreenshotUtil.captureScreenshot(getDriver(), "EmptyLogin");

        System.out.println("Screenshot captured for empty login");

        boolean status = loginPage.isLoginButtonStillVisible();

        Assert.assertTrue(
                status,
                "Login should not proceed with empty fields"
        );

        System.out.println("Empty login validation passed");
    }

    // Logout
    @Test
    public void testLogout() {

        System.out.println("Starting logout test");

        loginPage.login(
                ConfigReader.get("validEmail"),
                ConfigReader.get("validPassword")
        );

        loginPage.waitForPageLoad();

        System.out.println("Logged in successfully");

        loginPage.logout();

        System.out.println("Clicked logout button");

        boolean status = getDriver().getCurrentUrl().contains("auth");

        Assert.assertTrue(
                status,
                "Logout failed"
        );

        System.out.println("Logout test passed");
    }
}