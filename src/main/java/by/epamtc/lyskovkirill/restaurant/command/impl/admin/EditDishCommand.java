package by.epamtc.lyskovkirill.restaurant.command.impl.admin;

import by.epamtc.lyskovkirill.restaurant.bean.Dish;
import by.epamtc.lyskovkirill.restaurant.bean.DishCategory;
import by.epamtc.lyskovkirill.restaurant.command.Command;
import by.epamtc.lyskovkirill.restaurant.command.CommandResult;
import by.epamtc.lyskovkirill.restaurant.command.constant.CommandConstants;
import by.epamtc.lyskovkirill.restaurant.command.exception.CommandException;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerConstants;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerURL;
import by.epamtc.lyskovkirill.restaurant.service.DishService;
import by.epamtc.lyskovkirill.restaurant.service.ServiceProvider;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;
import by.epamtc.lyskovkirill.restaurant.util.controllerutil.ImageHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;

/**
 * The implementation of the {@link Command} interface that is responsible
 * for editing the {@link Dish} by admin.
 *
 * @author k1ly
 */
public class EditDishCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult = new CommandResult(ControllerURL.ADMINISTRATION_URL.getPath());
        HttpSession session = request.getSession();

        int dishId = Integer.parseInt(request.getParameter(CommandConstants.DISH_ID));
        String name = request.getParameter(CommandConstants.NAME);
        String composition = request.getParameter(CommandConstants.COMPOSITION);
        Integer weight = request.getParameter(CommandConstants.WEIGHT) != null ?
                Integer.parseInt(request.getParameter(CommandConstants.WEIGHT)) : null;
        Double price = request.getParameter(CommandConstants.PRICE) != null ?
                Double.parseDouble(request.getParameter(CommandConstants.PRICE)) : null;
        Integer discount = request.getParameter(CommandConstants.DISCOUNT) != null ?
                Integer.parseInt(request.getParameter(CommandConstants.DISCOUNT)) : null;
        Integer categoryId = request.getParameter(CommandConstants.CATEGORY_ID) != null ?
                Integer.parseInt(request.getParameter(CommandConstants.CATEGORY_ID)) : null;

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        DishService dishService = serviceProvider.getDishService();
        try {
            String imageUrl = null;
            ImageHandler imageHandler = ImageHandler.getInstance();
            Part fileData = request.getPart(CommandConstants.IMAGE);
            if (fileData != null) {
                String path = request.getServletContext().getRealPath(CommandConstants.DISH_IMAGE_DIRECTORY);
                String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                        + request.getContextPath() + "/" + CommandConstants.DISH_IMAGE_DIRECTORY;
                imageUrl = url + "/" + imageHandler.saveImage(fileData, path);
            }
            Dish newDish = new Dish();
            newDish.setId(dishId);
            newDish.setName(name);
            newDish.setComposition(composition);
            newDish.setWeight(weight);
            newDish.setPrice(price);
            newDish.setDiscount(discount);
            newDish.setImage(imageUrl);
            newDish.setCategory(categoryId != null ? new DishCategory(categoryId) : null);
            int updateStatus = dishService.updateDish(newDish);
            if (updateStatus == 0)
                session.setAttribute(ControllerConstants.ERROR_MESSAGE, "error.message.edit_dish");
            else if (updateStatus < 0)
                session.setAttribute(ControllerConstants.ERROR_MESSAGE, "error.message.invalid");
        } catch (ServiceException e) {
            throw new CommandException("Error during editing the dish", e);
        } catch (ServletException | IOException e) {
            session.setAttribute(ControllerConstants.ERROR_MESSAGE, "error.message.save_image");
        }
        return commandResult;
    }
}