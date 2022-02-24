package by.epamtc.lyskovkirill.restaurant.controller;

import by.epamtc.lyskovkirill.restaurant.command.Command;
import by.epamtc.lyskovkirill.restaurant.command.CommandProvider;
import by.epamtc.lyskovkirill.restaurant.command.CommandResult;
import by.epamtc.lyskovkirill.restaurant.command.exception.CommandException;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerConstants;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Servlet Class that is required to directly process requests from the client and return the result.
 * <br>
 * Request processing passes in the method
 * {@link Controller#processRequest(HttpServletRequest, HttpServletResponse)}
 *
 * @author k1ly
 */
@MultipartConfig
public final class Controller extends HttpServlet {
    private static final String AJAX_HEADER = "X-Requested-With";
    private static final String AJAX_HEADER_VALUE = "XMLHttpRequest";
    private static final String METHOD_POST = "POST";

    private static final Logger logger = LogManager.getLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Receives a request and a response. Fetches command name from the request
     * and via {@link CommandProvider} invokes necessary {@link Command}.
     * After {@link CommandResult} is returned, depending on which method was used,
     * request can be forwarded or sending redirect can be used.
     * <br>
     * Also, method will write a response if request was an AJAX.
     *
     * @param request the {@link HttpServletRequest}
     * @param response the {@link HttpServletResponse}
     * @throws ServletException servlet can throw when it encounters difficulty
     * @throws IOException if an input or output error is detected when the servlet handles the request
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(ControllerConstants.COMMAND) != null ?
                request.getParameter(ControllerConstants.COMMAND) : request.getAttribute(ControllerConstants.COMMAND).toString();
        CommandProvider provider = CommandProvider.getInstance();
        Command executionCommand = provider.getCommand(commandName);

        CommandResult commandResult;
        try {
            commandResult = executionCommand.execute(request, response);
        } catch (CommandException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }

        if (AJAX_HEADER_VALUE.equals(request.getHeader(AJAX_HEADER))) {
            if (commandResult.getResponseData() != null) {
                String json = new Gson().toJson(commandResult.getResponseData());
                response.getWriter().write(json);
            }
        } else {
            String page = commandResult.getPath();
            if (METHOD_POST.equals(request.getMethod()))
                response.sendRedirect(page);
            else {
                ServletContext servletContext = request.getServletContext();
                RequestDispatcher dispatcher = servletContext.getRequestDispatcher(page);
                dispatcher.forward(request, response);
            }
        }
    }
}