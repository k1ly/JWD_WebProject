package by.epamtc.lyskovkirill.restaurant.controller.listener;

import by.epamtc.lyskovkirill.restaurant.util.connection.ConnectionPool;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

/**
 * Servlet Context Listener implementation class for {@link by.epamtc.lyskovkirill.restaurant.controller.Controller}.
 * <br>
 * It is required to initialize the {@link ConnectionPool} instance within {@link ControllerListener#contextInitialized(ServletContextEvent)}
 * and destroy it in {@link ControllerListener#contextDestroyed(ServletContextEvent)}.
 *
 * @author k1ly
 */
@WebListener
public class ControllerListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ConnectionPool.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool.getInstance().destroy();
    }
}
