package by.epamtc.lyskovkirill.restaurant.command.impl.user;

import by.epamtc.lyskovkirill.restaurant.command.Command;
import by.epamtc.lyskovkirill.restaurant.command.CommandResult;
import by.epamtc.lyskovkirill.restaurant.controller.constant.Page;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * The implementation of the {@link Command} interface that is responsible
 * for redirecting to Penalized page.
 *
 * @author k1ly
 */
public class GoToPenalizedPage implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        return new CommandResult(Page.PENALIZED_PAGE.getPath());
    }
}