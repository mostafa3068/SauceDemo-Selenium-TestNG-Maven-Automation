package tests;

/**
 * Centralized test data used by SauceDemo UI tests.
 */
final class TestData {

    static final String STANDARD_USER = "standard_user";
    static final String LOCKED_OUT_USER = "locked_out_user";
    static final String VALID_PASSWORD = "secret_sauce";
    static final String SORT_PRICE_LOW_TO_HIGH = "lohi";
    static final String EXPECTED_CART_COUNT_ONE = "1";
    static final String EXPECTED_CART_COUNT_ZERO = "0";
    static final String CHECKOUT_FIRST_NAME = "Mostafa";
    static final String CHECKOUT_LAST_NAME = "QA";
    static final String CHECKOUT_POSTAL_CODE = "12345";

    private TestData() {
        throw new IllegalStateException("Utility class");
    }
}
