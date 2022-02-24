package by.epamtc.lyskovkirill.restaurant.service.impl;

import by.epamtc.lyskovkirill.restaurant.bean.OrderStatus;
import by.epamtc.lyskovkirill.restaurant.dao.OrderStatusDAO;
import by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable.OrderStatusTable;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;
import by.epamtc.lyskovkirill.restaurant.dao.DAOProvider;
import by.epamtc.lyskovkirill.restaurant.dao.parameter.ParameterMap;
import by.epamtc.lyskovkirill.restaurant.service.OrderStatusService;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * {@link OrderStatusService} interface implementation.
 *
 * @author k1ly
 */
public class OrderStatusServiceImpl implements OrderStatusService {

    @Override
    public boolean addOrderStatus(OrderStatus orderStatus) throws ServiceException {
        boolean isOrderStatusAdded;
        DAOProvider daoProvider = DAOProvider.getInstance();
        OrderStatusDAO orderStatusDAO = daoProvider.getOrderStatusRepository();
        try {
            isOrderStatusAdded = orderStatusDAO.addOrderStatus(orderStatus);
        } catch (DAOException e) {
            throw new ServiceException("Order status adding error", e);
        }
        return isOrderStatusAdded;
    }

    @Override
    public List<OrderStatus> browseOrderStatuses() throws ServiceException {
        List<OrderStatus> orderStatusList;
        DAOProvider daoProvider = DAOProvider.getInstance();
        OrderStatusDAO orderStatusDAO = daoProvider.getOrderStatusRepository();
        try {
            orderStatusList = orderStatusDAO.browseOrderStatuses(false,
                    OrderStatusTable.ID.getColumnName(), true);
        } catch (DAOException e) {
            throw new ServiceException("Obtaining order statuses error", e);
        }
        return orderStatusList;
    }

    @Override
    public Optional<OrderStatus> findOrderStatusById(int orderStatusId) throws ServiceException {
        Optional<OrderStatus> orderStatus;
        DAOProvider daoProvider = DAOProvider.getInstance();
        OrderStatusDAO orderStatusDAO = daoProvider.getOrderStatusRepository();
        try {
            Map<String, Object> orderStatusIdParameter = new LinkedHashMap<>();
            orderStatusIdParameter.put(OrderStatusTable.ID.getColumnName(), orderStatusId);
            List<OrderStatus> orderStatusList = orderStatusDAO.findOrderStatuses(ParameterMap.of(orderStatusIdParameter));
            orderStatus = orderStatusList.size() == 1 ? Optional.of(orderStatusList.get(0)) : Optional.empty();
        } catch (DAOException e) {
            throw new ServiceException("Order status searching error", e);
        }
        return orderStatus;
    }

    @Override
    public Optional<OrderStatus> findOrderStatusByName(String orderStatusName) throws ServiceException {
        Optional<OrderStatus> orderStatus;
        DAOProvider daoProvider = DAOProvider.getInstance();
        OrderStatusDAO orderStatusDAO = daoProvider.getOrderStatusRepository();
        try {
            Map<String, Object> orderStatusNameParameter = new LinkedHashMap<>();
            orderStatusNameParameter.put(OrderStatusTable.STATUS_NAME.getColumnName(), orderStatusName);
            List<OrderStatus> orderStatusList = orderStatusDAO.findOrderStatuses(ParameterMap.of(orderStatusNameParameter));
            orderStatus = orderStatusList.size() == 1 ? Optional.of(orderStatusList.get(0)) : Optional.empty();
        } catch (DAOException e) {
            throw new ServiceException("Order status searching error", e);
        }
        return orderStatus;
    }
}
