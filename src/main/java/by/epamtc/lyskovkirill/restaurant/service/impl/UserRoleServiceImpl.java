package by.epamtc.lyskovkirill.restaurant.service.impl;

import by.epamtc.lyskovkirill.restaurant.bean.UserRole;
import by.epamtc.lyskovkirill.restaurant.dao.UserRoleDAO;
import by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable.UserRoleTable;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;
import by.epamtc.lyskovkirill.restaurant.dao.DAOProvider;
import by.epamtc.lyskovkirill.restaurant.dao.parameter.ParameterMap;
import by.epamtc.lyskovkirill.restaurant.service.UserRoleService;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * {@link UserRoleService} interface implementation.
 *
 * @author k1ly
 */
public class UserRoleServiceImpl implements UserRoleService {

    @Override
    public boolean addUserRole(UserRole userRole) throws ServiceException {
        boolean isUserRoleAdded;
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserRoleDAO userRoleDAO = daoProvider.getUserRoleRepository();
        try {
            isUserRoleAdded = userRoleDAO.addUserRole(userRole);
        } catch (DAOException e) {
            throw new ServiceException("User role adding error", e);
        }
        return isUserRoleAdded;
    }

    @Override
    public List<UserRole> browseUserRoles() throws ServiceException {
        List<UserRole> userRoleList;
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserRoleDAO userRoleDAO = daoProvider.getUserRoleRepository();
        try {
            userRoleList = userRoleDAO.browseUserRoles(false, UserRoleTable.ID.getColumnName(), true);
        } catch (DAOException e) {
            throw new ServiceException("Obtaining user roles error", e);
        }
        return userRoleList;
    }

    @Override
    public Optional<UserRole> findUserRoleById(int userRoleId) throws ServiceException {
        Optional<UserRole> userRole;
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserRoleDAO userRoleDAO = daoProvider.getUserRoleRepository();
        try {
            Map<String, Object> userRoleIdParameter = new LinkedHashMap<>();
            userRoleIdParameter.put(UserRoleTable.ID.getColumnName(), userRoleId);
            List<UserRole> userRoleList = userRoleDAO.findUserRoles(ParameterMap.of(userRoleIdParameter));
            userRole = userRoleList.size() == 1 ? Optional.of(userRoleList.get(0)) : Optional.empty();
        } catch (DAOException e) {
            throw new ServiceException("User role searching error", e);
        }
        return userRole;
    }

    @Override
    public Optional<UserRole> findUserRoleByName(String userRoleName) throws ServiceException {
        Optional<UserRole> userRole;
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserRoleDAO userRoleDAO = daoProvider.getUserRoleRepository();
        try {
            Map<String, Object> userRoleNameParameter = new LinkedHashMap<>();
            userRoleNameParameter.put(UserRoleTable.ROLE_NAME.getColumnName(), userRoleName);
            List<UserRole> userRoleList = userRoleDAO.findUserRoles(ParameterMap.of(userRoleNameParameter));
            userRole = userRoleList.size() == 1 ? Optional.of(userRoleList.get(0)) : Optional.empty();
        } catch (DAOException e) {
            throw new ServiceException("User role searching error", e);
        }
        return userRole;
    }
}
