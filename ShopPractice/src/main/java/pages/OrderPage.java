package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class OrderPage extends BasePage {

    public OrderPage(WebDriver driver) {
        super(driver);
    }

    private By ordersButton = By.cssSelector("button[routerlink='/dashboard/myorders']");
    private By orderRows = By.cssSelector("tbody tr");
    private By viewButtons = By.cssSelector("button.btn-primary");
    private By orderProduct = By.cssSelector(".col-text");
    private By spinner = By.cssSelector(".ngx-spinner-overlay");

    // Navigate to Orders page
    public void goToOrders() {

        // wait for spinner
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));

        WebElement orders = wait.until(
                ExpectedConditions.visibilityOfElementLocated(ordersButton)
        );

        // scroll
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);", orders
        );

        // wait clickable
        wait.until(ExpectedConditions.elementToBeClickable(orders));

        // JS click (avoids interception)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", orders
        );

        // wait after navigation
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
    }

    // Get orders list
    public List<WebElement> getOrders() {
        return driver.findElements(orderRows);
    }

    // Check if orders exist
    public boolean isOrderPresent() {
        return getOrders().size() > 0;
    }

    // Open first order
    public void openFirstOrder() {

        List<WebElement> buttons = driver.findElements(viewButtons);

        if (!buttons.isEmpty()) {

            WebElement btn = buttons.get(0);

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView(true);", btn
            );

            wait.until(ExpectedConditions.elementToBeClickable(btn));

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();", btn
            );
        }
    }

    // Get product name
    public String getOrderProductName() {

        waitForElement(orderProduct);

        return driver.findElement(orderProduct).getText();
    }
}