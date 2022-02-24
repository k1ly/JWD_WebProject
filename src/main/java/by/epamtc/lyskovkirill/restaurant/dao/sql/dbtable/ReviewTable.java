package by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable;

/**
 * Enum that is required to store Review database table columns.
 *
 * @author k1ly
 */
public enum ReviewTable {
    ID("id"),
    COMMENT("comment"),
    GRADE("grade"),
    DATE("date"),
    USER_ID("user_id");

    private final String columnName;

    ReviewTable(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
