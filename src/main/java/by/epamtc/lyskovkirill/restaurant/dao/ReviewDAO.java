package by.epamtc.lyskovkirill.restaurant.dao;

import by.epamtc.lyskovkirill.restaurant.bean.Review;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;
import by.epamtc.lyskovkirill.restaurant.dao.parameter.ParameterMap;

import java.util.List;

/**
 * DAO interface that encapsulates {@link Review} java bean class processing using data source.
 *
 * @author k1ly
 */
public interface ReviewDAO {

    /**
     * Tries to add the specified {@link Review} into data source.
     * Returns boolean adding process status value.
     *
     * @param review the {@link Review} which should be added.
     * @return false if {@link Review} was not added,
     * or true if {@link Review} was successfully added.
     * @throws DAOException if an error occurred during adding the review.
     */
    boolean addReview(Review review) throws DAOException;

    /**
     * Tries to find the {@link List} of {@link Review} entries from data source using {@link ParameterMap} as condition.
     * Returns list of existing entries.
     *
     * @param parameters the {@link ParameterMap} that is used for search condition.
     * @return the {@link List} of existing {@link Review} entries.
     * @throws DAOException if an error occurred during retrieving reviews.
     */
    List<Review> findReviews(ParameterMap parameters) throws DAOException;

    /**
     * Retrieves the {@link List} of {@link Review} entries from data source depending on <b>includeDeleted</b> flag,
     * sorted by <b>orderAttr</b> and <b>asc</b> ascending flag.
     * Takes only <b>rowCount</b> starting from <b>firstRow</b>. Returns list of existing entries.
     *
     * @param includeDeleted the boolean flag of including deleted entries.
     * @param orderAttr the name of sorting order attribute.
     * @param asc the boolean flag of order ascending.
     * @param firstRow the integer number of a row where to start counting from.
     * @param rowCount the integer row count of entries that should be taken.
     * @return the {@link List} of existing {@link Review} entries.
     * @throws DAOException if an error occurred during retrieving reviews.
     */
    List<Review> browseReviews(boolean includeDeleted, String orderAttr, boolean asc, int firstRow, int rowCount) throws DAOException;

    /**
     * Counts the total number of {@link Review} entries in data source depending on <b>includeDeleted</b> flag.
     *
     * @param includeDeleted the boolean flag of including deleted entries.
     * @return an integer value of users count.
     * @throws DAOException if an error occurred during counting reviews counting.
     */
    int countReviews(boolean includeDeleted) throws DAOException;
}
