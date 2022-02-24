package by.epamtc.lyskovkirill.restaurant.command.impl.user;

import by.epamtc.lyskovkirill.restaurant.bean.*;
import by.epamtc.lyskovkirill.restaurant.command.Command;
import by.epamtc.lyskovkirill.restaurant.command.CommandResult;
import by.epamtc.lyskovkirill.restaurant.bean.dto.UserDTO;
import by.epamtc.lyskovkirill.restaurant.command.constant.CommandConstants;
import by.epamtc.lyskovkirill.restaurant.command.exception.CommandException;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerConstants;
import by.epamtc.lyskovkirill.restaurant.controller.constant.Page;
import by.epamtc.lyskovkirill.restaurant.service.UserService;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;
import by.epamtc.lyskovkirill.restaurant.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

/**
 * The implementation of the {@link Command} interface that is responsible
 * for redirecting to Account Profile page.
 *
 * @author k1ly
 */
public class GoToAccountProfilePage implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult = new CommandResult(Page.ACCOUNT_PROFILE_PAGE.getPath());
        HttpSession session = request.getSession();

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        try {
            User userInfo = new User();
            Object userAttr = session.getAttribute(ControllerConstants.USER);
            if (userAttr != null) {
                Optional<User> user = userService.findUserById(((UserDTO) userAttr).getId());
                if (user.isPresent()) {
                    userInfo.setLogin(user.get().getLogin());
                    userInfo.setName(user.get().getName());
                    userInfo.setEmail(user.get().getEmail());
                    userInfo.setPhone(user.get().getPhone());
                }
            }
            request.setAttribute(CommandConstants.USER_INFO, userInfo);
        } catch (ServiceException e) {
            throw new CommandException("Error during loading Account Profile page", e);
        }
        return commandResult;
    }
}