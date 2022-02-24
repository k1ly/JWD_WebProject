package by.epamtc.lyskovkirill.restaurant.service.impl;

import by.epamtc.lyskovkirill.restaurant.bean.Dish;
import by.epamtc.lyskovkirill.restaurant.bean.OrderItem;
import by.epamtc.lyskovkirill.restaurant.dao.OrderItemDAO;
import by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable.OrderItemTable;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;
import by.epamtc.lyskovkirill.restaurant.dao.DAOProvider;
import by.epamtc.lyskovkirill.restaurant.dao.parameter.ParameterMap;
import by.epamtc.lyskovkirill.restaurant.service.DishService;
import by.epamtc.lyskovkirill.restaurant.service.OrderItemService;
import by.epamtc.lyskovkirill.restaurant.service.ServiceProvider;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * {@link OrderItemService} interface implementation.
 *
 * @author k1ly
 */
public class OrderItemServiceImpl implements OrderItemService {

    @Override
    public boolean addOrderItem(OrderItem orderItem) throws ServiceException {
        boolean isOrderItemAdded;
        DAOProvider daoProvider = DAOProvider.getInstance();
        OrderItemDAO orderItemDAO = daoProvider.getOrderItemRepository();
        try {
            isOrderItemAdded = orderItemDAO.addOrderItem(orderItem);
        } catch (DAOException e) {
            throw new ServiceException("Order item adding error", e);
        }
        return isOrderItemAdded;
    }

    @Override
    public List<OrderItem> browseOrderItems(int orderId) throws ServiceException {
        List<OrderItem> orderItemList;
        DAOProvider daoProvider = DAOProvider.getInstance();
        OrderItemDAO orderItemDAO = daoProvider.getOrderItemRepository();
        try {
            Map<String, Object> orderItemIdParameters = new LinkedHashMap<>();
            orderItemIdParameters.put(OrderItemTable.ORDER_ID.getColumnName(), orderId);
            orderItemList = orderItemDAO.findOrderItems(ParameterMap.of(orderItemIdParameters));
            if (orderItemList.size() > 0) {
                ServiceProvider serviceProvider = ServiceProvider.getInstance();
                DishService dishService = serviceProvider.getDishService();
                for (OrderItem orderItem : orderItemList) {
                    Optional<Dish> dish = dishService.findDishById(orderItem.getDish().getId());
                    dish.ifPresent(orderItem::setDish);
                }
            }
        } catch (DAOException e) {
            throw new ServiceException("Order items obtaining error", e);
        }
        return orderItemList;
    }

    @Override
    public Optional<OrderItem> findOrderItemByIds(int orderId, int dishId) throws ServiceException {
        Optional<OrderItem> orderItem;
        DAOProvider daoProvider = DAOProvider.getInstance();
        OrderItemDAO orderItemDAO = daoProvider.getOrderItemRepository();
        try {
            Map<String, Object> orderItemIdParameters = new LinkedHashMap<>();
            orderItemIdParameters.put(OrderItemTable.ORDER_ID.getColumnName(), orderId);
            orderItemIdParameters.put(OrderItemTable.DISH_ID.getColumnName(), dishId);
            List<OrderItem> orderItemList = orderItemDAO.findOrderItems(ParameterMap.of(orderItemIdParameters));
            orderItem = orderItemList.size() == 1 ? Optional.of(orderItemList.get(0)) : Optional.empty();
        } catch (DAOException e) {
            throw new ServiceException("Order item searching error", e);
        }
        return orderItem;
    }

    @Override
    public boolean updateOrderItem(OrderItem orderItem, boolean isNew) throws ServiceException {
        boolean isOrderItemUpdated = false;
        if (orderItem.getOrder() != null && orderItem.getOrder().getId() > 0
                && orderItem.getDish() != null && orderItem.getDish().getId() > 0) {
            DAOProvider daoProvider = DAOProvider.getInstance();
            OrderItemDAO orderItemDAO = daoProvider.getOrderItemRepository();
            try {
                Map<String, Object> orderItemIdParameters = new LinkedHashMap<>();
                orderItemIdParameters.put(OrderItemTable.ORDER_ID.getColumnName(), orderItem.getOrder().getId());
                orderItemIdParameters.put(OrderItemTable.DISH_ID.getColumnName(), orderItem.getDish().getId());
                Optional<OrderItem> cartItem = findOrderItemByIds(orderItem.getOrder().getId(), orderItem.getDish().getId());
                if (cartItem.isPresent()) {
                    if (orderItem.getQuantity() > 0) {
                        int quantity = orderItem.getQuantity();
                        orderItem = new OrderItem();
                        orderItem.setQuantity(isNew ? cartItem.get().getQuantity() + quantity : quantity);
                        isOrderItemUpdated = orderItemDAO.updateOrderItem(ParameterMap.of(orderItemIdParameters), orderItem);
                    } else
                        isOrderItemUpdated = deleteOrderItem(orderItem.getOrder().getId(), orderItem.getDish().getId());
                } else if (orderItem.getQuantity() > 0) {
                    cartItem = restoreOrderItem(orderItem.getOrder().getId(), orderItem.getDish().getId());
                    if (cartItem.isPresent()) {
                        if (!cartItem.get().getQuantity().equals(orderItem.getQuantity())) {
                            int quantity = orderItem.getQuantity();
                            orderItem = new OrderItem();
                            orderItem.setQuantity(quantity);
                            isOrderItemUpdated = orderItemDAO.updateOrderItem(ParameterMap.of(orderItemIdParameters), orderItem);
                        }
                    } else {
                        isOrderItemUpdated = addOrderItem(orderItem);
                    }
                }
            } catch (DAOException e) {
                throw new ServiceException("Order item updating error", e);
            }
        }
        return isOrderItemUpdated;
    }

    @Override
    public boolean deleteOrderItem(int orderId, int dishId) throws ServiceException {
        boolean isOrderItemDeleted;
        DAOProvider daoProvider = DAOProvider.getInstance();
        OrderItemDAO orderItemDAO = daoProvider.getOrderItemRepository();
        try {
            Map<String, Object> orderItemIdParameters = new LinkedHashMap<>();
            orderItemIdParameters.put(OrderItemTable.ORDER_ID.getColumnName(), orderId);
            orderItemIdParameters.put(OrderItemTable.DISH_ID.getColumnName(), dishId);
            isOrderItemDeleted = orderItemDAO.deleteOrderItem(ParameterMap.of(orderItemIdParameters));
        } catch (DAOException e) {
            throw new ServiceException("Order item deleting error", e);
        }
        return isOrderItemDeleted;
    }

    @Override
    public void calculateTotalPrice(int orderId, int dishId) throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        OrderItemDAO orderItemDAO = daoProvider.getOrderItemRepository();
        try {
            Optional<OrderItem> orderItem = findOrderItemByIds(orderId, dishId);
            if (orderItem.isPresent()) {
                ServiceProvider serviceProvider = ServiceProvider.getInstance();
                DishService dishService = serviceProvider.getDishService();
                Optional<Dish> dish = dishService.findDishById(orderItem.get().getDish().getId());
                if (dish.isPresent()) {
                    OrderItem item = new OrderItem();
                    item.setTotalPrice((dish.get().getDiscount() != null ? dish.get().getPrice() / 100
                            * (100 - dish.get().getDiscount()) : dish.get().getPrice()) * item.getQuantity());
                    Map<String, Object> orderItemIdParameters = new LinkedHashMap<>();
                    orderItemIdParameters.put(OrderItemTable.ORDER_ID.getColumnName(), orderId);
                    orderItemIdParameters.put(OrderItemTable.DISH_ID.getColumnName(), dishId);
                    orderItemDAO.updateOrderItem(ParameterMap.of(orderItemIdParameters), item);
                }
            }
        } catch (DAOException e) {
            throw new ServiceException("Order item total price calculating error", e);
        }
    }

    @Override
    public Optional<OrderItem> restoreOrderItem(int orderId, int dishId) throws ServiceException {
        Optional<OrderItem> orderItem;
        DAOProvider daoProvider = DAOProvider.getInstance();
        OrderItemDAO orderItemDAO = daoProvider.getOrderItemRepository();
        try {
            Map<String, Object> orderItemIdParameters = new LinkedHashMap<>();
            orderItemIdParameters.put(OrderItemTable.ORDER_ID.getColumnName(), orderId);
            orderItemIdParameters.put(OrderItemTable.DISH_ID.getColumnName(), dishId);
            orderItem = orderItemDAO.restoreOrderItem(ParameterMap.of(orderItemIdParameters));
        } catch (DAOException e) {
            throw new ServiceException("Order item restoring error", e);
        }
        return orderItem;
    }
}
