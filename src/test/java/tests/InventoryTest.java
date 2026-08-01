package tests;

import mostafa.qa.core.BaseTest;
import mostafa.qa.pages.InventoryPage;
import mostafa.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Inventory page tests for product listing, cart badge, and sorting behavior.
 */
public class InventoryTest extends BaseTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeMethod(groups = {"smoke", "regression"})
    public void setUpPages() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
    }

    @Test(groups = {"smoke"})
    public void inventory_shouldLoadAfterValidLogin() {
        loginPage.login(TestData.STANDARD_USER, TestData.VALID_PASSWORD);
        Assert.assertTrue(inventoryPage.isLoaded(), "Inventory page should be loaded after login");
    }

    @Test(groups = {"smoke", "regression"})
    public void addBackpack_shouldUpdateCartBadgeToOne() {
        loginPage.login(TestData.STANDARD_USER, TestData.VALID_PASSWORD);
        inventoryPage.addBackpack();
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), TestData.EXPECTED_CART_COUNT_ONE,
                "Cart badge should show 1 after adding backpack");
    }

    @Test(groups = {"regression"})
    public void sort_lowToHigh_shouldOrderPricesAscending() {
        loginPage.login(TestData.STANDARD_USER, TestData.VALID_PASSWORD);
        inventoryPage.sortBy(TestData.SORT_PRICE_LOW_TO_HIGH);
        Assert.assertTrue(inventoryPage.arePricesSortedAscending(),
                "Prices should be sorted ascending after applying Low to High");
    }
}
