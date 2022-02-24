package by.epamtc.lyskovkirill.restaurant.service;

import by.epamtc.lyskovkirill.restaurant.bean.Dish;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * Service interface that encapsulates {@link Dish} java bean class processing
 * via connecting Controller layer and DAO layer of the layered architecture.
 *
 * @author k1ly
 */
public interface DishService {

    /**
     * Validates the specified {@link Dish} and tries to add it using the DAO method.
     * Returns integer adding process status value.
     *
     * @param dish the {@link Dish} which should be added.
     * @return a negative integer if {@link Dish} is not valid, zero if {@link Dish} was not added,
     * or a positive integer if {@link Dish} was successfully added.
     * @throws ServiceException if an error occurred during dish adding process.
     */
    int addDish(Dish dish) throws ServiceException;

    /**
     * Browses {@link Dish} entries using the DAO method, sorted by <b>orderAttr</b> and <b>asc</b> ascending flag.
     * Takes only <b>count</b> of specified <b>page</b>. Returns list of existing entries.
     *
     * @param orderAttr the name of sorting order attribute.
     * @param asc the boolean flag of order ascending.
     * @param count the integer value of entry count that should be taken.
     * @param page the integer page number value where to start counting from.
     * @return the {@link List} of existing {@link Dish} entries.
     * @throws ServiceException if an error occurred during dishes obtaining process.
     */
    List<Dish> browseDishes(String orderAttr, boolean asc, int count, int page) throws ServiceException;

    /**
     * Tries to find {@link Dish} entries by the specified <b>category id</b> using the DAO method,
     * sorted by <b>orderAttr</b> and <b>asc</b> ascending flag.
     * Takes only <b>count</b> of specified <b>page</b>. Returns list of existing entries.
     *
     * @param categoryId the dish category id.
     * @param asc the boolean flag of order ascending.
     * @param count the integer value of entry count that should be taken.
     * @param page the integer page number value where to start counting from.
     * @return the {@link List} of existing {@link Dish} entries.
     * @throws ServiceException if an error occurred during dishes obtaining process.
     */
    List<Dish> findDishesByCategory(int categoryId, String orderAttr, boolean asc, int count, int page) throws ServiceException;

    /**
     * Tries to find a {@link Dish} entry by the specified <b>id</b> using the DAO method.
     *
     * @param dishId the dish id.
     * @return the {@link Optional#empty()} if the dish with this id was not found,
     * or {@link Dish} entry if such dish exists.
     * @throws ServiceException if an error occurred during dish searching process.
     */
    Optional<Dish> findDishById(int dishId) throws ServiceException;

    /**
     * Validates the specified {@link Dish} and tries to update it using the DAO method.
     * Returns integer updating process status value.
     *
     * @param dish the {@link Dish} which should be updated by its <b>id</b>.
     * @return a negative integer if {@link Dish} is not valid, zero if {@link Dish} was not updated,
     * or a positive integer if {@link Dish} was successfully updated.
     * @throws ServiceException if an error occurred during dish updating process.
     */
    int updateDish(Dish dish) throws ServiceException;

    /**
     * Tries to delete existing {@link Dish} entry by the specified <b>id</b> using the DAO method.
     *
     * @param dishId the order id.
     * @return false if {@link Dish} was not deleted,
     * or true if {@link Dish} was successfully deleted.
     * @throws ServiceException if an error occurred during dish deleting process.
     */
    boolean deleteDish(int dishId) throws ServiceException;

    /**
     * Counts the total number of {@link Dish} entries using the DAO method.
     *
     * @return an integer value of dishes count.
     * @throws ServiceException if an error occurred during dishes counting process.
     */
    int countDishes() throws ServiceException;

    /**
     * Counts the total number of {@link Dish} entries by the specified
     * <b>category id</b> using the DAO method.
     *
     * @param categoryId the dish category id.
     * @return an integer value of orders count.
     * @throws ServiceException if an error occurred during dishes counting process.
     */
    int countDishes(int categoryId) throws ServiceException;
}
