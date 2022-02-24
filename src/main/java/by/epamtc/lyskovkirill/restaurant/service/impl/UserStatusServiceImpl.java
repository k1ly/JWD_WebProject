package by.epamtc.lyskovkirill.restaurant.service.impl;

import by.epamtc.lyskovkirill.restaurant.bean.UserStatus;
import by.epamtc.lyskovkirill.restaurant.dao.UserStatusDAO;
import by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable.UserStatusTable;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;
import by.epamtc.lyskovkirill.restaurant.dao.DAOProvider;
import by.epamtc.lyskovkirill.restaurant.dao.parameter.ParameterMap;
import by.epamtc.lyskovkirill.restaurant.service.UserStatusService;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * {@link UserStatusService} interface implementation.
 *
 * @author k1ly
 */
public class UserStatusServiceImpl implements UserStatusService {

    @Override
    public boolean addUserStatus(UserStatus userStatus) throws ServiceException {
        boolean isUserStatusAdded;
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserStatusDAO userStatusDAO = daoProvider.getUserStatusRepository();
        try {
            isUserStatusAdded = userStatusDAO.addUserStatus(userStatus);
        } catch (DAOException e) {
            throw new ServiceException("User status adding error", e);
        }
        return isUserStatusAdded;
    }

    @Override
    public List<UserStatus> browseUserStatuses() throws ServiceException {
        List<UserStatus> userStatusList;
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserStatusDAO userStatusDAO = daoProvider.getUserStatusRepository();
        try {
            userStatusList = userStatusDAO.browseUserStatuses(false, UserStatusTable.ID.getColumnName(), true);
        } catch (DAOException e) {
            throw new ServiceException("Obtaining user statuses error", e);
        }
        return userStatusList;
    }

    @Override
    public Optional<UserStatus> findUserStatusById(int userStatusId) throws ServiceException {
        Optional<UserStatus> userStatus;
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserStatusDAO userStatusDAO = daoProvider.getUserStatusRepository();
        try {
            Map<String, Object> userStatusIdParameter = new LinkedHashMap<>();
            userStatusIdParameter.put(UserStatusTable.ID.getColumnName(), userStatusId);
            List<UserStatus> userStatusList = userStatusDAO.findUserStatuses(ParameterMap.of(userStatusIdParameter));
            userStatus = userStatusList.size() == 1 ? Optional.of(userStatusList.get(0)) : Optional.empty();
        } catch (DAOException e) {
            throw new ServiceException("User status searching error", e);
        }
        return userStatus;
    }

    @Override
    public Optional<UserStatus> findUserStatusByName(String userStatusName) throws ServiceException {
        Optional<UserStatus> userStatus;
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserStatusDAO userStatusDAO = daoProvider.getUserStatusRepository();
        try {
            Map<String, Object> userStatusNameParameter = new LinkedHashMap<>();
            userStatusNameParameter.put(UserStatusTable.STATUS_NAME.getColumnName(), userStatusName);
            List<UserStatus> userStatusList = userStatusDAO.findUserStatuses(ParameterMap.of(userStatusNameParameter));
            userStatus = userStatusList.size() == 1 ? Optional.of(userStatusList.get(0)) : Optional.empty();
        } catch (DAOException e) {
            throw new ServiceException("User status searching error", e);
        }
        return userStatus;
    }
}
