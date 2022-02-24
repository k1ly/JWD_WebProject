package by.epamtc.lyskovkirill.restaurant.command.impl.admin;

import by.epamtc.lyskovkirill.restaurant.bean.DishCategory;
import by.epamtc.lyskovkirill.restaurant.command.Command;
import by.epamtc.lyskovkirill.restaurant.command.CommandResult;
import by.epamtc.lyskovkirill.restaurant.command.constant.CommandConstants;
import by.epamtc.lyskovkirill.restaurant.command.exception.CommandException;
import by.epamtc.lyskovkirill.restaurant.service.DishCategoryService;
import by.epamtc.lyskovkirill.restaurant.service.ServiceProvider;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * The implementation of the {@link Command} interface that is responsible
 * for searching the {@link DishCategory} by admin.
 *
 * @author k1ly
 */
public class FindCategoryCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult;

        Integer categoryId = request.getParameter(CommandConstants.CATEGORY_ID) != null ?
                Integer.parseInt(request.getParameter(CommandConstants.CATEGORY_ID)) : null;

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        DishCategoryService dishCategoryService = serviceProvider.getDishCategoryService();
        try {
            Map<String, Object> responseData = new LinkedHashMap<>();
            if (categoryId != null) {
                Optional<DishCategory> dishCategory = dishCategoryService.findDishCategoryById(categoryId);
                responseData.put(CommandConstants.CATEGORY, dishCategory.orElse(null));
            }
            commandResult = new CommandResult(responseData);
        } catch (ServiceException e) {
            throw new CommandException("Error during searching the dish category", e);
        }
        return commandResult;
    }
}