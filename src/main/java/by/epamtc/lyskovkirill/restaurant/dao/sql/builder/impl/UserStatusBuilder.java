package by.epamtc.lyskovkirill.restaurant.dao.sql.builder.impl;

import by.epamtc.lyskovkirill.restaurant.bean.UserStatus;
import by.epamtc.lyskovkirill.restaurant.dao.sql.builder.Builder;
import by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable.UserStatusTable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * {@link Builder} implementation for {@link UserStatus} class.
 *
 * @author k1ly
 */
public class UserStatusBuilder implements Builder<UserStatus> {

    @Override
    public UserStatus build(ResultSet resultSet) throws SQLException {
        UserStatus userStatus = new UserStatus();
        userStatus.setId(resultSet.getInt(UserStatusTable.ID.getColumnName()));
        userStatus.setName(resultSet.getString(UserStatusTable.STATUS_NAME.getColumnName()));
        return userStatus;
    }
}
