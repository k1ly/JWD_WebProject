package by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable;

/**
 * Enum that is required to store Dish database table columns.
 *
 * @author k1ly
 */
public enum DishTable {
    ID("id"),
    NAME("name"),
    COMPOSITION("composition"),
    IMAGE("image"),
    WEIGHT("weight"),
    PRICE("price"),
    DISCOUNT("discount"),
    DISH_CATEGORY_ID("dish_category_id");

    private final String columnName;

    DishTable(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
