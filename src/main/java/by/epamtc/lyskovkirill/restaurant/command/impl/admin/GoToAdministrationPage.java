package by.epamtc.lyskovkirill.restaurant.command.impl.admin;

import by.epamtc.lyskovkirill.restaurant.bean.*;
import by.epamtc.lyskovkirill.restaurant.command.Command;
import by.epamtc.lyskovkirill.restaurant.command.CommandResult;
import by.epamtc.lyskovkirill.restaurant.command.constant.CommandConstants;
import by.epamtc.lyskovkirill.restaurant.command.exception.CommandException;
import by.epamtc.lyskovkirill.restaurant.controller.constant.Page;
import by.epamtc.lyskovkirill.restaurant.service.*;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * The implementation of the {@link Command} interface that is responsible
 * for redirecting to Administration page.
 *
 * @author k1ly
 */
public class GoToAdministrationPage implements Command {
    private static final int DEFAULT_COUNT = 10;

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult = new CommandResult(Page.ADMINISTRATION_PAGE.getPath());

        boolean asc = request.getParameter(CommandConstants.ASC) == null
                || Boolean.parseBoolean(request.getParameter(CommandConstants.ASC));
        String orderParam = request.getParameter(CommandConstants.ORDER);
        int count = request.getParameter(CommandConstants.COUNT) != null ?
                Integer.parseInt(request.getParameter(CommandConstants.COUNT)) : DEFAULT_COUNT;
        int page = request.getParameter(CommandConstants.PAGE) != null ?
                Integer.parseInt(request.getParameter(CommandConstants.PAGE)) : 1;
        String tab = request.getParameter(CommandConstants.TAB) != null ?
                request.getParameter(CommandConstants.TAB) : CommandConstants.DISH_TAB;

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        DishService dishService = serviceProvider.getDishService();
        DishCategoryService dishCategoryService = serviceProvider.getDishCategoryService();
        UserService userService = serviceProvider.getUserService();
        try {
            int pageCount = 1;
            if (tab.equals(CommandConstants.DISH_TAB)) {
                List<Dish> dishList = dishService.browseDishes(orderParam, asc, count, page);
                pageCount = (int) Math.ceil((double) dishService.countDishes() / count);
                request.setAttribute(CommandConstants.DISH_LIST_ATTRIBUTE, dishList);
            }
            if (tab.equals(CommandConstants.DISH_CATEGORY_TAB)) {
                List<DishCategory> dishCategoryList = dishCategoryService.browseDishCategories();
                request.setAttribute(CommandConstants.CATEGORY_LIST_ATTRIBUTE, dishCategoryList);
            }
            if (tab.equals(CommandConstants.USER_TAB)) {
                List<User> userList = userService.browseUsers(orderParam, asc, count, page);
                pageCount = (int) Math.ceil((double) userService.countUsers() / count);
                request.setAttribute(CommandConstants.USER_LIST_ATTRIBUTE, userList);
            }
            request.setAttribute(CommandConstants.PAGE, page);
            request.setAttribute(CommandConstants.PAGE_COUNT, pageCount);
            request.setAttribute(CommandConstants.TAB, tab);
        } catch (ServiceException e) {
            throw new CommandException("Error during loading Administration page", e);
        }
        return commandResult;
    }
}