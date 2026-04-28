package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigReader;

import java.time.Duration;

public class BaseTest {

    // Thread-safe driver (best practice)
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Getter for driver (used everywhere)
    public static WebDriver getDriver() {
        return driver.get();
    }

    @BeforeMethod
    public void setup() {

        String browser = ConfigReader.get("browser");
        String url = ConfigReader.get("baseUrl");
        int timeout = Integer.parseInt(ConfigReader.get("timeout"));

        System.out.println("Launching browser");
        System.out.println("Browser: " + browser);

        if (browser.equalsIgnoreCase("chrome")) {

            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver());

        } else if (browser.equalsIgnoreCase("firefox")) {

            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver());
        }

        // Maximize and waits
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        // Open URL
        getDriver().get(url);

        System.out.println("Application launched: " + url);
    }

    @AfterMethod
    public void teardown() {

        System.out.println("Closing browser");

        if (getDriver() != null) {
            getDriver().quit();
            driver.remove(); // important for parallel execution
        }
    }
}