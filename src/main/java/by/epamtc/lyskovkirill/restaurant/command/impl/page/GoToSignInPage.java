package by.epamtc.lyskovkirill.restaurant.command.impl.page;

import by.epamtc.lyskovkirill.restaurant.command.Command;
import by.epamtc.lyskovkirill.restaurant.command.CommandResult;
import by.epamtc.lyskovkirill.restaurant.controller.constant.Page;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * The implementation of the {@link Command} interface that is responsible
 * for redirecting to Sign In page.
 *
 * @author k1ly
 */
public class GoToSignInPage implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        return new CommandResult(Page.SIGN_IN_PAGE.getPath());
    }
}