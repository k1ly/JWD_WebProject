package by.epamtc.lyskovkirill.restaurant.controller.constant;

/**
 * Enum that is required to store declared URLs of the {@link by.epamtc.lyskovkirill.restaurant.controller.Controller}.
 *
 * @author k1ly
 */
public enum ControllerURL {
    INDEX_URL("/index"),
    WELCOME_URL("/welcome"),
    MENU_URL("/menu"),
    CONTACTS_URL("/contacts"),
    ABOUT_URL("/about"),
    REGISTER_URL("/register"),
    SIGN_IN_URL("/sign_in"),
    RECOVER_URL("/recover"),
    PENALIZED_URL("/penalized"),

    CART_URL("/cart"),
    ORDER_SUCCESS_URL("/order_success"),

    ACCOUNT_URL("/account"),
    ACCOUNT_PROFILE_URL("/account/profile"),
    ACCOUNT_ORDERS_URL("/account/orders"),
    SETTINGS_URL("/settings"),
    MANAGE_ORDERS_URL("/manage/orders"),

    ADMINISTRATION_URL("/administration");


    public static final String CONTEXT_PATH = "/restaurant";
    private final String path;

    ControllerURL(String path) {
        this.path = path;
    }

    public String getPath() {
        return CONTEXT_PATH + path;
    }
}
