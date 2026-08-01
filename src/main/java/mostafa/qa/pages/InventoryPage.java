package mostafa.qa.pages;

import mostafa.qa.core.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Page object for the SauceDemo inventory page.
 */
public class InventoryPage extends PageBase {

    @FindBy(css = ".title")
    private WebElement pageTitle;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addBackpackButton;

    @FindBy(css = ".shopping_cart_link")
    private WebElement cartLink;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(css = ".product_sort_container")
    private WebElement sortDropdown;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return pageTitle.isDisplayed();
    }

    public void addBackpack() {
        addBackpackButton.click();
    }

    public String getCartBadgeCount() {
        try {
            return cartBadge.getText();
        } catch (RuntimeException e) {
            return "0";
        }
    }

    public void goToCart() {
        cartLink.click();
    }

    /**
     * Sorts products by the native SauceDemo select value.
     * Values include az, za, lohi, and hilo.
     */
    public void sortBy(String value) {
        new Select(sortDropdown).selectByValue(value);
    }

    public boolean arePricesSortedAscending() {
        List<WebElement> prices = driver.findElements(By.cssSelector(".inventory_item_price"));

        double previousPrice = -Double.MAX_VALUE;
        for (WebElement priceElement : prices) {
            double currentPrice = Double.parseDouble(priceElement.getText().replace("$", "").trim());
            if (currentPrice < previousPrice) {
                return false;
            }
            previousPrice = currentPrice;
        }
        return true;
    }
}
