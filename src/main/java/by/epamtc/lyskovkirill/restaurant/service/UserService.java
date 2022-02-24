package by.epamtc.lyskovkirill.restaurant.service;

import by.epamtc.lyskovkirill.restaurant.bean.User;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * Service interface that encapsulates {@link User} java bean class processing
 * via connecting Controller layer and DAO layer of the layered architecture.
 *
 * @author k1ly
 */
public interface UserService {

    /**
     * Validates the specified {@link User} and tries to register it using the DAO method.
     * Returns integer registration process status value.
     *
     * @param user the {@link User} which should be registered.
     * @return a negative integer if {@link User} is not valid, zero if {@link User} was not registered,
     * or a positive integer if {@link User} was successfully registered.
     * @throws ServiceException if an error occurred during user registration process.
     */
    int registerUser(User user) throws ServiceException;

    /**
     * Validates the specified {@link User} and tries to find it using the DAO method.
     * If such user exists, assigns its value to the parameter and compares its password hash.
     * If the passwords match, fills its fields and returns this {@link User}.
     *
     * @param user the {@link User} which should be found.
     * @return the {@link Optional#empty()} if user is not valid or does not exist,
     * or {@link User} entry if such user exists.
     * @throws ServiceException if an error occurred during user searching process.
     */
    Optional<User> signIn(User user) throws ServiceException;

    /**
     * Browses {@link User} entries using the DAO method, sorted by <b>orderAttr</b> and <b>asc</b> ascending flag.
     * Takes only <b>count</b> of specified <b>page</b>. Returns list of existing entries.
     *
     * @param orderAttr the name of sorting order attribute.
     * @param asc the boolean flag of order ascending.
     * @param count the integer value of entry count that should be taken.
     * @param page the integer page number value where to start counting from.
     * @return the {@link List} of existing {@link User} entries.
     * @throws ServiceException if an error occurred during users obtaining process.
     */
    List<User> browseUsers(String orderAttr, boolean asc, int count, int page) throws ServiceException;

    /**
     * Tries to find a {@link User} entry by the specified <b>id</b> using the DAO method.
     *
     * @param userId the user id.
     * @return the {@link Optional#empty()} if the user with this id was not found,
     * or {@link User} entry if such user exists.
     * @throws ServiceException if an error occurred during user searching process.
     */
    Optional<User> findUserById(int userId) throws ServiceException;

    /**
     * Tries to find a {@link User} entry by the specified <b>user login</b> using the DAO method.
     *
     * @param userLogin the user login.
     * @return the {@link Optional#empty()} if the user with this login was not found,
     * or {@link User} entry if such user exists.
     * @throws ServiceException if an error occurred during user searching process.
     */
    Optional<User> findUserByLogin(String userLogin) throws ServiceException;

    /**
     * Validates the specified {@link User} and tries to update it using the DAO method.
     * Returns integer updating process status value.
     *
     * @param user the {@link User} which should be updated by its <b>id</b>.
     * @return a negative integer if {@link User} is not valid, zero if {@link User} was not updated,
     * or a positive integer if {@link User} was successfully updated.
     * @throws ServiceException if an error occurred during user updating process.
     */
    int updateUser(User user) throws ServiceException;

    /**
     * Counts the total number of {@link User} entries using the DAO method.
     *
     * @return an integer value of users count.
     * @throws ServiceException if an error occurred during users counting process.
     */
    int countUsers() throws ServiceException;
}
