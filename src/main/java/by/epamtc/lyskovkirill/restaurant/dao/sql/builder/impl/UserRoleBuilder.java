package by.epamtc.lyskovkirill.restaurant.dao.sql.builder.impl;

import by.epamtc.lyskovkirill.restaurant.bean.UserRole;
import by.epamtc.lyskovkirill.restaurant.dao.sql.builder.Builder;
import by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable.UserRoleTable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * {@link Builder} implementation for {@link UserRole} class.
 *
 * @author k1ly
 */
public class UserRoleBuilder implements Builder<UserRole> {

    @Override
    public UserRole build(ResultSet resultSet) throws SQLException {
        UserRole userRole = new UserRole();
        userRole.setId(resultSet.getInt(UserRoleTable.ID.getColumnName()));
        userRole.setName(resultSet.getString(UserRoleTable.ROLE_NAME.getColumnName()));
        return userRole;
    }
}
