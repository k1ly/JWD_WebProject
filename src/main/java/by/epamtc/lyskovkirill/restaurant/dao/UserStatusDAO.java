package by.epamtc.lyskovkirill.restaurant.dao;

import by.epamtc.lyskovkirill.restaurant.bean.UserStatus;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;
import by.epamtc.lyskovkirill.restaurant.dao.parameter.ParameterMap;

import java.util.List;

/**
 * DAO interface that encapsulates {@link UserStatus} java bean class processing using data source.
 *
 * @author k1ly
 */
public interface UserStatusDAO {

    /**
     * Tries to add the specified {@link UserStatus} into data source.
     * Returns boolean adding process status value.
     *
     * @param userStatus the {@link UserStatus} which should be added.
     * @return false if {@link UserStatus} was not added,
     * or true if {@link UserStatus} was successfully added.
     * @throws DAOException if an error occurred during adding the user status.
     */
    boolean addUserStatus(UserStatus userStatus) throws DAOException;

    /**
     * Tries to find the {@link List} of {@link UserStatus} entries from data source using {@link ParameterMap} as condition.
     * Returns list of existing entries.
     *
     * @param parameters the {@link ParameterMap} that is used for search condition.
     * @return the {@link List} of existing {@link UserStatus} entries.
     * @throws DAOException if an error occurred during retrieving user statuses.
     */
    List<UserStatus> findUserStatuses(ParameterMap parameters) throws DAOException;

    /**
     * Retrieves the {@link List} of {@link UserStatus} entries from data source depending on <b>includeDeleted</b> flag,
     * sorted by <b>orderAttr</b> and <b>asc</b> ascending flag.
     * Returns list of existing entries.
     *
     * @param includeDeleted the boolean flag of including deleted entries.
     * @param orderAttr the name of sorting order attribute.
     * @param asc the boolean flag of order ascending.
     * @return the {@link List} of existing {@link UserStatus} entries.
     * @throws DAOException if an error occurred during retrieving user statuses.
     */
    List<UserStatus> browseUserStatuses(boolean includeDeleted, String orderAttr, boolean asc) throws DAOException;
}
