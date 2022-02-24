package by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable;

/**
 * Enum that is required to store Order Status database table columns.
 *
 * @author k1ly
 */
public enum OrderStatusTable {
    ID("id"),
    STATUS_NAME("status_name");

    private final String columnName;

    OrderStatusTable(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
