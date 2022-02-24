package by.epamtc.lyskovkirill.restaurant.command.impl.page;

import by.epamtc.lyskovkirill.restaurant.command.Command;
import by.epamtc.lyskovkirill.restaurant.command.CommandResult;
import by.epamtc.lyskovkirill.restaurant.command.constant.CommandConstants;
import by.epamtc.lyskovkirill.restaurant.command.exception.CommandException;
import by.epamtc.lyskovkirill.restaurant.controller.constant.Page;
import by.epamtc.lyskovkirill.restaurant.service.AboutService;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;
import by.epamtc.lyskovkirill.restaurant.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * The implementation of the {@link Command} interface that is responsible
 * for redirecting to About page.
 *
 * @author k1ly
 */
public class GoToAboutPage implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult = new CommandResult(Page.ABOUT_PAGE.getPath());
        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        AboutService aboutService = serviceProvider.getAboutService();
        try {
            String aboutText = aboutService.findAbout();
            request.setAttribute(CommandConstants.ABOUT_TEXT, aboutText);
        } catch (ServiceException e) {
            throw new CommandException("Error during loading About page", e);
        }
        return commandResult;
    }
}