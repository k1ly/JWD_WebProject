package by.epamtc.lyskovkirill.restaurant.command.impl.page;

import by.epamtc.lyskovkirill.restaurant.bean.Dish;
import by.epamtc.lyskovkirill.restaurant.bean.DishCategory;
import by.epamtc.lyskovkirill.restaurant.command.Command;
import by.epamtc.lyskovkirill.restaurant.command.CommandResult;
import by.epamtc.lyskovkirill.restaurant.command.constant.CommandConstants;
import by.epamtc.lyskovkirill.restaurant.command.exception.CommandException;
import by.epamtc.lyskovkirill.restaurant.controller.constant.Page;
import by.epamtc.lyskovkirill.restaurant.service.DishCategoryService;
import by.epamtc.lyskovkirill.restaurant.service.DishService;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;
import by.epamtc.lyskovkirill.restaurant.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Optional;

/**
 * The implementation of the {@link Command} interface that is responsible
 * for redirecting to Menu page.
 *
 * @author k1ly
 */
public class GoToMenuPage implements Command {
    private static final int DEFAULT_COUNT = 10;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult;

        int categoryId = request.getParameter(CommandConstants.CATEGORY_ID) != null ?
                Integer.parseInt(request.getParameter(CommandConstants.CATEGORY_ID)) : 1;
        String orderParam = request.getParameter(CommandConstants.ORDER);
        boolean asc = request.getParameter(CommandConstants.ASC) != null
                && Boolean.parseBoolean(request.getParameter(CommandConstants.ASC));
        int count = request.getParameter(CommandConstants.COUNT) != null ?
                Integer.parseInt(request.getParameter(CommandConstants.COUNT)) : DEFAULT_COUNT;
        int page = request.getParameter(CommandConstants.PAGE) != null ?
                Integer.parseInt(request.getParameter(CommandConstants.PAGE)) : 1;

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        DishCategoryService dishCategoryService = serviceProvider.getDishCategoryService();
        DishService dishService = serviceProvider.getDishService();
        try {
            List<DishCategory> categoryList = dishCategoryService.browseDishCategories();
            Optional<DishCategory> dishCategory = dishCategoryService.findDishCategoryById(categoryId);
            List<Dish> dishList = dishService.findDishesByCategory(categoryId, orderParam, asc, count, page);
            int pageCount = (int) Math.ceil((double) dishService.countDishes(categoryId) / count);
            request.setAttribute(CommandConstants.PAGE, page);
            request.setAttribute(CommandConstants.PAGE_COUNT, pageCount);
            request.setAttribute(CommandConstants.DISH_LIST_ATTRIBUTE, dishList);
            request.setAttribute(CommandConstants.CATEGORY, dishCategory.orElse(null));
            request.setAttribute(CommandConstants.CATEGORY_LIST_ATTRIBUTE, categoryList);
            commandResult = new CommandResult(Page.MENU_PAGE.getPath());
        } catch (ServiceException e) {
            throw new CommandException("Error during loading Menu page", e);
        }
        return commandResult;
    }
}