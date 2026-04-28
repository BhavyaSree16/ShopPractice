package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutPage extends BasePage {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    private By countryInput = By.cssSelector("input[placeholder='Select Country']");
    private By countryOption = By.cssSelector(".ta-item");
    private By placeOrderBtn = By.cssSelector(".action__submit");
    private By successMsg = By.cssSelector(".hero-primary");
    private By spinner = By.cssSelector(".ngx-spinner-overlay");

    // Select country
    public void selectCountry(String country) {

        type(countryInput, country);

        wait.until(ExpectedConditions.visibilityOfElementLocated(countryOption));

        click(countryOption);

        // wait dropdown disappear
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector(".ta-results")));
    }

    // Place order
    public void placeOrder() {

        wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));

        wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn));

        click(placeOrderBtn);
    }

    // Success message
    public String getSuccessMessage() {
        waitForElement(successMsg);
        return driver.findElement(successMsg).getText();
    }
}