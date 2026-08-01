package mostafa.qa.core;

import mostafa.qa.utils.ExtentManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Base class for all TestNG tests.
 * It owns the WebDriver lifecycle and reads runtime configuration from Maven system properties.
 */
public class BaseTest {

    private static final String DEFAULT_BROWSER = "chrome";
    private static final String DEFAULT_BASE_URL = "https://www.saucedemo.com/";
    private static final String BROWSER_PROPERTY = "browser.name";
    private static final String BASE_URL_PROPERTY = "app.baseUrl";
    private static final String HEADLESS_PROPERTY = "browser.headless";
    private static final AtomicBoolean IS_BROWSER_INFO_SET = new AtomicBoolean(false);

    protected WebDriver driver;
    protected String browserName;

    public WebDriver getDriver() {
        return driver;
    }

    public String getBrowserName() {
        return browserName;
    }

    /**
     * Creates a fresh browser before each test method to keep tests isolated.
     */
    @BeforeMethod(alwaysRun = true)
    public void setUpClass() {
        String configuredBrowser = System.getProperty(BROWSER_PROPERTY, DEFAULT_BROWSER);
        String baseUrl = System.getProperty(BASE_URL_PROPERTY, DEFAULT_BASE_URL);
        boolean headless = Boolean.parseBoolean(System.getProperty(HEADLESS_PROPERTY, "false"));

        driver = DriverFactory.createDriver(configuredBrowser, headless);
        driver.manage().window().maximize();
        driver.get(baseUrl);

        Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
        browserName = capabilities.getBrowserName();

        if (IS_BROWSER_INFO_SET.compareAndSet(false, true)) {
            ExtentManager.setBrowserInfo(browserName);
        }
    }

    /**
     * Closes the browser after each test method to prevent resource leaks.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDownClass() {
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                driver = null;
            }
        }
    }
}
