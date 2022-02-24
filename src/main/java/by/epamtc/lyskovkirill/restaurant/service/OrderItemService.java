package by.epamtc.lyskovkirill.restaurant.service;

import by.epamtc.lyskovkirill.restaurant.bean.Order;
import by.epamtc.lyskovkirill.restaurant.bean.OrderItem;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * Service interface that encapsulates {@link OrderItem} java bean class processing
 * via connecting Controller layer and DAO layer of the layered architecture.
 *
 * @author k1ly
 */
public interface OrderItemService {

    /**
     * Tries to add the specified {@link OrderItem} using the DAO method.
     * Returns boolean adding process status value.
     *
     * @param orderItem the {@link OrderItem} which should be added.
     * @return false if {@link OrderItem} was not added,
     * or true if {@link OrderItem} was successfully added.
     * @throws ServiceException if an error occurred during order item adding process.
     */
    boolean addOrderItem(OrderItem orderItem) throws ServiceException;

    /**
     * Tries to find {@link OrderItem} entries by the specified <b>order id</b> using the DAO method.
     * Returns list of existing entries.
     *
     * @param orderId the order id.
     * @return the {@link List} of existing {@link OrderItem} entries.
     * @throws ServiceException if an error occurred during order items obtaining process.
     */
    List<OrderItem> browseOrderItems(int orderId) throws ServiceException;

    /**
     * Tries to find a {@link OrderItem} entry by the specified <b>order id</b>
     * and <b>dish id</b> using the DAO method.
     *
     * @param orderId the order id.
     * @param dishId the dish id.
     * @return the {@link Optional#empty()} if the order item with these ids was not found,
     * or {@link OrderItem} entry if such order item exists.
     * @throws ServiceException if an error occurred during order item searching process.
     */
    Optional<OrderItem> findOrderItemByIds(int orderId, int dishId) throws ServiceException;

    /**
     * Tries to update the specified {@link OrderItem} using the DAO method.
     * Returns boolean updating process status value.
     *
     * @param orderItem the {@link OrderItem} which should be updated.
     * @param isNew boolean adding flag.
     * @return false if {@link OrderItem} was not updated,
     * or true if {@link OrderItem} was successfully updated.
     * @throws ServiceException if an error occurred during order item updating process.
     */
    boolean updateOrderItem(OrderItem orderItem, boolean isNew) throws ServiceException;

    /**
     * Tries to delete existing {@link Order} entry by the specified <b>id</b> using the DAO method.
     *
     * @param orderId the order id.
     * @return false if {@link Order} was not deleted,
     * or true if {@link Order} was successfully deleted.
     * @throws ServiceException if an error occurred during order deleting process.
     */
    boolean deleteOrderItem(int orderId, int dishId) throws ServiceException;

    /**
     * Tries to calculate {@link OrderItem} total price using the DAO method.
     *
     * @param orderId the order id.
     * @param dishId the dish id.
     * @throws ServiceException if an error occurred during order item total price calculating process.
     */
    void calculateTotalPrice(int orderId, int dishId) throws ServiceException;

    /**
     * Tries to restore deleted {@link OrderItem} entry by the specified <b>order id</b> and <b>dish id</b> using the DAO method.
     *
     * @param orderId the order id.
     * @param dishId the dish id.
     * @return the {@link Optional#empty()} if the order item with these ids was not restored,
     * or {@link OrderItem} entry if such order item is restored.
     * @throws ServiceException if an error occurred during order item restoring process.
     */
    Optional<OrderItem> restoreOrderItem(int orderId, int dishId) throws ServiceException;
}
