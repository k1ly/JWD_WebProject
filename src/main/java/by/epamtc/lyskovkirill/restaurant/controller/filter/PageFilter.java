package by.epamtc.lyskovkirill.restaurant.controller.filter;

import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerURL;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet Filter implementation class.
 * It is required to prevent direct accessing to jsp pages.
 *
 * @author k1ly
 */
public class PageFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.sendRedirect(ControllerURL.CONTEXT_PATH);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
