package by.epamtc.lyskovkirill.restaurant.command.impl.admin;

import by.epamtc.lyskovkirill.restaurant.command.Command;
import by.epamtc.lyskovkirill.restaurant.command.CommandResult;
import by.epamtc.lyskovkirill.restaurant.command.constant.CommandConstants;
import by.epamtc.lyskovkirill.restaurant.command.exception.CommandException;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerConstants;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerURL;
import by.epamtc.lyskovkirill.restaurant.service.DishService;
import by.epamtc.lyskovkirill.restaurant.service.ServiceProvider;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * The implementation of the {@link Command} interface that is responsible
 * for deleting the {@link by.epamtc.lyskovkirill.restaurant.bean.Dish} by admin.
 *
 * @author k1ly
 */
public class DeleteDishCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult = new CommandResult(ControllerURL.ADMINISTRATION_URL.getPath());
        HttpSession session = request.getSession();

        int dishId = Integer.parseInt(request.getParameter(CommandConstants.DISH_ID));

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        DishService dishService = serviceProvider.getDishService();
        try {
            if (!dishService.deleteDish(dishId))
                session.setAttribute(ControllerConstants.ERROR_MESSAGE, "error.message.delete_dish");
        } catch (ServiceException e) {
            throw new CommandException("Error during deleting the dish", e);
        }
        return commandResult;
    }
}