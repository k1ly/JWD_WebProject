package by.epamtc.lyskovkirill.restaurant.dao.impl;

import by.epamtc.lyskovkirill.restaurant.bean.Review;
import by.epamtc.lyskovkirill.restaurant.dao.sql.AbstractSQLDAO;
import by.epamtc.lyskovkirill.restaurant.dao.ReviewDAO;
import by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable.ReviewTable;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;
import by.epamtc.lyskovkirill.restaurant.dao.parameter.ParameterMap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * SQL {@link ReviewDAO} interface implementation.
 *
 * @author k1ly
 */
public class SQLReviewDAO extends AbstractSQLDAO<Review> implements ReviewDAO {
    private static final String TABLE_NAME = "reviews";

    public SQLReviewDAO() {
        tableName = TABLE_NAME;
    }

    @Override
    public boolean addReview(Review review) throws DAOException {
        boolean isReviewAdded = false;
        if (review != null) {
            try {
                Integer generatedId = insertEntity(takeFields(review));
                review.setId(generatedId);
                isReviewAdded = true;
            } catch (SQLException exception) {
                throw new DAOException("Review inserting error", exception);
            }
        }
        return isReviewAdded;
    }

    @Override
    public List<Review> findReviews(ParameterMap parameters) throws DAOException {
        List<Review> reviewList = new ArrayList<>();
        try {
            String sql = makeQuery() + makeQueryCondition(parameters.getParameters(), true);
            for (Object item : executeQuery(Review.class, sql)) {
                reviewList.add((Review) item);
            }
        } catch (SQLException exception) {
            throw new DAOException("Review selecting error", exception);
        }
        return reviewList;
    }

    @Override
    public List<Review> browseReviews(boolean includeDeleted, String orderAttr, boolean asc, int firstRow, int rowCount) throws DAOException {
        List<Review> reviewList = new ArrayList<>();
        try {
            String sql = makeQuery() + makeQueryCondition(includeDeleted)
                    + makeOrderQuery(orderAttr, asc);
            for (Object item : executeQuery(Review.class, makePaginationQuery(sql, firstRow, rowCount))) {
                reviewList.add((Review) item);
            }
        } catch (SQLException exception) {
            throw new DAOException("Review selecting error", exception);
        }
        return reviewList;
    }

    @Override
    public int countReviews(boolean includeDeleted) throws DAOException {
        try {
            return countTableRows(makeQueryCondition(includeDeleted));
        } catch (SQLException exception) {
            throw new DAOException("Review counting error", exception);
        }
    }

    @Override
    public ParameterMap takeFields(Review review) {
        Map<String, Object> fields = new LinkedHashMap<>();
        if (review.getComment() != null)
            fields.put(ReviewTable.COMMENT.getColumnName(), review.getComment());
        if (review.getGrade() != null)
            fields.put(ReviewTable.GRADE.getColumnName(), review.getGrade());
        if (review.getDate() != null)
            fields.put(ReviewTable.DATE.getColumnName(), review.getDate());
        if (review.getUser() != null && review.getUser().getId() > 0)
            fields.put(ReviewTable.USER_ID.getColumnName(), review.getUser().getId());
        return ParameterMap.of(fields);
    }

    @Override
    public String getColumns() {
        return ReviewTable.ID.getColumnName() +
                ", " + ReviewTable.COMMENT.getColumnName() +
                ", " + ReviewTable.GRADE.getColumnName() +
                ", " + ReviewTable.DATE.getColumnName() +
                ", " + ReviewTable.USER_ID.getColumnName();
    }
}
