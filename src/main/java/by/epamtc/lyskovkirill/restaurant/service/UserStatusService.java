package by.epamtc.lyskovkirill.restaurant.service;

import by.epamtc.lyskovkirill.restaurant.bean.UserStatus;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * Service interface that encapsulates {@link UserStatus} java bean class processing
 * via connecting Controller layer and DAO layer of the layered architecture.
 *
 * @author k1ly
 */
public interface UserStatusService {

    /**
     * Tries to add the specified {@link UserStatus} using the DAO method.
     * Returns boolean adding process status value.
     *
     * @param userStatus the {@link UserStatus} which should be added.
     * @return false if {@link UserStatus} was not added,
     * or true if {@link UserStatus} was successfully added.
     * @throws ServiceException if an error occurred during user status adding process.
     */
    boolean addUserStatus(UserStatus userStatus) throws ServiceException;

    /**
     * Browses all {@link UserStatus} entries using the DAO method. Returns list of existing entries.
     *
     * @return the {@link List} of existing {@link UserStatus} entries.
     * @throws ServiceException if an error occurred during user statuses obtaining process.
     */
    List<UserStatus> browseUserStatuses() throws ServiceException;

    /**
     * Tries to find a {@link UserStatus} entry by the specified <b>id</b> using the DAO method.
     *
     * @param userStatusId the user status id.
     * @return the {@link Optional#empty()} if the user status with this id was not found,
     * or {@link UserStatus} entry if such user status exists.
     * @throws ServiceException if an error occurred during user status searching process.
     */
    Optional<UserStatus> findUserStatusById(int userStatusId) throws ServiceException;

    /**
     * Tries to find a {@link UserStatus} entry by the specified <b>user status name</b> using the DAO method.
     *
     * @param userStatusName the user status name.
     * @return the {@link Optional#empty()} if the user status with this id was not found,
     * or {@link UserStatus} entry if such user status exists.
     * @throws ServiceException if an error occurred during user status searching process.
     */
    Optional<UserStatus> findUserStatusByName(String userStatusName) throws ServiceException;
}
