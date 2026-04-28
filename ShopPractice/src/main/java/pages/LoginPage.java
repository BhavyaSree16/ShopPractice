package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private By email = By.id("userEmail");
    private By password = By.id("userPassword");
    private By loginBtn = By.id("login");
    private By errorToast = By.cssSelector(".toast-error");
    private By logoutBtn = By.xpath("//button[text()=' Sign Out ']");

    private By productCards = By.cssSelector(".card-body");
    private By spinner = By.cssSelector(".ngx-spinner-overlay");

    // Login Action
    public void login(String userEmail, String userPassword) {
        type(email, userEmail);
        type(password, userPassword);
        click(loginBtn);
    }

    // Invalid login error
    public String getErrorMessage() {
        return getText(errorToast);
    }

    // Wait for dashboard
    public void waitForPageLoad() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
    }

    // Validate dashboard
    public boolean isDashboardLoaded() {
        try {
            waitForElement(productCards);
            return driver.findElements(productCards).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    // Empty login validation
    public boolean isLoginButtonStillVisible() {
        return driver.findElement(loginBtn).isDisplayed();
    }

    // Logout
    public void logout() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
        click(logoutBtn);
    }
}