package by.epamtc.lyskovkirill.restaurant.controller.filter;

import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerConstants;
import by.epamtc.lyskovkirill.restaurant.controller.constant.LocaleEnum;
import by.epamtc.lyskovkirill.restaurant.util.controllerutil.CookieHandler;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet Filter implementation class.
 * It is required to track user localization with {@link LocaleEnum}
 * and set it in session and cookie.
 *
 * @author k1ly
 */
public class LocaleFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        Cookie langCookie = null;
        if (session.getAttribute(ControllerConstants.LANG) == null) {
            CookieHandler cookieHandler = CookieHandler.getInstance();
            langCookie = cookieHandler.findCookie(request.getCookies(), ControllerConstants.LANG);
        }
        if (langCookie != null) {
            session.setAttribute(ControllerConstants.LANG, langCookie.getValue());
        } else {
            String lang = request.getParameter(ControllerConstants.LANG);
            if (lang != null) {
                for (LocaleEnum locale : LocaleEnum.values()) {
                    if (locale.getLocale().getLanguage().equals(lang)) {
                        session.setAttribute(ControllerConstants.LANG, locale.getLocale().getLanguage());
                        HttpServletResponse response = (HttpServletResponse) servletResponse;
                        response.addCookie(new Cookie(ControllerConstants.LANG, locale.getLocale().getLanguage()));
                    }
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
