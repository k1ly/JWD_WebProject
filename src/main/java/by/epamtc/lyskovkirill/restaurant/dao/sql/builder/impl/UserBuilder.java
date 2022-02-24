package by.epamtc.lyskovkirill.restaurant.dao.sql.builder.impl;

import by.epamtc.lyskovkirill.restaurant.bean.*;
import by.epamtc.lyskovkirill.restaurant.bean.contact.Address;
import by.epamtc.lyskovkirill.restaurant.dao.sql.builder.Builder;
import by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable.UserTable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * {@link Builder} implementation for {@link User} class.
 *
 * @author k1ly
 */
public class UserBuilder implements Builder<User> {

    @Override
    public User build(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(UserTable.ID.getColumnName()));
        user.setLogin(resultSet.getString(UserTable.LOGIN.getColumnName()));
        user.setPassword(resultSet.getBytes(UserTable.PASSWORD.getColumnName()));
        user.setName(resultSet.getString(UserTable.NAME.getColumnName()));
        user.setEmail(resultSet.getString(UserTable.EMAIL.getColumnName()));
        user.setPhone(resultSet.getString(UserTable.PHONE.getColumnName()));
        user.setCart(new Order(resultSet.getInt(UserTable.CART_ID.getColumnName())));
        user.setRole(new UserRole(resultSet.getInt(UserTable.USER_ROLE_ID.getColumnName())));
        user.setStatus(new UserStatus(resultSet.getInt(UserTable.USER_STATUS_ID.getColumnName())));
        return user;
    }
}
