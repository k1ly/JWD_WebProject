package by.epamtc.lyskovkirill.restaurant.dao.sql.builder.impl;

import by.epamtc.lyskovkirill.restaurant.bean.Dish;
import by.epamtc.lyskovkirill.restaurant.bean.Order;
import by.epamtc.lyskovkirill.restaurant.bean.OrderItem;
import by.epamtc.lyskovkirill.restaurant.dao.sql.builder.Builder;
import by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable.OrderItemTable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * {@link Builder} implementation for {@link OrderItem} class.
 *
 * @author k1ly
 */
public class OrderItemBuilder implements Builder<OrderItem> {

    @Override
    public OrderItem build(ResultSet resultSet) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(new Order(resultSet.getInt(OrderItemTable.ORDER_ID.getColumnName())));
        orderItem.setDish(new Dish(resultSet.getInt(OrderItemTable.DISH_ID.getColumnName())));
        orderItem.setQuantity(resultSet.getInt(OrderItemTable.DISH_QUANTITY.getColumnName()));
        orderItem.setTotalPrice(resultSet.getDouble(OrderItemTable.TOTAL_PRICE.getColumnName()));
        return orderItem;
    }
}
