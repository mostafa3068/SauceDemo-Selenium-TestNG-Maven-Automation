package tests;

import mostafa.qa.core.BaseTest;
import mostafa.qa.pages.CartPage;
import mostafa.qa.pages.CheckoutPage;
import mostafa.qa.pages.InventoryPage;
import mostafa.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * End-to-end checkout test for a valid SauceDemo order.
 */
public class CheckoutTest extends BaseTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod(groups = {"smoke", "regression"})
    public void setUpPages() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @Test(groups = {"smoke"})
    public void validCheckout_shouldShowConfirmation() {
        loginPage.login(TestData.STANDARD_USER, TestData.VALID_PASSWORD);
        inventoryPage.addBackpack();
        inventoryPage.goToCart();
        cartPage.proceedToCheckout();
        checkoutPage.fillInfo(TestData.CHECKOUT_FIRST_NAME, TestData.CHECKOUT_LAST_NAME, TestData.CHECKOUT_POSTAL_CODE);
        checkoutPage.continueCheckout();
        checkoutPage.finishOrder();

        Assert.assertTrue(checkoutPage.getConfirmationMessage().toUpperCase().contains("THANK YOU"),
                "Order confirmation should display THANK YOU");
    }
}
