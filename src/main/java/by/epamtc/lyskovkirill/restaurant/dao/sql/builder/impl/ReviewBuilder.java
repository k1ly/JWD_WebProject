package by.epamtc.lyskovkirill.restaurant.dao.sql.builder.impl;

import by.epamtc.lyskovkirill.restaurant.bean.Review;
import by.epamtc.lyskovkirill.restaurant.bean.User;
import by.epamtc.lyskovkirill.restaurant.dao.sql.builder.Builder;
import by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable.ReviewTable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * {@link Builder} implementation for {@link Review} class.
 *
 * @author k1ly
 */
public class ReviewBuilder implements Builder<Review> {

    @Override
    public Review build(ResultSet resultSet) throws SQLException {
        Review review = new Review();
        review.setId(resultSet.getInt(ReviewTable.ID.getColumnName()));
        review.setComment(resultSet.getString(ReviewTable.COMMENT.getColumnName()));
        review.setGrade(resultSet.getInt(ReviewTable.GRADE.getColumnName()));
        review.setDate(resultSet.getTimestamp(ReviewTable.DATE.getColumnName()));
        review.setUser(new User(resultSet.getInt(ReviewTable.USER_ID.getColumnName())));
        return review;
    }
}
