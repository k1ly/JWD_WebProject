package by.epamtc.lyskovkirill.restaurant.dao.sql.builder.impl;

import by.epamtc.lyskovkirill.restaurant.bean.DishCategory;
import by.epamtc.lyskovkirill.restaurant.dao.sql.builder.Builder;
import by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable.DishCategoryTable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * {@link Builder} implementation for {@link DishCategory} class.
 *
 * @author k1ly
 */
public class DishCategoryBuilder implements Builder<DishCategory> {

    @Override
    public DishCategory build(ResultSet resultSet) throws SQLException {
        DishCategory dishCategory = new DishCategory();
        dishCategory.setId(resultSet.getInt(DishCategoryTable.ID.getColumnName()));
        dishCategory.setName(resultSet.getString(DishCategoryTable.CATEGORY_NAME.getColumnName()));
        return dishCategory;
    }
}
