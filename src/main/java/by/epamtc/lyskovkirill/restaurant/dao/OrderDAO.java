package by.epamtc.lyskovkirill.restaurant.dao;

import by.epamtc.lyskovkirill.restaurant.bean.Order;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;
import by.epamtc.lyskovkirill.restaurant.dao.parameter.ParameterMap;

import java.util.List;

/**
 * DAO interface that encapsulates {@link Order} java bean class processing using data source.
 *
 * @author k1ly
 */
public interface OrderDAO {

    /**
     * Tries to add the specified {@link Order} into data source.
     * Returns boolean adding process status value.
     *
     * @param order the {@link Order} which should be added.
     * @return false if {@link Order} was not added,
     * or true if {@link Order} was successfully added.
     * @throws DAOException if an error occurred during adding the order.
     */
    boolean addOrder(Order order) throws DAOException;

    /**
     * Tries to find the {@link List} of {@link Order} entries from data source using {@link ParameterMap} as condition.
     * Returns list of existing entries.
     *
     * @param parameters the {@link ParameterMap} that is used for search condition.
     * @return the {@link List} of existing {@link Order} entries.
     * @throws DAOException if an error occurred during retrieving orders.
     */
    List<Order> findOrders(ParameterMap parameters) throws DAOException;

    /**
     * Tries to find the {@link List} of {@link Order} entries from data source using {@link ParameterMap} as condition,
     * depending on <b>includeDeleted</b> flag, sorted by <b>orderAttr</b> and <b>asc</b> ascending flag.
     * Takes only <b>rowCount</b> starting from <b>firstRow</b>. Returns list of existing entries.
     *
     * @param parameters the {@link ParameterMap} that is used for search condition.
     * @param includeDeleted the boolean flag of including deleted entries.
     * @param orderAttr the name of sorting order attribute.
     * @param asc the boolean flag of order ascending.
     * @param firstRow the integer number of a row where to start counting from.
     * @param rowCount the integer row count of entries that should be taken.
     * @return the {@link List} of existing {@link Order} entries.
     * @throws DAOException if an error occurred during retrieving orders.
     */
    List<Order> browseOrders(ParameterMap parameters, boolean includeDeleted,
                             String orderAttr, boolean asc, int firstRow, int rowCount) throws DAOException;

    /**
     * Tries to find order entry in data source using {@link ParameterMap} as condition and update it with new {@link Order} parameter.
     * Returns boolean updating process status value.
     *
     * @param parameters the {@link ParameterMap} that is used for search condition.
     * @param order the {@link Order} whose fields are to be used as a replacement.
     * @return false if {@link Order} was not updated,
     * or true if {@link Order} was successfully updated.
     * @throws DAOException if an error occurred during updating the order.
     */
    boolean updateOrder(ParameterMap parameters, Order order) throws DAOException;

    /**
     * Tries to find {@link Order} entry in data source using {@link ParameterMap} as condition and delete it.
     * Returns boolean deleting process status value.
     *
     * @param deleteId the order id that should be deleted.
     * @return false if {@link Order} was not deleted,
     * or true if {@link Order} was successfully deleted.
     * @throws DAOException if an error occurred during deleting the order.
     */
    boolean deleteOrder(ParameterMap deleteId) throws DAOException;

    /**
     * Counts the total number of {@link Order} entries in data source
     * using {@link ParameterMap} as condition and depending on <b>includeDeleted</b> flag.
     *
     * @param includeDeleted the boolean flag of including deleted entries.
     * @return an integer value of orders count.
     * @throws DAOException if an error occurred during counting orders counting.
     */
    int countOrders(ParameterMap parameters, boolean includeDeleted) throws DAOException;
}
