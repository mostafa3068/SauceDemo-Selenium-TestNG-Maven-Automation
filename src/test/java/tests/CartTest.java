package tests;

import mostafa.qa.core.BaseTest;
import mostafa.qa.pages.CartPage;
import mostafa.qa.pages.InventoryPage;
import mostafa.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Cart tests covering add and remove product behavior.
 */
public class CartTest extends BaseTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;

    @BeforeMethod(groups = {"smoke", "regression"})
    public void setUpPages() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test(groups = {"smoke"})
    public void addToCart_shouldUpdateBadge() {
        loginPage.login(TestData.STANDARD_USER, TestData.VALID_PASSWORD);
        inventoryPage.addBackpack();
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), TestData.EXPECTED_CART_COUNT_ONE,
                "Cart badge should show 1 after adding backpack");
        inventoryPage.goToCart();
        cartPage.removeBackpack();
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), TestData.EXPECTED_CART_COUNT_ZERO,
                "Cart badge should be hidden after removing backpack");
    }
}
