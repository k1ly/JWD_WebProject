package by.epamtc.lyskovkirill.restaurant.dao.impl;

import by.epamtc.lyskovkirill.restaurant.bean.OrderItem;
import by.epamtc.lyskovkirill.restaurant.dao.sql.AbstractSQLDAO;
import by.epamtc.lyskovkirill.restaurant.dao.OrderItemDAO;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;
import by.epamtc.lyskovkirill.restaurant.dao.parameter.ParameterMap;
import by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable.OrderItemTable;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * SQL {@link OrderItemDAO} interface implementation.
 *
 * @author k1ly
 */
public class SQLOrderItemDAO extends AbstractSQLDAO<OrderItem> implements OrderItemDAO {
    private static final String TABLE_NAME = "order_items";

    public SQLOrderItemDAO() {
        tableName = TABLE_NAME;
    }

    @Override
    public boolean addOrderItem(OrderItem orderItem) throws DAOException {
        boolean isOrderItemAdded = false;
        if (orderItem != null) {
            try {
                insertEntity(takeFields(orderItem));
                isOrderItemAdded = true;
            } catch (SQLException exception) {
                throw new DAOException("Order item inserting error", exception);
            }
        }
        return isOrderItemAdded;
    }

    public boolean addOrderItem(OrderItem orderItem, Connection connection) throws DAOException {
        boolean isOrderItemAdded = false;
        if (orderItem != null) {
            try {
                insertEntity(takeFields(orderItem), connection);
                isOrderItemAdded = true;
            } catch (SQLException exception) {
                throw new DAOException("Order item inserting error", exception);
            }
        }
        return isOrderItemAdded;
    }

    @Override
    public List<OrderItem> findOrderItems(ParameterMap parameters) throws DAOException {
        List<OrderItem> orderItemList = new ArrayList<>();
        try {
            String sql = makeQuery() + makeQueryCondition(parameters.getParameters(), true);
            for (Object item : executeQuery(OrderItem.class, sql)) {
                orderItemList.add((OrderItem) item);
            }
        } catch (SQLException exception) {
            throw new DAOException("Order item selecting error", exception);
        }
        return orderItemList;
    }

    @Override
    public boolean updateOrderItem(ParameterMap parameters, OrderItem orderItem) throws DAOException {
        boolean isOrderItemUpdated = false;
        if (orderItem != null) {
            try {
                isOrderItemUpdated = updateEntity(takeFields(orderItem), parameters) == 1;
            } catch (SQLException exception) {
                throw new DAOException("Order item updating error", exception);
            }
        }
        return isOrderItemUpdated;
    }

    @Override
    public boolean deleteOrderItem(ParameterMap deleteId) throws DAOException {
        boolean isOrderItemDeleted;
        try {
            isOrderItemDeleted = softDeleteEntity(deleteId) == 1;
        } catch (SQLException exception) {
            throw new DAOException("Order item soft deleting error", exception);
        }
        return isOrderItemDeleted;
    }

    @Override
    public Optional<OrderItem> restoreOrderItem(ParameterMap restoreId) throws DAOException {
        Optional<OrderItem> orderItem;
        try {
            List<OrderItem> orderItems = findOrderItems(restoreId);
            orderItem = orderItems.size() == 1 ? Optional.of(orderItems.get(0)) : Optional.empty();
            if (restoreEntity(restoreId) != orderItems.size())
                throw new DAOException("Order item restoring error");
        } catch (SQLException exception) {
            throw new DAOException("Order item restoring error", exception);
        }
        return orderItem;
    }

    @Override
    public ParameterMap takeFields(OrderItem orderItem) {
        Map<String, Object> fields = new LinkedHashMap<>();
        if (orderItem.getOrder() != null && orderItem.getOrder().getId() > 0)
            fields.put(OrderItemTable.ORDER_ID.getColumnName(), orderItem.getOrder().getId());
        if (orderItem.getDish() != null && orderItem.getDish().getId() > 0)
            fields.put(OrderItemTable.DISH_ID.getColumnName(), orderItem.getDish().getId());
        if (orderItem.getQuantity() != null)
            fields.put(OrderItemTable.DISH_QUANTITY.getColumnName(), orderItem.getQuantity());
        if (orderItem.getTotalPrice() != null)
            fields.put(OrderItemTable.TOTAL_PRICE.getColumnName(), orderItem.getTotalPrice());
        return ParameterMap.of(fields);
    }

    @Override
    public String getColumns() {
        return OrderItemTable.ORDER_ID.getColumnName()
                + ", " + OrderItemTable.DISH_ID.getColumnName()
                + ", " + OrderItemTable.DISH_QUANTITY.getColumnName()
                + ", " + OrderItemTable.TOTAL_PRICE.getColumnName();
    }
}
