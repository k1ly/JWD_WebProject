package by.epamtc.lyskovkirill.restaurant.command.impl.admin;

import by.epamtc.lyskovkirill.restaurant.bean.DishCategory;
import by.epamtc.lyskovkirill.restaurant.command.Command;
import by.epamtc.lyskovkirill.restaurant.command.CommandResult;
import by.epamtc.lyskovkirill.restaurant.command.constant.CommandConstants;
import by.epamtc.lyskovkirill.restaurant.command.exception.CommandException;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerConstants;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerURL;
import by.epamtc.lyskovkirill.restaurant.service.DishCategoryService;
import by.epamtc.lyskovkirill.restaurant.service.ServiceProvider;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * The implementation of the {@link Command} interface that is responsible
 * for adding the {@link DishCategory} by admin.
 *
 * @author k1ly
 */
public class AddCategoryCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult = new CommandResult(ControllerURL.ADMINISTRATION_URL.getPath());
        HttpSession session = request.getSession();

        String name = request.getParameter(CommandConstants.NAME);

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        DishCategoryService dishCategoryService = serviceProvider.getDishCategoryService();
        try {
            DishCategory dishCategory = new DishCategory();
            dishCategory.setName(name);
            int addStatus = dishCategoryService.addDishCategory(dishCategory);
            if (addStatus == 0)
                session.setAttribute(ControllerConstants.ERROR_MESSAGE, "error.message.add_category");
            else if (addStatus < 0)
                session.setAttribute(ControllerConstants.ERROR_MESSAGE, "error.message.invalid");
        } catch (ServiceException e) {
            throw new CommandException("Error during adding the dish category", e);
        }
        return commandResult;
    }
}