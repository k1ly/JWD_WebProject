package by.epamtc.lyskovkirill.restaurant.service;

import by.epamtc.lyskovkirill.restaurant.bean.DishCategory;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * Service interface that encapsulates {@link DishCategory} java bean class processing
 * via connecting Controller layer and DAO layer of the layered architecture.
 *
 * @author k1ly
 */
public interface DishCategoryService {

    /**
     * Tries to add the specified {@link DishCategory} using the DAO method.
     * Returns integer adding process status value.
     *
     * @param dishCategory the {@link DishCategory} which should be added.
     * @return a negative integer if {@link DishCategory} is not valid, zero if {@link DishCategory} was not added,
     * or a positive integer if {@link DishCategory} was successfully added.
     * @throws ServiceException if an error occurred during dish category adding process.
     */
    int addDishCategory(DishCategory dishCategory) throws ServiceException;

    /**
     * Browses all {@link DishCategory} entries using the DAO method. Returns list of existing entries.
     *
     * @return the {@link List} of existing {@link DishCategory} entries.
     * @throws ServiceException if an error occurred during dish categories obtaining process.
     */
    List<DishCategory> browseDishCategories() throws ServiceException;

    /**
     * Tries to find a {@link DishCategory} entry by the specified <b>id</b> using the DAO method.
     *
     * @param dishCategoryId the dish category status id.
     * @return the {@link Optional#empty()} if the dish category with this id was not found,
     * or {@link DishCategory} entry if such dish category exists.
     * @throws ServiceException if an error occurred during dish category searching process.
     */
    Optional<DishCategory> findDishCategoryById(int dishCategoryId) throws ServiceException;

    /**
     * Tries to update the specified {@link DishCategory} using the DAO method.
     * Returns integer updating process status value.
     *
     * @param dishCategory the {@link DishCategory} which should be updated by its <b>id</b>.
     * @return false if {@link DishCategory} was not updated,
     * or true if {@link DishCategory} was successfully updated.
     * @throws ServiceException if an error occurred during dish category updating process.
     */
    int updateDishCategory(DishCategory dishCategory) throws ServiceException;

    /**
     * Tries to delete existing {@link DishCategory} entry by the specified <b>id</b> using the DAO method.
     *
     * @param dishCategoryId the order id.
     * @return false if {@link DishCategory} was not deleted,
     * or true if {@link DishCategory} was successfully deleted.
     * @throws ServiceException if an error occurred during dish category deleting process.
     */
    boolean deleteDishCategory(int dishCategoryId) throws ServiceException;
}
