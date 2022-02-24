package by.epamtc.lyskovkirill.restaurant.command.impl.user;

import by.epamtc.lyskovkirill.restaurant.bean.*;
import by.epamtc.lyskovkirill.restaurant.bean.dto.UserDTO;
import by.epamtc.lyskovkirill.restaurant.command.Command;
import by.epamtc.lyskovkirill.restaurant.command.CommandResult;
import by.epamtc.lyskovkirill.restaurant.command.constant.CommandConstants;
import by.epamtc.lyskovkirill.restaurant.command.exception.CommandException;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerConstants;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerURL;
import by.epamtc.lyskovkirill.restaurant.service.UserService;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;
import by.epamtc.lyskovkirill.restaurant.service.ServiceProvider;
import by.epamtc.lyskovkirill.restaurant.util.controllerutil.CartCookieHandler;
import by.epamtc.lyskovkirill.restaurant.util.controllerutil.CookieHandler;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Arrays;

/**
 * The implementation of the {@link Command} interface that is responsible
 * for {@link User} registration process.
 *
 * @author k1ly
 */
public class RegisterUserCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult = new CommandResult(ControllerURL.REGISTER_URL.getPath());
        HttpSession session = request.getSession();

        String referer = request.getParameter(CommandConstants.REFERER);
        String login = request.getParameter(CommandConstants.LOGIN);
        byte[] password = request.getParameter(CommandConstants.PASSWORD).getBytes();
        byte[] repeatPassword = request.getParameter(CommandConstants.REPEAT_PASSWORD).getBytes();
        String name = request.getParameter(CommandConstants.NAME);
        String email = request.getParameter(CommandConstants.EMAIL);
        String phone = request.getParameter(CommandConstants.PHONE);

        if (Arrays.equals(password, repeatPassword)) {
            ServiceProvider serviceProvider = ServiceProvider.getInstance();
            UserService userService = serviceProvider.getUserService();
            try {
                User user = new User();
                user.setLogin(login);
                user.setPassword(password);
                user.setName(name);
                user.setEmail(email);
                user.setPhone(phone);
                user.setRole(new UserRole(UserRole.CLIENT));
                user.setStatus(new UserStatus(UserStatus.ACTIVE));

                Order order = new Order();
                order.setCustomer(user);
                user.setCart(order);
                CookieHandler cookieHandler = CookieHandler.getInstance();
                Cookie cartCookie = cookieHandler.findCookie(request.getCookies(), ControllerConstants.CART);
                CartCookieHandler cartCookieHandler = CartCookieHandler.getInstance();
                order.setOrderItems(cartCookieHandler.parseCookie(cartCookie));

                if (userService.findUserByLogin(user.getLogin()).isEmpty()) {
                    int registerStatus = userService.registerUser(user);
                    if (registerStatus >= 0) {
                        if (registerStatus > 0) {
                            if (cartCookie != null) {
                                cookieHandler.clearCookie(cartCookie);
                                response.addCookie(cartCookie);
                            }
                            session.setAttribute(ControllerConstants.USER, UserDTO.convert(user));
                            commandResult.setPath(ControllerURL.CONTEXT_PATH + "/" + (referer != null ? referer : ""));
                        } else
                            session.setAttribute(ControllerConstants.ERROR_MESSAGE, "error.message.register");
                    } else
                        session.setAttribute(ControllerConstants.ERROR_MESSAGE, "error.message.try_again");
                } else
                    session.setAttribute(ControllerConstants.ERROR_MESSAGE, "error.message.user_exists");
            } catch (ServiceException e) {
                throw new CommandException("Error during registration", e);
            }
        } else
            session.setAttribute(ControllerConstants.ERROR_MESSAGE, "error.message.not_repeated");
        return commandResult;
    }
}