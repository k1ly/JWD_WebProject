package by.epamtc.lyskovkirill.restaurant.command.impl.user;

import by.epamtc.lyskovkirill.restaurant.bean.*;
import by.epamtc.lyskovkirill.restaurant.command.Command;
import by.epamtc.lyskovkirill.restaurant.command.CommandResult;
import by.epamtc.lyskovkirill.restaurant.bean.dto.UserDTO;
import by.epamtc.lyskovkirill.restaurant.command.constant.CommandConstants;
import by.epamtc.lyskovkirill.restaurant.command.exception.CommandException;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerConstants;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerURL;
import by.epamtc.lyskovkirill.restaurant.service.UserService;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;
import by.epamtc.lyskovkirill.restaurant.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * The implementation of the {@link Command} interface that is responsible
 * for editing {@link User} settings.
 *
 * @author k1ly
 */
public class EditSettingsCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult = new CommandResult(ControllerURL.ACCOUNT_PROFILE_URL.getPath());

        byte[] password = request.getParameter(CommandConstants.PASSWORD) != null ?
                request.getParameter(CommandConstants.PASSWORD).getBytes() : null;
        String name = request.getParameter(CommandConstants.NAME);
        String email = request.getParameter(CommandConstants.EMAIL);
        String phone = request.getParameter(CommandConstants.PHONE);

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        try {
            HttpSession session = request.getSession();
            Object userAttr = session.getAttribute(ControllerConstants.USER);
            if (userAttr != null) {
                UserDTO user = (UserDTO) userAttr;
                User newUser = new User();
                newUser.setId(user.getId());
                newUser.setPassword(password);
                newUser.setName(name);
                newUser.setEmail(email);
                newUser.setPhone(phone);
                int updateStatus = userService.updateUser(newUser);
                if (updateStatus >= 0) {
                    if (updateStatus > 0) {
                        if (newUser.getName() != null) {
                            user.setName(newUser.getName());
                        }
                        session.setAttribute(ControllerConstants.USER, user);
                    }
                } else
                    session.setAttribute(ControllerConstants.ERROR_MESSAGE, "error.message.invalid");
            }
        } catch (ServiceException e) {
            throw new CommandException("Error during editing user settings", e);
        }
        return commandResult;
    }
}