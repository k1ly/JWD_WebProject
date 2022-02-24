package by.epamtc.lyskovkirill.restaurant.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet Filter implementation class.
 * It is required to set request and response character encoding using an initialization parameter.
 *
 * @author k1ly
 */
public class EncodingFilter implements Filter {
    private static final String ENCODING_INIT_PARAMETER = "encoding";
    private static final String HTML_CONTENT_TYPE = "text/html";
    private String code;

    public void init(FilterConfig config) {
        code = config.getInitParameter(ENCODING_INIT_PARAMETER);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (code != null && !code.equalsIgnoreCase(request.getCharacterEncoding())) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
            response.setContentType(HTML_CONTENT_TYPE);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        code = null;
    }
}
