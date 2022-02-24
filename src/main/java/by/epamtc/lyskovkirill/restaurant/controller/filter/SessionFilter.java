package by.epamtc.lyskovkirill.restaurant.controller.filter;

import by.epamtc.lyskovkirill.restaurant.bean.User;
import by.epamtc.lyskovkirill.restaurant.bean.UserRole;
import by.epamtc.lyskovkirill.restaurant.bean.dto.UserDTO;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerConstants;
import by.epamtc.lyskovkirill.restaurant.util.controllerutil.CookieHandler;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet Filter implementation class.
 * It is required to refresh session max inactive interval
 * and track user role by setting it to session.
 *
 * @author k1ly
 */
public class SessionFilter implements Filter {
    private static final int MAX_INACTIVE_INTERVAL = 60 * 10;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(MAX_INACTIVE_INTERVAL);
        if (session.getAttribute(ControllerConstants.USER) == null) {
            CookieHandler cookieHandler = CookieHandler.getInstance();
            Cookie userCookie = cookieHandler.findCookie(request.getCookies(), ControllerConstants.USER);
            if (userCookie != null) {
                session.setAttribute(ControllerConstants.USER, cookieHandler.parseCookie(userCookie, new TypeToken<UserDTO>() {
                }));
            } else {
                User user = new User();
                user.setRole(new UserRole(UserRole.GUEST));
                session.setAttribute(ControllerConstants.USER, UserDTO.convert(user));
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
