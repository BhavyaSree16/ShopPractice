package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    private By cartButton = By.cssSelector("button[routerlink='/dashboard/cart']");
    private By cartItems = By.cssSelector(".cartSection h3");
    private By removeBtn = By.cssSelector(".cartSection button.btn-danger");
    private By checkoutBtn = By.cssSelector(".totalRow button");
    private By spinner = By.cssSelector(".ngx-spinner-overlay");

    // Navigate to cart (FIXED PROPERLY)
    public void goToCart() {

        // wait before clicking
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));

        wait.until(ExpectedConditions.elementToBeClickable(cartButton));

        click(cartButton);

        // wait after navigation
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
    }

    // Get cart items
    public List<WebElement> getCartItems() {
        return driver.findElements(cartItems);
    }

    // Verify product exists
    public boolean isProductInCart(String productName) {
        for (WebElement item : getCartItems()) {
            if (item.getText().equalsIgnoreCase(productName)) {
                return true;
            }
        }
        return false;
    }

    // Remove item
    public void removeItem() {
        click(removeBtn);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));
    }

    // Wait for cart empty
    public boolean waitForCartToBeEmpty() {
        try {
            wait.until(driver -> getCartItems().size() == 0);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Checkout
    public void clickCheckout() {

        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));

        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn));

        click(checkoutBtn);
    }
}