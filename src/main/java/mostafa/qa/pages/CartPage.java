package mostafa.qa.pages;

import mostafa.qa.core.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page object for the SauceDemo cart page.
 */
public class CartPage extends PageBase {

    @FindBy(id = "remove-sauce-labs-backpack")
    private WebElement removeBackpackButton;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void removeBackpack() {
        removeBackpackButton.click();
    }

    public void proceedToCheckout() {
        checkoutButton.click();
    }
}
