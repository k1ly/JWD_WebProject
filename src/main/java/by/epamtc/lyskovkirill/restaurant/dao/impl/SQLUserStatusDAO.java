package by.epamtc.lyskovkirill.restaurant.dao.impl;

import by.epamtc.lyskovkirill.restaurant.bean.UserStatus;
import by.epamtc.lyskovkirill.restaurant.dao.sql.AbstractSQLDAO;
import by.epamtc.lyskovkirill.restaurant.dao.UserStatusDAO;
import by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable.UserStatusTable;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;
import by.epamtc.lyskovkirill.restaurant.dao.parameter.ParameterMap;

import java.sql.SQLException;
import java.util.*;

/**
 * SQL {@link UserStatusDAO} interface implementation.
 *
 * @author k1ly
 */
public class SQLUserStatusDAO extends AbstractSQLDAO<UserStatus> implements UserStatusDAO {
    private static final String TABLE_NAME = "user_statuses";

    public SQLUserStatusDAO() {
        tableName = TABLE_NAME;
    }

    @Override
    public boolean addUserStatus(UserStatus userStatus) throws DAOException {
        boolean isUserStatusAdded = false;
        if (userStatus != null) {
            try {
                Integer generatedId = insertEntity(takeFields(userStatus));
                userStatus.setId(generatedId);
                isUserStatusAdded = true;
            } catch (SQLException exception) {
                throw new DAOException("User status inserting error", exception);
            }
        }
        return isUserStatusAdded;
    }

    @Override
    public List<UserStatus> findUserStatuses(ParameterMap parameters) throws DAOException {
        List<UserStatus> userStatusList = new ArrayList<>();
        try {
            String sql = makeQuery() + makeQueryCondition(parameters.getParameters(), true);
            for (Object item : executeQuery(UserStatus.class, sql)) {
                userStatusList.add((UserStatus) item);
            }
        } catch (SQLException exception) {
            throw new DAOException("User status selecting error", exception);
        }
        return userStatusList;
    }

    @Override
    public List<UserStatus> browseUserStatuses(boolean includeDeleted, String orderAttr, boolean asc) throws DAOException {
        List<UserStatus> userStatusList = new ArrayList<>();
        try {
            String sql = makeQuery() + makeQueryCondition(includeDeleted)
                    + makeOrderQuery(orderAttr, asc);
            for (Object item : executeQuery(UserStatus.class, sql)) {
                userStatusList.add((UserStatus) item);
            }
        } catch (SQLException exception) {
            throw new DAOException("User status selecting error", exception);
        }
        return userStatusList;
    }

    @Override
    public ParameterMap takeFields(UserStatus userStatus) {
        Map<String, Object> fields = new LinkedHashMap<>();
        if (userStatus.getName() != null)
            fields.put(UserStatusTable.STATUS_NAME.getColumnName(), userStatus.getName());
        return ParameterMap.of(fields);
    }

    @Override
    public String getColumns() {
        return UserStatusTable.ID.getColumnName() +
                ", " + UserStatusTable.STATUS_NAME.getColumnName();
    }
}
