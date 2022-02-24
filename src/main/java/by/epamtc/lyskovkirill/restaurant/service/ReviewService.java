package by.epamtc.lyskovkirill.restaurant.service;

import by.epamtc.lyskovkirill.restaurant.bean.Review;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * Service interface that encapsulates {@link Review} java bean class processing
 * via connecting Controller layer and DAO layer of the layered architecture.
 *
 * @author k1ly
 */
public interface ReviewService {

    /**
     * Tries to add the specified {@link Review} using the DAO method.
     * Returns boolean adding process status value.
     *
     * @param review the {@link Review} which should be added.
     * @return false if {@link Review} was not added,
     * or true if {@link Review} was successfully added.
     * @throws ServiceException if an error occurred during review adding process.
     */
    boolean addReview(Review review) throws ServiceException;

    /**
     * Browses {@link Review} entries using the DAO method, sorted by <b>asc</b> ascending flag.
     * Takes only <b>count</b> of specified <b>page</b>. Returns list of existing entries.
     *
     * @param asc the boolean flag of order ascending.
     * @param count the integer value of entry count that should be taken.
     * @param page the integer page number value where to start counting from.
     * @return the {@link List} of existing {@link Review} entries.
     * @throws ServiceException if an error occurred during reviews obtaining process.
     */
    List<Review> browseReviews(boolean asc, int count, int page) throws ServiceException;

    /**
     * Tries to find a {@link Review} entry by the specified <b>id</b> using the DAO method.
     *
     * @param reviewId the review id.
     * @return the {@link Optional#empty()} if the review with this id was not found,
     * or {@link Review} entry if such review exists.
     * @throws ServiceException if an error occurred during review searching process.
     */
    Optional<Review> findReviewById(int reviewId) throws ServiceException;

    /**
     * Counts the total number of {@link Review} entries using the DAO method.
     *
     * @return an integer value of reviews count.
     * @throws ServiceException if an error occurred during reviews counting process.
     */
    int countReviews() throws ServiceException;
}
