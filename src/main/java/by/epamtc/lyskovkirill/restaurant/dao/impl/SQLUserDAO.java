package by.epamtc.lyskovkirill.restaurant.dao.impl;

import by.epamtc.lyskovkirill.restaurant.bean.User;
import by.epamtc.lyskovkirill.restaurant.dao.sql.AbstractSQLDAO;
import by.epamtc.lyskovkirill.restaurant.dao.DAOProvider;
import by.epamtc.lyskovkirill.restaurant.dao.UserDAO;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;
import by.epamtc.lyskovkirill.restaurant.dao.parameter.ParameterMap;
import by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable.UserTable;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * SQL {@link UserDAO} interface implementation.
 *
 * @author k1ly
 */
public class SQLUserDAO extends AbstractSQLDAO<User> implements UserDAO {
    private static final String TABLE_NAME = "users";

    public SQLUserDAO() {
        tableName = TABLE_NAME;
    }

    @Override
    public boolean addUser(User user) throws DAOException {
        boolean isUserAdded = false;
        if (user != null) {
            Connection connection = null;
            try {
                connection = startTransaction();
                Integer generatedId = insertEntity(takeFields(user), connection);
                user.setId(generatedId);
                DAOProvider daoProvider = DAOProvider.getInstance();
                SQLOrderDAO orderDAO = (SQLOrderDAO) daoProvider.getOrderRepository();
                connection = startTransaction();
                isUserAdded = orderDAO.addOrder(user.getCart(), connection);
                commitTransaction(connection);
            } catch (SQLException | DAOException exception) {
                rollbackTransaction(connection);
                throw new DAOException("User inserting error", exception);
            } finally {
                finishTransaction(connection);
            }
        }
        return isUserAdded;
    }

    @Override
    public List<User> findUsers(ParameterMap parameters) throws DAOException {
        List<User> userList = new ArrayList<>();
        try {
            String sql = makeQuery() + makeQueryCondition(parameters.getParameters(), true);
            for (Object item : executeQuery(User.class, sql)) {
                userList.add((User) item);
            }
        } catch (SQLException exception) {
            throw new DAOException("User selecting error", exception);
        }
        return userList;
    }

    @Override
    public List<User> browseUsers(boolean includeDeleted, String orderAttr, boolean asc, int firstRow, int rowCount) throws DAOException {
        List<User> userList = new ArrayList<>();
        try {
            String sql = makeQuery() + makeQueryCondition(includeDeleted)
                    + makeOrderQuery(orderAttr, asc);
            for (Object item : executeQuery(User.class, makePaginationQuery(sql, firstRow, rowCount))) {
                userList.add((User) item);
            }
        } catch (SQLException exception) {
            throw new DAOException("User selecting error", exception);
        }
        return userList;
    }

    @Override
    public boolean updateUser(ParameterMap parameters, User user) throws DAOException {
        boolean isUserUpdated = false;
        if (user != null) {
            try {
                isUserUpdated = updateEntity(takeFields(user), parameters) == 1;
            } catch (SQLException exception) {
                throw new DAOException("User updating error", exception);
            }
        }
        return isUserUpdated;
    }

    @Override
    public int countUsers(boolean includeDeleted) throws DAOException {
        try {
            return countTableRows(makeQueryCondition(includeDeleted));
        } catch (SQLException exception) {
            throw new DAOException("User counting error", exception);
        }
    }

    @Override
    public ParameterMap takeFields(User user) {
        Map<String, Object> fields = new LinkedHashMap<>();
        if (user.getLogin() != null)
            fields.put(UserTable.LOGIN.getColumnName(), user.getLogin());
        if (user.getPassword() != null)
            fields.put(UserTable.PASSWORD.getColumnName(), user.getPassword());
        if (user.getName() != null)
            fields.put(UserTable.NAME.getColumnName(), user.getName());
        if (user.getEmail() != null)
            fields.put(UserTable.EMAIL.getColumnName(), user.getEmail());
        if (user.getPhone() != null)
            fields.put(UserTable.PHONE.getColumnName(), user.getPhone());
        if (user.getCart() != null && user.getCart().getId() > 0)
            fields.put(UserTable.CART_ID.getColumnName(), user.getCart().getId());
        if (user.getRole() != null && user.getRole().getId() > 0)
            fields.put(UserTable.USER_ROLE_ID.getColumnName(), user.getRole().getId());
        if (user.getStatus() != null && user.getStatus().getId() > 0)
            fields.put(UserTable.USER_STATUS_ID.getColumnName(), user.getStatus().getId());
        return ParameterMap.of(fields);
    }

    @Override
    public String getColumns() {
        return UserTable.ID.getColumnName()
                + ", " + UserTable.LOGIN.getColumnName()
                + ", " + UserTable.PASSWORD.getColumnName()
                + ", " + UserTable.NAME.getColumnName()
                + ", " + UserTable.EMAIL.getColumnName()
                + ", " + UserTable.PHONE.getColumnName()
                + ", " + UserTable.CART_ID.getColumnName()
                + ", " + UserTable.USER_ROLE_ID.getColumnName()
                + ", " + UserTable.USER_STATUS_ID.getColumnName();
    }
}
