package by.epamtc.lyskovkirill.restaurant.controller.filter;

import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerConstants;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerURL;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet Filter implementation class.
 * It is required to prevent manual accessing to service pages.
 *
 * @author k1ly
 */
public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if (session.getAttribute(ControllerConstants.ACCESS_ALLOWED) == null) {
            response.sendRedirect(ControllerURL.CONTEXT_PATH);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
