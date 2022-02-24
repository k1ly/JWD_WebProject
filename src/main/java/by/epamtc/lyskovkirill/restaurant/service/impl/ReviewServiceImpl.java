package by.epamtc.lyskovkirill.restaurant.service.impl;

import by.epamtc.lyskovkirill.restaurant.bean.Review;
import by.epamtc.lyskovkirill.restaurant.bean.User;
import by.epamtc.lyskovkirill.restaurant.dao.ReviewDAO;
import by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable.ReviewTable;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;
import by.epamtc.lyskovkirill.restaurant.dao.DAOProvider;
import by.epamtc.lyskovkirill.restaurant.dao.parameter.ParameterMap;
import by.epamtc.lyskovkirill.restaurant.service.ReviewService;
import by.epamtc.lyskovkirill.restaurant.service.ServiceProvider;
import by.epamtc.lyskovkirill.restaurant.service.UserService;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * {@link ReviewService} interface implementation.
 *
 * @author k1ly
 */
public class ReviewServiceImpl implements ReviewService {

    @Override
    public boolean addReview(Review review) throws ServiceException {
        boolean isReviewCreated;
        DAOProvider daoProvider = DAOProvider.getInstance();
        ReviewDAO reviewDAO = daoProvider.getReviewRepository();
        try {
            isReviewCreated = reviewDAO.addReview(review);
        } catch (DAOException e) {
            throw new ServiceException("Review adding error", e);
        }
        return isReviewCreated;
    }

    @Override
    public List<Review> browseReviews(boolean asc, int count, int page) throws ServiceException {
        List<Review> reviewList;
        DAOProvider daoProvider = DAOProvider.getInstance();
        ReviewDAO reviewDAO = daoProvider.getReviewRepository();
        try {
            int firstRow = (page - 1) * count + 1;
            reviewList = reviewDAO.browseReviews(false, ReviewTable.DATE.getColumnName(), asc, firstRow, count);
            if (reviewList.size() > 0) {
                ServiceProvider serviceProvider = ServiceProvider.getInstance();
                UserService userService = serviceProvider.getUserService();
                for (Review review : reviewList) {
                    Optional<User> user = userService.findUserById(review.getUser().getId());
                    user.ifPresent(value -> review.getUser().setName(value.getName()));
                }
            }
        } catch (DAOException e) {
            throw new ServiceException("Obtaining reviews error", e);
        }
        return reviewList;
    }

    @Override
    public Optional<Review> findReviewById(int reviewId) throws ServiceException {
        Optional<Review> review;
        DAOProvider daoProvider = DAOProvider.getInstance();
        ReviewDAO reviewDAO = daoProvider.getReviewRepository();
        try {
            Map<String, Object> reviewIdParameter = new LinkedHashMap<>();
            reviewIdParameter.put(ReviewTable.ID.getColumnName(), reviewId);
            List<Review> reviewList = reviewDAO.findReviews(ParameterMap.of(reviewIdParameter));
            review = reviewList.size() == 1 ? Optional.of(reviewList.get(0)) : Optional.empty();
        } catch (DAOException e) {
            throw new ServiceException("Review searching error", e);
        }
        return review;
    }

    @Override
    public int countReviews() throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        ReviewDAO reviewDAO = daoProvider.getReviewRepository();
        try {
            return reviewDAO.countReviews(false);
        } catch (DAOException e) {
            throw new ServiceException("Review searching error", e);
        }
    }
}
