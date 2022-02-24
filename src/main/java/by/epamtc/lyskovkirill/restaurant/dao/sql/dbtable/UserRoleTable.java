package by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable;

/**
 * Enum that is required to store User Role database table columns.
 *
 * @author k1ly
 */
public enum UserRoleTable {
    ID("id"),
    ROLE_NAME("role_name");

    private final String columnName;

    UserRoleTable(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
