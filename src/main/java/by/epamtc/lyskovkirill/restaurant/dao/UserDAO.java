package by.epamtc.lyskovkirill.restaurant.dao;

import by.epamtc.lyskovkirill.restaurant.bean.User;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;
import by.epamtc.lyskovkirill.restaurant.dao.parameter.ParameterMap;

import java.util.List;

/**
 * DAO interface that encapsulates {@link User} java bean class processing using data source.
 *
 * @author k1ly
 */
public interface UserDAO {

    /**
     * Tries to add the specified {@link User} into data source.
     * Returns boolean adding process status value.
     *
     * @param user the {@link User} which should be added.
     * @return false if {@link User} was not added,
     * or true if {@link User} was successfully added.
     * @throws DAOException if an error occurred during adding the user.
     */
    boolean addUser(User user) throws DAOException;

    /**
     * Tries to find the {@link List} of {@link User} entries from data source using {@link ParameterMap} as condition.
     * Returns list of existing entries.
     *
     * @param parameters the {@link ParameterMap} that is used for search condition.
     * @return the {@link List} of existing {@link User} entries.
     * @throws DAOException if an error occurred during retrieving users.
     */
    List<User> findUsers(ParameterMap parameters) throws DAOException;

    /**
     * Retrieves the {@link List} of {@link User} entries from data source depending on <b>includeDeleted</b> flag,
     * sorted by <b>orderAttr</b> and <b>asc</b> ascending flag.
     * Takes only <b>rowCount</b> starting from <b>firstRow</b>. Returns list of existing entries.
     *
     * @param includeDeleted the boolean flag of including deleted entries.
     * @param orderAttr the name of sorting order attribute.
     * @param asc the boolean flag of order ascending.
     * @param firstRow the integer number of a row where to start counting from.
     * @param rowCount the integer row count of entries that should be taken.
     * @return the {@link List} of existing {@link User} entries.
     * @throws DAOException if an error occurred during retrieving users.
     */
    List<User> browseUsers(boolean includeDeleted, String orderAttr, boolean asc, int firstRow, int rowCount) throws DAOException;

    /**
     * Tries to find user entry in data source using {@link ParameterMap} as condition and update it with new {@link User} parameter.
     * Returns boolean updating process status value.
     *
     * @param parameters the {@link ParameterMap} that is used for search condition.
     * @param user the {@link User} whose fields are to be used as a replacement.
     * @return false if {@link User} was not updated,
     * or true if {@link User} was successfully updated.
     * @throws DAOException if an error occurred during updating the user.
     */
    boolean updateUser(ParameterMap parameters, User user) throws DAOException;

    /**
     * Counts the total number of {@link User} entries in data source depending on <b>includeDeleted</b> flag.
     *
     * @param includeDeleted the boolean flag of including deleted entries.
     * @return an integer value of users count.
     * @throws DAOException if an error occurred during counting users counting.
     */
    int countUsers(boolean includeDeleted) throws DAOException;
}
