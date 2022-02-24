package by.epamtc.lyskovkirill.restaurant.service.impl;

import by.epamtc.lyskovkirill.restaurant.bean.*;
import by.epamtc.lyskovkirill.restaurant.dao.OrderDAO;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;
import by.epamtc.lyskovkirill.restaurant.dao.DAOProvider;
import by.epamtc.lyskovkirill.restaurant.dao.parameter.ParameterMap;
import by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable.OrderTable;
import by.epamtc.lyskovkirill.restaurant.service.OrderItemService;
import by.epamtc.lyskovkirill.restaurant.service.OrderService;
import by.epamtc.lyskovkirill.restaurant.service.OrderStatusService;
import by.epamtc.lyskovkirill.restaurant.service.UserService;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;
import by.epamtc.lyskovkirill.restaurant.service.ServiceProvider;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

/**
 * {@link OrderService} interface implementation.
 *
 * @author k1ly
 */
public class OrderServiceImpl implements OrderService {

    @Override
    public boolean addOrder(Order order) throws ServiceException {
        boolean isOrderCreated = false;
        DAOProvider daoProvider = DAOProvider.getInstance();
        OrderDAO orderDAO = daoProvider.getOrderRepository();
        try {
            ServiceProvider serviceProvider = ServiceProvider.getInstance();
            OrderStatusService orderStatusService = serviceProvider.getOrderStatusService();
            Optional<OrderStatus> orderStatus = orderStatusService.findOrderStatusByName(OrderStatus.CREATED);
            if (orderStatus.isPresent()) {
                order.setStatus(orderStatus.get());
                if (orderDAO.addOrder(order)) {
                    UserService userService = serviceProvider.getUserService();
                    User newUser = new User();
                    newUser.setId(order.getCustomer().getId());
                    newUser.setCart(order);
                    isOrderCreated = userService.updateUser(newUser) > 0;
                }
            }
        } catch (DAOException e) {
            throw new ServiceException("Order adding error", e);
        }
        return isOrderCreated;
    }

    @Override
    public List<Order> findOrdersByCustomer(int customerId, OrderStatus orderStatus, boolean asc, int count, int page) throws ServiceException {
        List<Order> orderList;
        DAOProvider daoProvider = DAOProvider.getInstance();
        OrderDAO orderDAO = daoProvider.getOrderRepository();
        try {
            ServiceProvider serviceProvider = ServiceProvider.getInstance();
            OrderStatusService orderStatusService = serviceProvider.getOrderStatusService();
            Map<String, Object> orderStatusParameter = new LinkedHashMap<>();
            orderStatusParameter.put(OrderTable.CUSTOMER_ID.getColumnName(), customerId);
            if (orderStatus.getName() != null) {
                Optional<OrderStatus> status = orderStatusService.findOrderStatusByName(orderStatus.getName());
                if (status.isPresent())
                    orderStatus = status.get();
            }
            orderStatusParameter.put(OrderTable.ORDER_STATUS_ID.getColumnName(), orderStatus.getId());
            int firstRow = (page - 1) * count + 1;
            orderList = orderDAO.browseOrders(ParameterMap.of(orderStatusParameter), false,
                    OrderTable.ID.getColumnName(), asc, firstRow, count);
            if (orderList.size() > 0) {
                List<OrderStatus> orderStatusList = orderStatusService.browseOrderStatuses();
                for (Order order : orderList) {
                    Optional<OrderStatus> status = orderStatusList.stream().filter(oStatus
                            -> order.getStatus().getId() == oStatus.getId()).findFirst();
                    status.ifPresent(order::setStatus);
                }
            }
        } catch (DAOException e) {
            throw new ServiceException("Obtaining orders error", e);
        }
        return orderList;
    }

    @Override
    public List<Order> findOrdersByStatus(OrderStatus orderStatus, boolean asc, int count, int page) throws ServiceException {
        List<Order> orderList;
        DAOProvider daoProvider = DAOProvider.getInstance();
        OrderDAO orderDAO = daoProvider.getOrderRepository();
        try {
            ServiceProvider serviceProvider = ServiceProvider.getInstance();
            Map<String, Object> orderStatusParameters = new LinkedHashMap<>();
            if (orderStatus.getName() != null) {
                OrderStatusService orderStatusService = serviceProvider.getOrderStatusService();
                Optional<OrderStatus> status = orderStatusService.findOrderStatusByName(orderStatus.getName());
                if (status.isPresent())
                    orderStatus = status.get();
            }
            orderStatusParameters.put(OrderTable.ORDER_STATUS_ID.getColumnName(), orderStatus.getId());
            int firstRow = (page - 1) * count + 1;
            orderList = orderDAO.browseOrders(ParameterMap.of(orderStatusParameters), false,
                    OrderTable.ID.getColumnName(), asc, firstRow, count);
            if (orderList.size() > 0) {
                UserService userService = serviceProvider.getUserService();
                for (Order order : orderList) {
                    order.setStatus(orderStatus);
                    Optional<User> customer = userService.findUserById(order.getCustomer().getId());
                    customer.ifPresent(order::setCustomer);
                    Optional<User> manager = userService.findUserById(order.getManager().getId());
                    manager.ifPresent(order::setManager);
                }
            }
        } catch (DAOException e) {
            throw new ServiceException("Obtaining orders error", e);
        }
        return orderList;
    }

    @Override
    public Optional<Order> findOrderById(int orderId) throws ServiceException {
        Optional<Order> order;
        DAOProvider daoProvider = DAOProvider.getInstance();
        OrderDAO orderDAO = daoProvider.getOrderRepository();
        try {
            Map<String, Object> orderIdParameter = new LinkedHashMap<>();
            orderIdParameter.put(OrderTable.ID.getColumnName(), orderId);
            List<Order> orderList = orderDAO.findOrders(ParameterMap.of(orderIdParameter));
            order = orderList.size() == 1 ? Optional.of(orderList.get(0)) : Optional.empty();
        } catch (DAOException e) {
            throw new ServiceException("Order searching error", e);
        }
        return order;
    }

    @Override
    public boolean updateOrder(Order order) throws ServiceException {
        boolean isOrderUpdated = false;
        if (order.getId() > 0) {
            DAOProvider daoProvider = DAOProvider.getInstance();
            OrderDAO orderDAO = daoProvider.getOrderRepository();
            try {
                ServiceProvider serviceProvider = ServiceProvider.getInstance();
                Map<String, Object> orderIdParameter = new LinkedHashMap<>();
                orderIdParameter.put(OrderTable.ID.getColumnName(), order.getId());
                if (order.getStatus() != null) {
                    if (OrderStatus.FINISHED.equals(order.getStatus().getName())) {
                        order.setDeliveryDate(Timestamp.from(Instant.now()));
                    }
                    OrderStatusService orderStatusService = serviceProvider.getOrderStatusService();
                    Optional<OrderStatus> orderStatus = orderStatusService.findOrderStatusByName(order.getStatus().getName());
                    orderStatus.ifPresent(order::setStatus);
                }
                isOrderUpdated = orderDAO.updateOrder(ParameterMap.of(orderIdParameter), order);
                if (isOrderUpdated) {
                    if (OrderStatus.AWAITING.equals(order.getStatus().getName())) {
                        OrderItemService orderItemService = serviceProvider.getOrderItemService();
                        List<OrderItem> orderItems = orderItemService.browseOrderItems(order.getId());
                        for (OrderItem orderItem : orderItems) {
                            orderItemService.calculateTotalPrice(orderItem.getOrder().getId(), orderItem.getDish().getId());
                        }
                    }
                    if (OrderStatus.NOT_PAID.equals(order.getStatus().getName())) {
                        UserService userService = serviceProvider.getUserService();
                        User newUser = new User();
                        newUser.setStatus(new UserStatus(UserStatus.VIOLATED));
                        Optional<Order> orderOptional = findOrderById(order.getId());
                        orderOptional.ifPresent(value -> newUser.setId(value.getId()));
                        userService.updateUser(newUser);
                    }
                }
            } catch (DAOException e) {
                throw new ServiceException("Order updating error", e);
            }
        }
        return isOrderUpdated;
    }

    @Override
    public boolean deleteOrder(int orderId) throws ServiceException {
        boolean isOrderDeleted;
        DAOProvider daoProvider = DAOProvider.getInstance();
        OrderDAO orderDAO = daoProvider.getOrderRepository();
        try {
            Map<String, Object> orderIdParameter = new LinkedHashMap<>();
            orderIdParameter.put(OrderTable.ID.getColumnName(), orderId);
            isOrderDeleted = orderDAO.deleteOrder(ParameterMap.of(orderIdParameter));
            if (isOrderDeleted) {
                ServiceProvider serviceProvider = ServiceProvider.getInstance();
                OrderItemService orderItemService = serviceProvider.getOrderItemService();
                List<OrderItem> orderItemList = orderItemService.browseOrderItems(orderId);
                for (OrderItem orderItem : orderItemList) {
                    orderItemService.deleteOrderItem(orderItem.getOrder().getId(), orderItem.getDish().getId());
                }
            }
        } catch (DAOException e) {
            throw new ServiceException("Order deleting error", e);
        }
        return isOrderDeleted;
    }

    @Override
    public int countOrders(OrderStatus orderStatus) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        OrderDAO orderDAO = daoProvider.getOrderRepository();
        try {
            Map<String, Object> orderParameters = new LinkedHashMap<>();
            ServiceProvider serviceProvider = ServiceProvider.getInstance();
            OrderStatusService orderStatusService = serviceProvider.getOrderStatusService();
            Optional<OrderStatus> status = orderStatusService.findOrderStatusByName(orderStatus.getName());
            status.ifPresent(value -> orderParameters.put(OrderTable.ORDER_STATUS_ID.getColumnName(), value.getId()));
            return orderDAO.countOrders(ParameterMap.of(orderParameters), false);
        } catch (DAOException e) {
            throw new ServiceException("Order counting error", e);
        }
    }

    @Override
    public int countOrders(int customerId, OrderStatus orderStatus) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        OrderDAO orderDAO = daoProvider.getOrderRepository();
        try {
            Map<String, Object> orderParameters = new LinkedHashMap<>();
            orderParameters.put(OrderTable.CUSTOMER_ID.getColumnName(), customerId);
            ServiceProvider serviceProvider = ServiceProvider.getInstance();
            OrderStatusService orderStatusService = serviceProvider.getOrderStatusService();
            Optional<OrderStatus> status = orderStatusService.findOrderStatusByName(orderStatus.getName());
            status.ifPresent(value -> orderParameters.put(OrderTable.ORDER_STATUS_ID.getColumnName(), value.getId()));
            return orderDAO.countOrders(ParameterMap.of(orderParameters), false);
        } catch (DAOException e) {
            throw new ServiceException("Order counting error", e);
        }
    }
}