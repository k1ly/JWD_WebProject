package by.epamtc.lyskovkirill.restaurant.dao;

import by.epamtc.lyskovkirill.restaurant.bean.OrderItem;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;
import by.epamtc.lyskovkirill.restaurant.dao.parameter.ParameterMap;

import java.util.List;
import java.util.Optional;

/**
 * DAO interface that encapsulates {@link OrderItem} java bean class processing using data source.
 *
 * @author k1ly
 */
public interface OrderItemDAO {

    /**
     * Tries to add the specified {@link OrderItem} into data source.
     * Returns boolean adding process status value.
     *
     * @param orderItem the {@link OrderItem} which should be added.
     * @return false if {@link OrderItem} was not added,
     * or true if {@link OrderItem} was successfully added.
     * @throws DAOException if an error occurred during adding the order item.
     */
    boolean addOrderItem(OrderItem orderItem) throws DAOException;

    /**
     * Tries to find the {@link List} of {@link OrderItem} entries from data source using {@link ParameterMap} as condition.
     * Returns list of existing entries.
     *
     * @param parameters the {@link ParameterMap} that is used for search condition.
     * @return the {@link List} of existing {@link OrderItem} entries.
     * @throws DAOException if an error occurred during retrieving order items.
     */
    List<OrderItem> findOrderItems(ParameterMap parameters) throws DAOException;

    /**
     * Tries to find order item entry in data source using {@link ParameterMap} as condition and update it with new {@link OrderItem} parameter.
     * Returns boolean updating process status value.
     *
     * @param parameters the {@link ParameterMap} that is used for search condition.
     * @param orderItem the {@link OrderItem} whose fields are to be used as a replacement.
     * @return false if {@link OrderItem} was not updated,
     * or true if {@link OrderItem} was successfully updated.
     * @throws DAOException if an error occurred during updating the order item.
     */
    boolean updateOrderItem(ParameterMap parameters, OrderItem orderItem) throws DAOException;

    /**
     * Tries to find {@link OrderItem} entry in data source using {@link ParameterMap} as condition and delete it.
     * Returns boolean deleting process status value.
     *
     * @param deleteId the order item id that should be deleted.
     * @return false if {@link OrderItem} was not deleted,
     * or true if {@link OrderItem} was successfully deleted.
     * @throws DAOException if an error occurred during deleting the order item.
     */
    boolean deleteOrderItem(ParameterMap deleteId) throws DAOException;

    /**
     * Tries to find deleted {@link OrderItem} entry in data source using {@link ParameterMap} as condition and restore it.
     * Returns boolean deleting process status value.
     *
     * @param restoreId the order item id that should be restored.
     * @return the {@link Optional#empty()} if such order item was not restored,
     * or {@link OrderItem} entry if such order item is restored.
     * @throws DAOException if an error occurred during restoring the order item.
     */
    Optional<OrderItem> restoreOrderItem(ParameterMap restoreId) throws DAOException;
}
