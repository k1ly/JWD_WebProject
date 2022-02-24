package by.epamtc.lyskovkirill.restaurant.command.impl.admin;

import by.epamtc.lyskovkirill.restaurant.bean.*;
import by.epamtc.lyskovkirill.restaurant.command.Command;
import by.epamtc.lyskovkirill.restaurant.command.CommandResult;
import by.epamtc.lyskovkirill.restaurant.command.constant.CommandConstants;
import by.epamtc.lyskovkirill.restaurant.command.exception.CommandException;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerConstants;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerURL;
import by.epamtc.lyskovkirill.restaurant.service.ServiceProvider;
import by.epamtc.lyskovkirill.restaurant.service.UserService;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * The implementation of the {@link Command} interface that is responsible
 * for editing the {@link User} by admin.
 *
 * @author k1ly
 */
public class EditUserCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult = new CommandResult(ControllerURL.ADMINISTRATION_URL.getPath());
        HttpSession session = request.getSession();

        int userId = Integer.parseInt(request.getParameter(CommandConstants.USER_ID));
        Integer roleId = request.getParameter(CommandConstants.ROLE_ID) != null ?
                Integer.parseInt(request.getParameter(CommandConstants.ROLE_ID)) : null;
        Integer statusId = request.getParameter(CommandConstants.STATUS_ID) != null ?
                Integer.parseInt(request.getParameter(CommandConstants.STATUS_ID)) : null;

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        try {
            User newUser = new User();
            newUser.setId(userId);
            newUser.setRole(roleId != null ? new UserRole(roleId) : null);
            newUser.setStatus(statusId != null ? new UserStatus(statusId) : null);
            int updateStatus = userService.updateUser(newUser);
            if (updateStatus == 0)
                session.setAttribute(ControllerConstants.ERROR_MESSAGE, "error.message.edit_user");
            else if (updateStatus < 0)
                session.setAttribute(ControllerConstants.ERROR_MESSAGE, "error.message.invalid");
        } catch (ServiceException e) {
            throw new CommandException("Error during editing the user", e);
        }
        return commandResult;
    }
}