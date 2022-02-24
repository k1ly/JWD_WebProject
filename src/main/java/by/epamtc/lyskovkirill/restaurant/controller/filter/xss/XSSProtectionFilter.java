package by.epamtc.lyskovkirill.restaurant.controller.filter.xss;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * Servlet Filter implementation class.
 * It is required to prevent XSS attacks.
 *
 * @author k1ly
 */
public class XSSProtectionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(new XSSRequestWrapper((HttpServletRequest) servletRequest), servletResponse);
    }
}

