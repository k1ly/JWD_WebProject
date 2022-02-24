package by.epamtc.lyskovkirill.restaurant.command.impl.admin;

import by.epamtc.lyskovkirill.restaurant.bean.Dish;
import by.epamtc.lyskovkirill.restaurant.bean.DishCategory;
import by.epamtc.lyskovkirill.restaurant.command.Command;
import by.epamtc.lyskovkirill.restaurant.command.CommandResult;
import by.epamtc.lyskovkirill.restaurant.command.constant.CommandConstants;
import by.epamtc.lyskovkirill.restaurant.command.exception.CommandException;
import by.epamtc.lyskovkirill.restaurant.service.DishCategoryService;
import by.epamtc.lyskovkirill.restaurant.service.DishService;
import by.epamtc.lyskovkirill.restaurant.service.ServiceProvider;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The implementation of the {@link Command} interface that is responsible
 * for searching the {@link Dish} by admin.
 *
 * @author k1ly
 */
public class FindDishCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult;

        Integer dishId = request.getParameter(CommandConstants.DISH_ID) != null ?
                Integer.parseInt(request.getParameter(CommandConstants.DISH_ID)) : null;

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        DishService dishService = serviceProvider.getDishService();
        DishCategoryService dishCategoryService = serviceProvider.getDishCategoryService();
        try {
            Map<String, Object> responseData = new LinkedHashMap<>();
            if (dishId != null) {
                Optional<Dish> dish = dishService.findDishById(dishId);
                responseData.put(CommandConstants.DISH, dish.orElse(null));
            }
            List<DishCategory> categoryList = dishCategoryService.browseDishCategories();
            responseData.put(CommandConstants.CATEGORY_LIST_ATTRIBUTE, categoryList);
            commandResult = new CommandResult(responseData);
        } catch (ServiceException e) {
            throw new CommandException("Error during searching the dish", e);
        }
        return commandResult;
    }
}