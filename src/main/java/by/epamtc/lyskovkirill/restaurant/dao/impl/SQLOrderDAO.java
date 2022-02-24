package by.epamtc.lyskovkirill.restaurant.dao.impl;

import by.epamtc.lyskovkirill.restaurant.bean.Order;
import by.epamtc.lyskovkirill.restaurant.bean.OrderItem;
import by.epamtc.lyskovkirill.restaurant.dao.sql.AbstractSQLDAO;
import by.epamtc.lyskovkirill.restaurant.dao.OrderDAO;
import by.epamtc.lyskovkirill.restaurant.dao.DAOProvider;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;
import by.epamtc.lyskovkirill.restaurant.dao.parameter.ParameterMap;
import by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable.OrderTable;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * SQL {@link OrderDAO} interface implementation.
 *
 * @author k1ly
 */
public class SQLOrderDAO extends AbstractSQLDAO<Order> implements OrderDAO {
    private static final String TABLE_NAME = "orders";

    public SQLOrderDAO() {
        tableName = TABLE_NAME;
    }

    @Override
    public boolean addOrder(Order order) throws DAOException {
        boolean isOrderAdded = false;
        if (order != null) {
            try {
                Integer generatedId = insertEntity(takeFields(order));
                order.setId(generatedId);
                isOrderAdded = true;
            } catch (SQLException exception) {
                throw new DAOException("Order inserting error", exception);
            }
        }
        return isOrderAdded;
    }

    public boolean addOrder(Order order, Connection connection) throws DAOException {
        boolean isOrderAdded = false;
        if (order != null) {
            try {
                Integer generatedId = insertEntity(takeFields(order), connection);
                order.setId(generatedId);
                isOrderAdded = true;
                DAOProvider daoProvider = DAOProvider.getInstance();
                SQLOrderItemDAO orderItemDAO = (SQLOrderItemDAO) daoProvider.getOrderItemRepository();
                for (OrderItem orderItem : order.getOrderItems()) {
                    orderItem.setOrder(order);
                    isOrderAdded &= orderItemDAO.addOrderItem(orderItem, connection);
                }
            } catch (SQLException exception) {
                throw new DAOException("Order inserting error", exception);
            }
        }
        return isOrderAdded;
    }

    @Override
    public List<Order> findOrders(ParameterMap parameters) throws DAOException {
        List<Order> orderList = new ArrayList<>();
        try {
            String sql = makeQuery() + makeQueryCondition(parameters.getParameters(), true);
            for (Object item : executeQuery(Order.class, sql)) {
                orderList.add((Order) item);
            }
        } catch (SQLException exception) {
            throw new DAOException("Order selecting error", exception);
        }
        return orderList;
    }

    @Override
    public List<Order> browseOrders(ParameterMap parameters, boolean includeDeleted,
                                    String orderAttr, boolean asc, int firstRow, int rowCount) throws DAOException {
        List<Order> orderList = new ArrayList<>();
        try {
            String sql = makeQuery() + makeQueryCondition(parameters.getParameters(), includeDeleted)
                    + makeOrderQuery(orderAttr, asc);
            for (Object item : executeQuery(Order.class, makePaginationQuery(sql, firstRow, rowCount))) {
                orderList.add((Order) item);
            }
        } catch (SQLException exception) {
            throw new DAOException("Order selecting error", exception);
        }
        return orderList;
    }

    @Override
    public boolean updateOrder(ParameterMap parameters, Order order) throws DAOException {
        boolean isOrderUpdated = false;
        if (order != null) {
            try {
                isOrderUpdated = updateEntity(takeFields(order), parameters) == 1;
            } catch (SQLException exception) {
                throw new DAOException("Order updating error", exception);
            }
        }
        return isOrderUpdated;
    }

    @Override
    public boolean deleteOrder(ParameterMap deleteId) throws DAOException {
        boolean isOrderDeleted;
        try {
            isOrderDeleted = softDeleteEntity(deleteId) == 1;
        } catch (SQLException exception) {
            throw new DAOException("Order soft deleting error", exception);
        }
        return isOrderDeleted;
    }

    @Override
    public int countOrders(ParameterMap parameters, boolean includeDeleted) throws DAOException {
        try {
            return countTableRows(makeQueryCondition(parameters.getParameters(), includeDeleted));
        } catch (SQLException exception) {
            throw new DAOException("Order counting error", exception);
        }
    }

    @Override
    public ParameterMap takeFields(Order order) {
        Map<String, Object> fields = new LinkedHashMap<>();
        if (order.getOrderDate() != null)
            fields.put(OrderTable.ORDER_DATE.getColumnName(), order.getOrderDate());
        if (order.getRequiredDate() != null)
            fields.put(OrderTable.REQUIRED_DATE.getColumnName(), order.getRequiredDate());
        if (order.getDeliveryDate() != null)
            fields.put(OrderTable.DELIVERY_DATE.getColumnName(), order.getDeliveryDate());
        if (order.getCustomer() != null && order.getCustomer().getId() > 0)
            fields.put(OrderTable.CUSTOMER_ID.getColumnName(), order.getCustomer().getId());
        if (order.getManager() != null && order.getManager().getId() > 0)
            fields.put(OrderTable.MANAGER_ID.getColumnName(), order.getManager().getId());
        if (order.getAddress() != null && order.getAddress().getId() > 0)
            fields.put(OrderTable.ADDRESS_ID.getColumnName(), order.getAddress().getId());
        if (order.getStatus() != null && order.getStatus().getId() > 0)
            fields.put(OrderTable.ORDER_STATUS_ID.getColumnName(), order.getStatus().getId());
        return ParameterMap.of(fields);
    }

    @Override
    public String getColumns() {
        return OrderTable.ID.getColumnName() +
                ", " + OrderTable.ORDER_DATE.getColumnName() +
                ", " + OrderTable.REQUIRED_DATE.getColumnName() +
                ", " + OrderTable.DELIVERY_DATE.getColumnName() +
                ", " + OrderTable.CUSTOMER_ID.getColumnName() +
                ", " + OrderTable.MANAGER_ID.getColumnName() +
                ", " + OrderTable.ADDRESS_ID.getColumnName() +
                ", " + OrderTable.ORDER_STATUS_ID.getColumnName();
    }
}
