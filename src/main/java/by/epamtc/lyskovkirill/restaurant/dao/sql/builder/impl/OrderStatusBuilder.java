package by.epamtc.lyskovkirill.restaurant.dao.sql.builder.impl;

import by.epamtc.lyskovkirill.restaurant.bean.OrderStatus;
import by.epamtc.lyskovkirill.restaurant.dao.sql.builder.Builder;
import by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable.OrderStatusTable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * {@link Builder} implementation for {@link OrderStatus} class.
 *
 * @author k1ly
 */
public class OrderStatusBuilder implements Builder<OrderStatus> {

    @Override
    public OrderStatus build(ResultSet resultSet) throws SQLException {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(resultSet.getInt(OrderStatusTable.ID.getColumnName()));
        orderStatus.setName(resultSet.getString(OrderStatusTable.STATUS_NAME.getColumnName()));
        return orderStatus;
    }
}
