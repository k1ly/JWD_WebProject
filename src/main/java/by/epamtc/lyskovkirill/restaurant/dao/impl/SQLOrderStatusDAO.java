package by.epamtc.lyskovkirill.restaurant.dao.impl;

import by.epamtc.lyskovkirill.restaurant.bean.OrderStatus;
import by.epamtc.lyskovkirill.restaurant.dao.sql.AbstractSQLDAO;
import by.epamtc.lyskovkirill.restaurant.dao.OrderStatusDAO;
import by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable.OrderStatusTable;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;
import by.epamtc.lyskovkirill.restaurant.dao.parameter.ParameterMap;

import java.sql.SQLException;
import java.util.*;

/**
 * SQL {@link OrderStatusDAO} interface implementation.
 *
 * @author k1ly
 */
public class SQLOrderStatusDAO extends AbstractSQLDAO<OrderStatus> implements OrderStatusDAO {
    private static final String TABLE_NAME = "order_statuses";

    public SQLOrderStatusDAO() {
        tableName = TABLE_NAME;
    }

    @Override
    public boolean addOrderStatus(OrderStatus orderStatus) throws DAOException {
        boolean isOrderStatusAdded = false;
        if (orderStatus != null) {
            try {
                Integer generatedId = insertEntity(takeFields(orderStatus));
                orderStatus.setId(generatedId);
                isOrderStatusAdded = true;
            } catch (SQLException exception) {
                throw new DAOException("Order status inserting error", exception);
            }
        }
        return isOrderStatusAdded;
    }

    @Override
    public List<OrderStatus> findOrderStatuses(ParameterMap parameters) throws DAOException {
        List<OrderStatus> orderStatusList = new ArrayList<>();
        try {
            String sql = makeQuery() + makeQueryCondition(parameters.getParameters(), true);
            for (Object item : executeQuery(OrderStatus.class, sql)) {
                orderStatusList.add((OrderStatus) item);
            }
        } catch (SQLException exception) {
            throw new DAOException("Order status selecting error", exception);
        }
        return orderStatusList;
    }

    @Override
    public List<OrderStatus> browseOrderStatuses(boolean includeDeleted, String orderAttr, boolean asc) throws DAOException {
        List<OrderStatus> orderStatusList = new ArrayList<>();
        try {
            String sql = makeQuery() + makeQueryCondition(includeDeleted)
                    + makeOrderQuery(orderAttr, asc);
            for (Object item : executeQuery(OrderStatus.class, sql)) {
                orderStatusList.add((OrderStatus) item);
            }
        } catch (SQLException exception) {
            throw new DAOException("Order status selecting error", exception);
        }
        return orderStatusList;
    }

    @Override
    public ParameterMap takeFields(OrderStatus orderStatus) {
        Map<String, Object> fields = new LinkedHashMap<>();
        if (orderStatus.getName() != null)
            fields.put(OrderStatusTable.STATUS_NAME.getColumnName(), orderStatus.getName());
        return ParameterMap.of(fields);
    }

    @Override
    public String getColumns() {
        return OrderStatusTable.ID.getColumnName() +
                ", " + OrderStatusTable.STATUS_NAME.getColumnName();
    }
}
