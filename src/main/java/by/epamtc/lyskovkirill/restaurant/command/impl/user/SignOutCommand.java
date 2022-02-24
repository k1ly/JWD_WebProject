package by.epamtc.lyskovkirill.restaurant.command.impl.user;

import by.epamtc.lyskovkirill.restaurant.bean.UserRole;
import by.epamtc.lyskovkirill.restaurant.bean.dto.UserDTO;
import by.epamtc.lyskovkirill.restaurant.command.Command;
import by.epamtc.lyskovkirill.restaurant.command.CommandResult;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerConstants;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerURL;
import by.epamtc.lyskovkirill.restaurant.util.controllerutil.CookieHandler;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * The implementation of the {@link Command} interface that is responsible
 * for user signing out process.
 *
 * @author k1ly
 */
public class SignOutCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult = new CommandResult(ControllerURL.CONTEXT_PATH);
        HttpSession session = request.getSession();

        CookieHandler cookieHandler = CookieHandler.getInstance();
        Cookie userCookie = cookieHandler.findCookie(request.getCookies(), ControllerConstants.USER);
        if (userCookie != null) {
            cookieHandler.clearCookie(userCookie);
            response.addCookie(userCookie);
        }
        UserDTO user = new UserDTO();
        user.setRoleName(UserRole.GUEST);
        session.setAttribute(ControllerConstants.USER, user);

        return commandResult;
    }
}
