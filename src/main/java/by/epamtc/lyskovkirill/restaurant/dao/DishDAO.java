package by.epamtc.lyskovkirill.restaurant.dao;

import by.epamtc.lyskovkirill.restaurant.bean.Dish;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;
import by.epamtc.lyskovkirill.restaurant.dao.parameter.ParameterMap;

import java.util.List;

/**
 * DAO interface that encapsulates {@link Dish} java bean class processing using data source.
 *
 * @author k1ly
 */
public interface DishDAO {

    /**
     * Tries to add the specified {@link Dish} into data source.
     * Returns boolean adding process status value.
     *
     * @param dish the {@link Dish} which should be added.
     * @return false if {@link Dish} was not added,
     * or true if {@link Dish} was successfully added.
     * @throws DAOException if an error occurred during adding the dish.
     */
    boolean addDish(Dish dish) throws DAOException;

    /**
     * Tries to find the {@link List} of {@link Dish} entries from data source using {@link ParameterMap} as condition.
     * Returns list of existing entries.
     *
     * @param parameters the {@link ParameterMap} that is used for search condition.
     * @return the {@link List} of existing {@link Dish} entries.
     * @throws DAOException if an error occurred during retrieving dishes.
     */
    List<Dish> findDishes(ParameterMap parameters) throws DAOException;

    /**
     * Tries to find the {@link List} of {@link Dish} entries from data source using {@link ParameterMap} as condition,
     * depending on <b>includeDeleted</b> flag, sorted by <b>orderAttr</b> and <b>asc</b> ascending flag.
     * Takes only <b>rowCount</b> starting from <b>firstRow</b>. Returns list of existing entries.
     *
     * @param parameters the {@link ParameterMap} that is used for search condition.
     * @param includeDeleted the boolean flag of including deleted entries.
     * @param orderAttr the name of sorting order attribute.
     * @param asc the boolean flag of order ascending.
     * @param firstRow the integer number of a row where to start counting from.
     * @param rowCount the integer row count of entries that should be taken.
     * @return the {@link List} of existing {@link Dish} entries.
     * @throws DAOException if an error occurred during retrieving dishes.
     */
    List<Dish> findDishes(ParameterMap parameters, boolean includeDeleted, String orderAttr, boolean asc, int firstRow, int rowCount) throws DAOException;

    /**
     * Retrieves the {@link List} of {@link Dish} entries from data source depending on <b>includeDeleted</b> flag,
     * sorted by <b>orderAttr</b> and <b>asc</b> ascending flag.
     * Takes only <b>rowCount</b> starting from <b>firstRow</b>. Returns list of existing entries.
     *
     * @param includeDeleted the boolean flag of including deleted entries.
     * @param orderAttr the name of sorting order attribute.
     * @param asc the boolean flag of order ascending.
     * @param firstRow the integer number of a row where to start counting from.
     * @param rowCount the integer row count of entries that should be taken.
     * @return the {@link List} of existing {@link Dish} entries.
     * @throws DAOException if an error occurred during retrieving dishes.
     */
    List<Dish> browseDishes(boolean includeDeleted, String orderAttr, boolean asc, int firstRow, int rowCount) throws DAOException;

    /**
     * Tries to find dish entry in data source using {@link ParameterMap} as condition and update it with new {@link Dish} parameter.
     * Returns boolean updating process status value.
     *
     * @param parameters the {@link ParameterMap} that is used for search condition.
     * @param dish the {@link Dish} whose fields are to be used as a replacement.
     * @return false if {@link Dish} was not updated,
     * or true if {@link Dish} was successfully updated.
     * @throws DAOException if an error occurred during updating the dish.
     */
    boolean updateDish(ParameterMap parameters, Dish dish) throws DAOException;

    /**
     * Tries to find {@link Dish} entry in data source using {@link ParameterMap} as condition and delete it.
     * Returns boolean deleting process status value.
     *
     * @param deleteId the dish id that should be deleted.
     * @return false if {@link Dish} was not deleted,
     * or true if {@link Dish} was successfully deleted.
     * @throws DAOException if an error occurred during deleting the dish.
     */
    boolean deleteDish(ParameterMap deleteId) throws DAOException;

    /**
     * Counts the total number of {@link Dish} entries in data source
     * using {@link ParameterMap} as condition and depending on <b>includeDeleted</b> flag.
     *
     * @param includeDeleted the boolean flag of including deleted entries.
     * @return an integer value of orders count.
     * @throws DAOException if an error occurred during counting dishes counting.
     */
    int countDishes(ParameterMap parameters, boolean includeDeleted) throws DAOException;
}
