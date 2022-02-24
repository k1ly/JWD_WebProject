package by.epamtc.lyskovkirill.restaurant.command;

/**
 * Enum that is required to store command names.
 *
 * @author k1ly
 */
public enum CommandName {
    /*
     * Page redirection
     */
    GO_TO_WELCOME,
    GO_TO_MENU,
    GO_TO_CONTACTS,
    GO_TO_ABOUT,
    GO_TO_REGISTER,
    GO_TO_SIGN_IN,
    GO_TO_RECOVER,
    GO_TO_PENALIZED,

    /*
     * User pages
     */
    GO_TO_CART,
    GO_TO_ORDER_SUCCESS,
    GO_TO_ACCOUNT,
    GO_TO_ACCOUNT_PROFILE,
    GO_TO_ACCOUNT_ORDERS,
    GO_TO_SETTINGS,
    GO_TO_MANAGE_ORDERS,

    /*
     * Service pages
     */
    REGISTER_USER,
    SIGN_IN,
    SIGN_OUT,
    EDIT_SETTINGS,
    RECOVER_PASSWORD,

    /*
     * User actions
     */
    UPDATE_CART_ITEM,
    CONFIRM_ORDER,
    CANCEL_ORDER,
    ADD_REVIEW,
    GET_REVIEWS,
    MANAGE_ORDER,

    /*
     * Admin actions
     */
    GO_TO_ADMINISTRATION,
    FIND_DISH,
    ADD_DISH,
    EDIT_DISH,
    DELETE_DISH,
    FIND_CATEGORY,
    ADD_CATEGORY,
    EDIT_CATEGORY,
    DELETE_CATEGORY,
    FIND_USER,
    EDIT_USER
}
