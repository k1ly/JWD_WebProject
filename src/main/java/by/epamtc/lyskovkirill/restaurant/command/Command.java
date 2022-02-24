package by.epamtc.lyskovkirill.restaurant.command;

import by.epamtc.lyskovkirill.restaurant.command.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Interface that is used to implement Command Pattern,
 * which has one method {@link Command#execute(HttpServletRequest, HttpServletResponse)}
 *
 * @author k1ly
 */
public interface Command {

    /**
     * Main method of the Command interface that is used to execute command using
     * request and response and put the result in a {@link CommandResult} object.
     *
     * @param request the {@link HttpServletRequest}
     * @param response the {@link HttpServletResponse}
     * @return the {@link CommandResult} which includes page path or response data map.
     * @throws CommandException command can throw when it encounters difficulty
     */
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}
