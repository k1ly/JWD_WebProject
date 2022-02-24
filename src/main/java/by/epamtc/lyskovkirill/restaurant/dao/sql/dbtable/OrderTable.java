package by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable;

/**
 * Enum that is required to store Order database table columns.
 *
 * @author k1ly
 */
public enum OrderTable {
    ID("id"),
    ORDER_DATE("order_date"),
    REQUIRED_DATE("required_date"),
    DELIVERY_DATE("delivery_date"),
    CUSTOMER_ID("customer_id"),
    MANAGER_ID("manager_id"),
    ADDRESS_ID("address_id"),
    ORDER_STATUS_ID("order_status_id");

    private final String columnName;

    OrderTable(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
