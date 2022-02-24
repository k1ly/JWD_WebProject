package by.epamtc.lyskovkirill.restaurant.controller.filter;

import by.epamtc.lyskovkirill.restaurant.bean.UserStatus;
import by.epamtc.lyskovkirill.restaurant.bean.dto.UserDTO;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerConstants;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerURL;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet Filter implementation class.
 * It is required to prevent access to URLs from users which have penalized status.
 *
 * @author k1ly
 */
public class UserStatusFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        Object userAttr = session.getAttribute(ControllerConstants.USER);
        if (userAttr == null || UserStatus.PENALIZED.equals(((UserDTO) session.getAttribute(ControllerConstants.USER)).getStatusName())) {
            response.sendRedirect(ControllerURL.CONTEXT_PATH);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
