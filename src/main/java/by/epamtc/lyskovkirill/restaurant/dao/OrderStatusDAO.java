package by.epamtc.lyskovkirill.restaurant.dao;

import by.epamtc.lyskovkirill.restaurant.bean.OrderStatus;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;
import by.epamtc.lyskovkirill.restaurant.dao.parameter.ParameterMap;

import java.util.List;

/**
 * DAO interface that encapsulates {@link OrderStatus} java bean class processing using data source.
 *
 * @author k1ly
 */
public interface OrderStatusDAO {

    /**
     * Tries to add the specified {@link OrderStatus} into data source.
     * Returns boolean adding process status value.
     *
     * @param orderStatus the {@link OrderStatus} which should be added.
     * @return false if {@link OrderStatus} was not added,
     * or true if {@link OrderStatus} was successfully added.
     * @throws DAOException if an error occurred during adding the order status.
     */
    boolean addOrderStatus(OrderStatus orderStatus) throws DAOException;

    /**
     * Tries to find the {@link List} of {@link OrderStatus} entries from data source using {@link ParameterMap} as condition.
     * Returns list of existing entries.
     *
     * @param parameters the {@link ParameterMap} that is used for search condition.
     * @return the {@link List} of existing {@link OrderStatus} entries.
     * @throws DAOException if an error occurred during retrieving order statuses.
     */
    List<OrderStatus> findOrderStatuses(ParameterMap parameters) throws DAOException;

    /**
     * Retrieves the {@link List} of {@link OrderStatus} entries from data source depending on <b>includeDeleted</b> flag,
     * sorted by <b>orderAttr</b> and <b>asc</b> ascending flag.
     * Returns list of existing entries.
     *
     * @param includeDeleted the boolean flag of including deleted entries.
     * @param orderAttr the name of sorting order attribute.
     * @param asc the boolean flag of order ascending.
     * @return the {@link List} of existing {@link OrderStatus} entries.
     * @throws DAOException if an error occurred during retrieving order statuses.
     */
    List<OrderStatus> browseOrderStatuses(boolean includeDeleted, String orderAttr, boolean asc) throws DAOException;
}
