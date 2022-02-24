package by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable;

/**
 * Enum that is required to store Order Item database table columns.
 *
 * @author k1ly
 */
public enum OrderItemTable {
    ORDER_ID("order_id"),
    DISH_ID("dish_id"),
    DISH_QUANTITY("dish_quantity"),
    TOTAL_PRICE("total_price");

    private final String columnName;

    OrderItemTable(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
