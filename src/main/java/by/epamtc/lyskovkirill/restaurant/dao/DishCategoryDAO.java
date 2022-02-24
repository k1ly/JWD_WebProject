package by.epamtc.lyskovkirill.restaurant.dao;

import by.epamtc.lyskovkirill.restaurant.bean.DishCategory;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;
import by.epamtc.lyskovkirill.restaurant.dao.parameter.ParameterMap;

import java.util.List;

/**
 * DAO interface that encapsulates {@link DishCategory} java bean class processing using data source.
 *
 * @author k1ly
 */
public interface DishCategoryDAO {

    /**
     * Tries to add the specified {@link DishCategory} into data source.
     * Returns boolean adding process status value.
     *
     * @param dishCategory the {@link DishCategory} which should be added.
     * @return false if {@link DishCategory} was not added,
     * or true if {@link DishCategory} was successfully added.
     * @throws DAOException if an error occurred during adding the dish category.
     */
    boolean addDishCategory(DishCategory dishCategory) throws DAOException;

    /**
     * Tries to find the {@link List} of {@link DishCategory} entries from data source using {@link ParameterMap} as condition.
     * Returns list of existing entries.
     *
     * @param parameters the {@link ParameterMap} that is used for search condition.
     * @return the {@link List} of existing {@link DishCategory} entries.
     * @throws DAOException if an error occurred during retrieving dish categories.
     */
    List<DishCategory> findDishCategories(ParameterMap parameters) throws DAOException;

    /**
     * Retrieves the {@link List} of {@link DishCategory} entries from data source depending on <b>includeDeleted</b> flag,
     * sorted by <b>orderAttr</b> and <b>asc</b> ascending flag.
     * Returns list of existing entries.
     *
     * @param includeDeleted the boolean flag of including deleted entries.
     * @param orderAttr the name of sorting order attribute.
     * @param asc the boolean flag of order ascending.
     * @return the {@link List} of existing {@link DishCategory} entries.
     * @throws DAOException if an error occurred during retrieving dish categories.
     */
    List<DishCategory> browseDishCategories(boolean includeDeleted, String orderAttr, boolean asc) throws DAOException;

    /**
     * Tries to find user entry in data source using {@link ParameterMap} as condition and update it with new {@link DishCategory} parameter.
     * Returns boolean updating process status value.
     *
     * @param parameters the {@link ParameterMap} that is used for search condition.
     * @param dishCategory the {@link DishCategory} whose fields are to be used as a replacement.
     * @return false if {@link DishCategory} was not updated,
     * or true if {@link DishCategory} was successfully updated.
     * @throws DAOException if an error occurred during updating the dish category.
     */
    boolean updateDishCategory(ParameterMap parameters, DishCategory dishCategory) throws DAOException;

    /**
     * Tries to find user entry in data source using {@link ParameterMap} as condition and delete it.
     * Returns boolean deleting process status value.
     *
     * @param deleteId the dish id that should be deleted.
     * @return false if {@link DishCategory} was not deleted,
     * or true if {@link DishCategory} was successfully deleted.
     * @throws DAOException if an error occurred during deleting the dish category.
     */
    boolean deleteDishCategory(ParameterMap deleteId) throws DAOException;
}
