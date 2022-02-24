package by.epamtc.lyskovkirill.restaurant.service;

import by.epamtc.lyskovkirill.restaurant.bean.OrderStatus;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * Service interface that encapsulates {@link OrderStatus} java bean class processing
 * via connecting Controller layer and DAO layer of the layered architecture.
 *
 * @author k1ly
 */
public interface OrderStatusService {

    /**
     * Tries to add the specified {@link OrderStatus} using the DAO method.
     * Returns boolean adding process status value.
     *
     * @param orderStatus the {@link OrderStatus} which should be added.
     * @return false if {@link OrderStatus} was not added,
     * or true if {@link OrderStatus} was successfully added.
     * @throws ServiceException if an error occurred during order status adding process.
     */
    boolean addOrderStatus(OrderStatus orderStatus) throws ServiceException;

    /**
     * Browses all {@link OrderStatus} entries using the DAO method. Returns list of existing entries.
     *
     * @return the {@link List} of existing {@link OrderStatus} entries.
     * @throws ServiceException if an error occurred during order statuses obtaining process.
     */
    List<OrderStatus> browseOrderStatuses() throws ServiceException;

    /**
     * Tries to find a {@link OrderStatus} entry by the specified <b>id</b> using the DAO method.
     *
     * @param orderStatusId the order status id.
     * @return the {@link Optional#empty()} if the order status with this id was not found,
     * or {@link OrderStatus} entry if such order status exists.
     * @throws ServiceException if an error occurred during order status searching process.
     */
    Optional<OrderStatus> findOrderStatusById(int orderStatusId) throws ServiceException;

    /**
     * Tries to find a {@link OrderStatus} entry by the specified <b>order status name</b> using the DAO method.
     *
     * @param orderStatusName the order status name.
     * @return the {@link Optional#empty()} if the order status with this id was not found,
     * or {@link OrderStatus} entry if such order status exists.
     * @throws ServiceException if an error occurred during order status searching process.
     */
    Optional<OrderStatus> findOrderStatusByName(String orderStatusName) throws ServiceException;
}
