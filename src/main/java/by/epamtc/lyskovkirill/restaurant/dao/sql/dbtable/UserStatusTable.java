package by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable;

/**
 * Enum that is required to store User Status database table columns.
 *
 * @author k1ly
 */
public enum UserStatusTable {
    ID("id"),
    STATUS_NAME("status_name");

    private final String columnName;

    UserStatusTable(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
