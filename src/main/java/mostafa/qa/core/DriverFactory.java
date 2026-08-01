package mostafa.qa.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Creates browser-specific WebDriver instances from one central location.
 */
public final class DriverFactory {

    private static final String CHROME = "chrome";
    private static final String EDGE = "edge";
    private static final String FIREFOX = "firefox";

    private DriverFactory() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Builds a WebDriver for the requested browser.
     *
     * @param browserName browser name passed from runtime configuration
     * @param headless true when the browser should run without a visible UI
     * @return configured WebDriver instance
     */
    public static WebDriver createDriver(String browserName, boolean headless) {
        String normalizedBrowser = browserName == null ? CHROME : browserName.trim().toLowerCase();

        return switch (normalizedBrowser) {
            case EDGE -> createEdgeDriver(headless);
            case FIREFOX -> createFirefoxDriver(headless);
            case CHROME -> createChromeDriver(headless);
            default -> throw new IllegalArgumentException("Unsupported browser: " + browserName);
        };
    }

    private static WebDriver createChromeDriver(boolean headless) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");

        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }

        return new ChromeDriver(options);
    }

    private static WebDriver createEdgeDriver(boolean headless) {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--inprivate");
        options.addArguments("--disable-notifications");

        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        }

        return new EdgeDriver(options);
    }

    private static WebDriver createFirefoxDriver(boolean headless) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-private");

        if (headless) {
            options.addArguments("-headless");
        }

        return new FirefoxDriver(options);
    }
}
