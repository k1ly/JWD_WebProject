package by.epamtc.lyskovkirill.restaurant.controller.filter;

import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerConstants;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * Servlet Filter implementation class.
 * It is required to set {@link by.epamtc.lyskovkirill.restaurant.command.Command} to the request depending on URL.
 *
 * @author k1ly
 */
public class CommandFilter implements Filter {
    private static final String GO_TO = "GO_TO_";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getParameter(ControllerConstants.COMMAND) == null) {
            String urlPath = request.getRequestURI().replace(request.getContextPath() + "/", "")
                    .replace("/", "_");
            request.setAttribute(ControllerConstants.COMMAND, GO_TO + urlPath);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
