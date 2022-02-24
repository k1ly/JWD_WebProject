package by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable;

/**
 * Enum that is required to store Dish Category database table columns.
 *
 * @author k1ly
 */
public enum DishCategoryTable {
    ID("id"),
    CATEGORY_NAME("category_name");

    private final String columnName;

    DishCategoryTable(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
