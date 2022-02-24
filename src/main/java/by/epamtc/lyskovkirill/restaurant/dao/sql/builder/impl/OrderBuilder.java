package by.epamtc.lyskovkirill.restaurant.dao.sql.builder.impl;

import by.epamtc.lyskovkirill.restaurant.bean.contact.Address;
import by.epamtc.lyskovkirill.restaurant.bean.Order;
import by.epamtc.lyskovkirill.restaurant.bean.OrderStatus;
import by.epamtc.lyskovkirill.restaurant.bean.User;
import by.epamtc.lyskovkirill.restaurant.dao.sql.builder.Builder;
import by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable.OrderTable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * {@link Builder} implementation for {@link Order} class.
 *
 * @author k1ly
 */
public class OrderBuilder implements Builder<Order> {

    @Override
    public Order build(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt(OrderTable.ID.getColumnName()));
        order.setOrderDate(resultSet.getTimestamp(OrderTable.ORDER_DATE.getColumnName()));
        order.setRequiredDate(resultSet.getTimestamp(OrderTable.REQUIRED_DATE.getColumnName()));
        order.setDeliveryDate(resultSet.getTimestamp(OrderTable.DELIVERY_DATE.getColumnName()));
        order.setCustomer(new User(resultSet.getInt(OrderTable.CUSTOMER_ID.getColumnName())));
        order.setManager(new User(resultSet.getInt(OrderTable.MANAGER_ID.getColumnName())));
        order.setAddress(new Address(resultSet.getInt(OrderTable.ADDRESS_ID.getColumnName())));
        order.setStatus(new OrderStatus(resultSet.getInt(OrderTable.ORDER_STATUS_ID.getColumnName())));
        return order;
    }
}
