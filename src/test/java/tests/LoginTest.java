package tests;

import mostafa.qa.core.BaseTest;
import mostafa.qa.pages.InventoryPage;
import mostafa.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Login tests for valid and blocked SauceDemo users.
 */
public class LoginTest extends BaseTest {

    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeMethod(groups = {"smoke", "regression"})
    public void setUpPages() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
    }

    @Test(groups = {"smoke", "regression"})
    public void validLogin_shouldGoToInventory() {
        loginPage.login(TestData.STANDARD_USER, TestData.VALID_PASSWORD);
        Assert.assertTrue(inventoryPage.isLoaded(), "Inventory page should load after valid login");
    }

    @Test(groups = {"regression"})
    public void lockedOutUser_shouldSeeError() {
        loginPage.login(TestData.LOCKED_OUT_USER, TestData.VALID_PASSWORD);
        Assert.assertTrue(loginPage.getErrorMessage().toLowerCase().contains("sorry"),
                "Locked out user should see an error message");
    }
}
