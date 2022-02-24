package by.epamtc.lyskovkirill.restaurant.controller.constant;

/**
 * Enum that is required to store existing paths of jsp pages.
 *
 * @author k1ly
 */
public enum Page {
    WELCOME_PAGE("/jsp/welcome.jsp"),
    MENU_PAGE("/jsp/menu.jsp"),
    CONTACTS_PAGE("/jsp/contacts.jsp"),
    ABOUT_PAGE("/jsp/about.jsp"),
    REGISTER_PAGE("/jsp/register.jsp"),
    SIGN_IN_PAGE("/jsp/signIn.jsp"),
    RECOVER_PAGE("/jsp/recoverPassword.jsp"),
    PENALIZED_PAGE("/jsp/penalized.jsp"),

    CART_PAGE("/jsp/cart.jsp"),
    ORDER_SUCCESS_PAGE("/jsp/orderSuccess.jsp"),

    ACCOUNT_PROFILE_PAGE("/jsp/accountProfile.jsp"),
    ACCOUNT_ORDERS_PAGE("/jsp/accountOrders.jsp"),
    SETTINGS_PAGE("/jsp/settings.jsp"),
    MANAGE_ORDERS_PAGE("/jsp/manageOrders.jsp"),

    ADMINISTRATION_PAGE("/jsp/administration.jsp");

    private final String path;

    Page(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
