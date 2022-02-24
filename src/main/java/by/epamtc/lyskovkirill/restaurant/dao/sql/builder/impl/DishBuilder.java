package by.epamtc.lyskovkirill.restaurant.dao.sql.builder.impl;

import by.epamtc.lyskovkirill.restaurant.bean.Dish;
import by.epamtc.lyskovkirill.restaurant.bean.DishCategory;
import by.epamtc.lyskovkirill.restaurant.dao.sql.builder.Builder;
import by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable.DishTable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * {@link Builder} implementation for {@link Dish} class.
 *
 * @author k1ly
 */
public class DishBuilder implements Builder<Dish> {

    @Override
    public Dish build(ResultSet resultSet) throws SQLException {
        Dish dish = new Dish();
        dish.setId(resultSet.getInt(DishTable.ID.getColumnName()));
        dish.setName(resultSet.getString(DishTable.NAME.getColumnName()));
        dish.setComposition(resultSet.getString(DishTable.COMPOSITION.getColumnName()));
        dish.setImage(resultSet.getString(DishTable.IMAGE.getColumnName()));
        dish.setWeight(resultSet.getInt(DishTable.WEIGHT.getColumnName()));
        dish.setPrice(resultSet.getDouble(DishTable.PRICE.getColumnName()));
        dish.setDiscount(resultSet.getInt(DishTable.DISCOUNT.getColumnName()));
        dish.setCategory(new DishCategory(resultSet.getInt(DishTable.DISH_CATEGORY_ID.getColumnName())));
        return dish;
    }
}
