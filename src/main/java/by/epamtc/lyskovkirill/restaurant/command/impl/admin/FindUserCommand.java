package by.epamtc.lyskovkirill.restaurant.command.impl.admin;

import by.epamtc.lyskovkirill.restaurant.bean.User;
import by.epamtc.lyskovkirill.restaurant.bean.UserRole;
import by.epamtc.lyskovkirill.restaurant.bean.UserStatus;
import by.epamtc.lyskovkirill.restaurant.command.Command;
import by.epamtc.lyskovkirill.restaurant.command.CommandResult;
import by.epamtc.lyskovkirill.restaurant.command.constant.CommandConstants;
import by.epamtc.lyskovkirill.restaurant.command.exception.CommandException;
import by.epamtc.lyskovkirill.restaurant.service.UserRoleService;
import by.epamtc.lyskovkirill.restaurant.service.UserService;
import by.epamtc.lyskovkirill.restaurant.service.UserStatusService;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;
import by.epamtc.lyskovkirill.restaurant.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The implementation of the {@link Command} interface that is responsible
 * for searching the {@link User} by admin.
 *
 * @author k1ly
 */
public class FindUserCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult;

        Integer userId = request.getParameter(CommandConstants.USER_ID) != null ?
                Integer.parseInt(request.getParameter(CommandConstants.USER_ID)) : null;

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        UserRoleService userRoleService = serviceProvider.getUserRoleService();
        UserStatusService userStatusService = serviceProvider.getUserStatusService();
        try {
            Map<String, Object> responseData = new LinkedHashMap<>();
            if (userId != null) {
                Optional<User> user = userService.findUserById(userId);
                responseData.put(CommandConstants.USER, user.orElse(null));
            }
            List<UserRole> roleList = userRoleService.browseUserRoles();
            responseData.put(CommandConstants.ROLE_LIST_ATTRIBUTE, roleList);
            List<UserStatus> statusList = userStatusService.browseUserStatuses();
            responseData.put(CommandConstants.STATUS_LIST_ATTRIBUTE, statusList);
            commandResult = new CommandResult(responseData);
        } catch (ServiceException e) {
            throw new CommandException("Error during searching the user", e);
        }
        return commandResult;
    }
}