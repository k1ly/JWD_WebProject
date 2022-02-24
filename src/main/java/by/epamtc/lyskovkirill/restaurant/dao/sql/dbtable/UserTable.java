package by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable;

/**
 * Enum that is required to store User database table columns.
 *
 * @author k1ly
 */
public enum UserTable {
    ID("id"),
    LOGIN("login"),
    PASSWORD("password"),
    NAME("name"),
    EMAIL("email"),
    PHONE("phone"),
    CART_ID("cart_id"),
    USER_ROLE_ID("user_role_id"),
    USER_STATUS_ID("user_status_id");

    private final String columnName;

    UserTable(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
