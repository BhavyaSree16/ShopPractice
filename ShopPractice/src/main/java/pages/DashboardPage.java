package pages;

import base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class DashboardPage extends BasePage {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    private By products = By.cssSelector(".card-body");
    private By spinner = By.cssSelector(".ngx-spinner-overlay");
    private By cartCount = By.cssSelector("button[routerlink='/dashboard/cart'] label");

    public List<WebElement> getProductList() {
        wait.until(driver -> driver.findElements(products).size() > 0);
        return driver.findElements(products);
    }

    public boolean isProductDisplayed() {
        return getProductList().size() > 0;
    }

    public boolean verifyProductDetails() {

        List<WebElement> list = getProductList();

        for (WebElement product : list) {

            String name = product.findElement(By.cssSelector("b")).getText();
            String price = product.findElement(By.cssSelector("h5")).getText();

            if (name.isEmpty() || price.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public void addProductToCart(String targetProduct) {

        List<WebElement> list = getProductList();

        for (WebElement product : list) {

            String name = product.findElement(By.cssSelector("b")).getText();

            if (name.equalsIgnoreCase(targetProduct)) {

                product.findElement(By.xpath(".//button[text()=' Add To Cart']")).click();

                wait.until(ExpectedConditions.invisibilityOfElementLocated(spinner));

                break;
            }
        }
    }

    public int getCartCount() {

        waitForElement(cartCount);

        String count = driver.findElement(cartCount).getText();

        return Integer.parseInt(count);
    }
}