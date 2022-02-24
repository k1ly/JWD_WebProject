package by.epamtc.lyskovkirill.restaurant.service;

import by.epamtc.lyskovkirill.restaurant.bean.UserRole;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * Service interface that encapsulates {@link UserRole} java bean class processing
 * via connecting Controller layer and DAO layer of the layered architecture.
 *
 * @author k1ly
 */
public interface UserRoleService {

    /**
     * Tries to add the specified {@link UserRole} using the DAO method.
     * Returns boolean adding process status value.
     *
     * @param userRole the {@link UserRole} which should be added.
     * @return false if {@link UserRole} was not added,
     * or true if {@link UserRole} was successfully added.
     * @throws ServiceException if an error occurred during user role adding process.
     */
    boolean addUserRole(UserRole userRole) throws ServiceException;

    /**
     * Browses all {@link UserRole} entries using the DAO method. Returns list of existing entries.
     *
     * @return the {@link List} of existing {@link UserRole} entries.
     * @throws ServiceException if an error occurred during user roles obtaining process.
     */
    List<UserRole> browseUserRoles() throws ServiceException;

    /**
     * Tries to find a {@link UserRole} entry by the specified <b>id</b> using the DAO method.
     *
     * @param userRoleId the user role id.
     * @return the {@link Optional#empty()} if the user role with this id was not found,
     * or {@link UserRole} entry if such user role exists.
     * @throws ServiceException if an error occurred during user role searching process.
     */
    Optional<UserRole> findUserRoleById(int userRoleId) throws ServiceException;

    /**
     * Tries to find a {@link UserRole} entry by the specified <b>user role name</b> using the DAO method.
     *
     * @param userRoleName the user role name.
     * @return the {@link Optional#empty()} if the user role with this id was not found,
     * or {@link UserRole} entry if such user role exists.
     * @throws ServiceException if an error occurred during user role searching process.
     */
    Optional<UserRole> findUserRoleByName(String userRoleName) throws ServiceException;
}
