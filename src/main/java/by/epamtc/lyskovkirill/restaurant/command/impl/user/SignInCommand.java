package by.epamtc.lyskovkirill.restaurant.command.impl.user;

import by.epamtc.lyskovkirill.restaurant.bean.User;
import by.epamtc.lyskovkirill.restaurant.bean.UserStatus;
import by.epamtc.lyskovkirill.restaurant.command.Command;
import by.epamtc.lyskovkirill.restaurant.command.CommandResult;
import by.epamtc.lyskovkirill.restaurant.bean.dto.UserDTO;
import by.epamtc.lyskovkirill.restaurant.command.constant.CommandConstants;
import by.epamtc.lyskovkirill.restaurant.command.exception.CommandException;
import by.epamtc.lyskovkirill.restaurant.controller.Controller;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerConstants;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerURL;
import by.epamtc.lyskovkirill.restaurant.service.UserService;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;
import by.epamtc.lyskovkirill.restaurant.service.ServiceProvider;
import by.epamtc.lyskovkirill.restaurant.util.controllerutil.CookieHandler;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

/**
 * The implementation of the {@link Command} interface that is responsible
 * for user authentication process.
 *
 * @author k1ly
 */
public class SignInCommand implements Command {
    private static final int COOKIE_MAX_AGE = 60 * 60 * 24 * 30;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult = new CommandResult(ControllerURL.SIGN_IN_URL.getPath());
        HttpSession session = request.getSession();

        String login = request.getParameter(CommandConstants.LOGIN);
        String password = request.getParameter(CommandConstants.PASSWORD);
        String remember = request.getParameter(CommandConstants.REMEMBER);

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        UserService userService = serviceProvider.getUserService();
        try {
            User user = new User();
            user.setLogin(login);
            user.setPassword(password.getBytes());
            Optional<User> loggedInUser = userService.signIn(user);
            if (loggedInUser.isPresent()) {
                if (loggedInUser.get() != user) {
                    UserDTO userDTO = UserDTO.convert(loggedInUser.get());
                    session.setAttribute(ControllerConstants.USER, userDTO);
                    if (UserStatus.PENALIZED.equals(loggedInUser.get().getStatus().getName())) {
                        session.setAttribute(ControllerConstants.ACCESS_ALLOWED, true);
                        commandResult.setPath(ControllerURL.PENALIZED_URL.getPath());
                    } else
                        commandResult.setPath(ControllerURL.CONTEXT_PATH);
                    if (remember != null) {
                        CookieHandler cookieHandler = CookieHandler.getInstance();
                        Cookie userCookie = cookieHandler.createCookie(ControllerConstants.USER, userDTO);
                        userCookie.setMaxAge(COOKIE_MAX_AGE);
                        response.addCookie(userCookie);
                    }
                } else
                    session.setAttribute(ControllerConstants.ERROR_MESSAGE, "error.message.sign_in");
            } else
                session.setAttribute(ControllerConstants.ERROR_MESSAGE, "error.message.try_again");
        } catch (ServiceException e) {
            throw new CommandException("Error during authentication", e);
        }
        return commandResult;
    }
}