package by.epamtc.lyskovkirill.restaurant.service.impl;

import by.epamtc.lyskovkirill.restaurant.bean.User;
import by.epamtc.lyskovkirill.restaurant.bean.UserRole;
import by.epamtc.lyskovkirill.restaurant.bean.UserStatus;
import by.epamtc.lyskovkirill.restaurant.dao.UserDAO;
import by.epamtc.lyskovkirill.restaurant.dao.exception.DAOException;
import by.epamtc.lyskovkirill.restaurant.dao.DAOProvider;
import by.epamtc.lyskovkirill.restaurant.dao.parameter.ParameterMap;
import by.epamtc.lyskovkirill.restaurant.dao.sql.dbtable.UserTable;
import by.epamtc.lyskovkirill.restaurant.service.*;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;
import by.epamtc.lyskovkirill.restaurant.service.ServiceProvider;
import by.epamtc.lyskovkirill.restaurant.util.hash.SHA256PasswordHash;
import by.epamtc.lyskovkirill.restaurant.util.validation.UserValidator;

import java.util.*;

/**
 * {@link UserService} interface implementation.
 *
 * @author k1ly
 */
public class UserServiceImpl implements UserService {

    @Override
    public int registerUser(User user) throws ServiceException {
        int status = -1;
        UserValidator userValidator = UserValidator.getInstance();
        if (userValidator.isUserValid(user)) {
            status = 0;
            ServiceProvider serviceProvider = ServiceProvider.getInstance();
            UserRoleService userRoleService = serviceProvider.getUserRoleService();
            UserStatusService userStatusService = serviceProvider.getUserStatusService();
            Optional<UserRole> userRole = userRoleService.findUserRoleByName(user.getRole().getName());
            Optional<UserStatus> userStatus = userStatusService.findUserStatusByName(user.getStatus().getName());
            if (userRole.isPresent() && userStatus.isPresent()) {
                user.setRole(userRole.get());
                user.setStatus(userStatus.get());
                DAOProvider daoProvider = DAOProvider.getInstance();
                UserDAO userDAO = daoProvider.getUserRepository();
                try {
                    SHA256PasswordHash sha256PasswordHash = SHA256PasswordHash.getInstance();
                    byte[] passwordHash = sha256PasswordHash.computeHash(user.getPassword());
                    user.setPassword(passwordHash);
                    status = userDAO.addUser(user) ? 1 : 0;
                } catch (DAOException e) {
                    throw new ServiceException("Registration error", e);
                }
            }
        }
        return status;
    }

    @Override
    public Optional<User> signIn(User user) throws ServiceException {
        Optional<User> loggedInUser = Optional.empty();
        UserValidator userValidator = UserValidator.getInstance();
        if (userValidator.isLoginValid(user.getLogin()) && userValidator.isPasswordValid(user.getPassword())) {
            loggedInUser = findUserByLogin(user.getLogin());
            if (loggedInUser.isPresent()) {
                SHA256PasswordHash sha256PasswordHash = SHA256PasswordHash.getInstance();
                byte[] passwordHash = sha256PasswordHash.computeHash(user.getPassword());
                if (Arrays.equals(passwordHash, loggedInUser.get().getPassword())) {
                    user = loggedInUser.get();
                    ServiceProvider serviceProvider = ServiceProvider.getInstance();
                    UserRoleService userRoleService = serviceProvider.getUserRoleService();
                    Optional<UserRole> userRole = userRoleService.findUserRoleById(user.getRole().getId());
                    if (userRole.isPresent())
                        loggedInUser.get().setRole(userRole.get());
                    UserStatusService userStatusService = serviceProvider.getUserStatusService();
                    Optional<UserStatus> userStatus = userStatusService.findUserStatusById(user.getStatus().getId());
                    if (userStatus.isPresent())
                        loggedInUser.get().setStatus(userStatus.get());
                }
            }
        }
        return loggedInUser;
    }

    @Override
    public List<User> browseUsers(String orderAttr, boolean asc, int count, int page) throws ServiceException {
        List<User> userList;
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserRepository();
        try {
            String userAttribute = null;
            for (var column : UserTable.values()) {
                if (column.getColumnName().equals(orderAttr)) {
                    userAttribute = column.getColumnName();
                    break;
                }
            }
            int firstRow = (page - 1) * count + 1;
            userList = userDAO.browseUsers(false, userAttribute, asc, firstRow, count);
        } catch (DAOException e) {
            throw new ServiceException("Obtaining users error", e);
        }
        return userList;
    }

    @Override
    public Optional<User> findUserById(int userId) throws ServiceException {
        Optional<User> user;
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserRepository();
        try {
            Map<String, Object> userIdParameter = new LinkedHashMap<>();
            userIdParameter.put(UserTable.ID.getColumnName(), userId);
            List<User> userList = userDAO.findUsers(ParameterMap.of(userIdParameter));
            user = userList.size() == 1 ? Optional.of(userList.get(0)) : Optional.empty();
        } catch (DAOException e) {
            throw new ServiceException("User searching error", e);
        }
        return user;
    }

    @Override
    public Optional<User> findUserByLogin(String userLogin) throws ServiceException {
        Optional<User> user;
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserRepository();
        try {
            Map<String, Object> userLoginParameter = new LinkedHashMap<>();
            userLoginParameter.put(UserTable.LOGIN.getColumnName(), userLogin);
            List<User> userList = userDAO.findUsers(ParameterMap.of(userLoginParameter));
            user = userList.size() == 1 ? Optional.of(userList.get(0)) : Optional.empty();
        } catch (DAOException e) {
            throw new ServiceException("User searching error", e);
        }
        return user;
    }

    @Override
    public int updateUser(User user) throws ServiceException {
        int status = -1;
        UserValidator userValidator = UserValidator.getInstance();
        if (userValidator.isUserValid(user)) {
            status = 0;
            if (user.getId() > 0) {
                DAOProvider daoProvider = DAOProvider.getInstance();
                UserDAO userDAO = daoProvider.getUserRepository();
                try {
                    Map<String, Object> userIdParameter = new LinkedHashMap<>();
                    userIdParameter.put(UserTable.ID.getColumnName(), user.getId());
                    if (user.getPassword() != null) {
                        SHA256PasswordHash sha256PasswordHash = SHA256PasswordHash.getInstance();
                        byte[] passwordHash = sha256PasswordHash.computeHash(user.getPassword());
                        user.setPassword(passwordHash);
                    }
                    if (user.getRole() != null && user.getRole().getId() > 0) {
                        ServiceProvider serviceProvider = ServiceProvider.getInstance();
                        UserRoleService userRoleService = serviceProvider.getUserRoleService();
                        Optional<UserRole> userRole = userRoleService.findUserRoleByName(user.getRole().getName());
                        userRole.ifPresent(user::setRole);
                    }
                    if (user.getStatus() != null && user.getStatus().getId() > 0) {
                        ServiceProvider serviceProvider = ServiceProvider.getInstance();
                        UserStatusService userStatusService = serviceProvider.getUserStatusService();
                        Optional<UserStatus> userStatus = userStatusService.findUserStatusByName(user.getStatus().getName());
                        userStatus.ifPresent(user::setStatus);
                    }
                    status = userDAO.updateUser(ParameterMap.of(userIdParameter), user) ? 1 : 0;
                } catch (DAOException e) {
                    throw new ServiceException("User updating error", e);
                }
            }
        }
        return status;
    }

    @Override
    public int countUsers() throws ServiceException {
        DAOProvider daoProvider = DAOProvider.getInstance();
        UserDAO userDAO = daoProvider.getUserRepository();
        try {
            return userDAO.countUsers(false);
        } catch (DAOException e) {
            throw new ServiceException("Dish counting error", e);
        }
    }
}