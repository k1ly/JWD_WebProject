package by.epamtc.lyskovkirill.restaurant.service;

import by.epamtc.lyskovkirill.restaurant.bean.Order;
import by.epamtc.lyskovkirill.restaurant.bean.OrderStatus;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * Service interface that encapsulates {@link Order} java bean class processing
 * via connecting Controller layer and DAO layer of the layered architecture.
 *
 * @author k1ly
 */
public interface OrderService {

    /**
     * Tries to add the specified {@link Order} using the DAO method.
     * Returns boolean adding process status value.
     *
     * @param order the {@link Order} which should be added.
     * @return false if {@link Order} was not added,
     * or true if {@link Order} was successfully added.
     * @throws ServiceException if an error occurred during order adding process.
     */
    boolean addOrder(Order order) throws ServiceException;

    /**
     * Tries to find {@link Order} entries by the specified <b>customer id</b> with
     * such {@link OrderStatus} using the DAO method, sorted by <b>asc</b> ascending flag.
     * Takes only <b>count</b> of specified <b>page</b>. Returns list of existing entries.
     *
     * @param customerId the order customer id.
     * @param orderStatus the order status.
     * @param asc the boolean flag of order ascending.
     * @param count the integer value of entry count that should be taken.
     * @param page the integer page number value where to start counting from.
     * @return the {@link List} of existing {@link Order} entries.
     * @throws ServiceException if an error occurred during orders obtaining process.
     */
    List<Order> findOrdersByCustomer(int customerId, OrderStatus orderStatus, boolean asc, int count, int page) throws ServiceException;

    /**
     * Tries to find {@link Order} entries with such {@link OrderStatus} using the DAO method,
     * sorted by <b>asc</b> ascending flag.
     * Takes only <b>count</b> of specified <b>page</b>. Returns list of existing entries.
     *
     * @param orderStatus the order status.
     * @param asc the boolean flag of order ascending.
     * @param count the integer value of entry count that should be taken.
     * @param page the integer page number value where to start counting from.
     * @return the {@link List} of existing {@link Order} entries.
     * @throws ServiceException if an error occurred during orders obtaining process.
     */
    List<Order> findOrdersByStatus(OrderStatus orderStatus, boolean asc, int count, int page) throws ServiceException;

    /**
     * Tries to find a {@link Order} entry by the specified <b>id</b> using the DAO method.
     *
     * @param orderId the order id.
     * @return the {@link Optional#empty()} if the order with this id was not found,
     * or {@link Order} entry if such order exists.
     * @throws ServiceException if an error occurred during order searching process.
     */
    Optional<Order> findOrderById(int orderId) throws ServiceException;

    /**
     * Tries to update the specified {@link Order} using the DAO method.
     * Returns boolean updating process status value.
     *
     * @param order the {@link Order} which should be updated by its <b>id</b>.
     * @return false if {@link Order} was not updated,
     * or true if {@link Order} was successfully updated.
     * @throws ServiceException if an error occurred during order updating process.
     */
    boolean updateOrder(Order order) throws ServiceException;

    /**
     * Tries to delete existing {@link Order} entry by the specified <b>id</b> using the DAO method.
     *
     * @param orderId the order id.
     * @return false if {@link Order} was not deleted,
     * or true if {@link Order} was successfully deleted.
     * @throws ServiceException if an error occurred during order deleting process.
     */
    boolean deleteOrder(int orderId) throws ServiceException;

    /**
     * Counts the total number of {@link Order} entries with such {@link OrderStatus} using the DAO method.
     *
     * @param orderStatus the order status.
     * @return an integer value of orders count.
     * @throws ServiceException if an error occurred during orders counting process.
     */
    int countOrders(OrderStatus orderStatus) throws ServiceException;

    /**
     * Counts the total number of {@link Order} entries by the specified
     * <b>customer id</b> with such {@link OrderStatus} using the DAO method.
     *
     * @param customerId the order customer id.
     * @param orderStatus the order status.
     * @return an integer value of orders count.
     * @throws ServiceException if an error occurred during orders counting process.
     */
    int countOrders(int customerId, OrderStatus orderStatus) throws ServiceException;
}
