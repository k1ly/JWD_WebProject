package by.epamtc.lyskovkirill.restaurant.command.impl.page;

import by.epamtc.lyskovkirill.restaurant.bean.*;
import by.epamtc.lyskovkirill.restaurant.command.Command;
import by.epamtc.lyskovkirill.restaurant.command.CommandResult;
import by.epamtc.lyskovkirill.restaurant.bean.dto.UserDTO;
import by.epamtc.lyskovkirill.restaurant.command.constant.CommandConstants;
import by.epamtc.lyskovkirill.restaurant.command.exception.CommandException;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerConstants;
import by.epamtc.lyskovkirill.restaurant.controller.constant.Page;
import by.epamtc.lyskovkirill.restaurant.service.DishService;
import by.epamtc.lyskovkirill.restaurant.service.OrderItemService;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;
import by.epamtc.lyskovkirill.restaurant.service.ServiceProvider;
import by.epamtc.lyskovkirill.restaurant.util.controllerutil.CartCookieHandler;
import by.epamtc.lyskovkirill.restaurant.util.controllerutil.CookieHandler;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

/**
 * The implementation of the {@link Command} interface that is responsible
 * for redirecting to Cart page.
 *
 * @author k1ly
 */
public class GoToCartPage implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult = new CommandResult(Page.CART_PAGE.getPath());
        HttpSession session = request.getSession();

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        OrderItemService orderItemService = serviceProvider.getOrderItemService();
        DishService dishService = serviceProvider.getDishService();
        try {
            Order order = new Order();
            Object userAttr = session.getAttribute(ControllerConstants.USER);
            if (userAttr != null) {
                UserDTO user = (UserDTO) userAttr;
                if (UserRole.GUEST.equals(user.getRoleName())) {
                    CookieHandler cookieHandler = CookieHandler.getInstance();
                    Cookie cartCookie = cookieHandler.findCookie(request.getCookies(), ControllerConstants.CART);
                    CartCookieHandler cartCookieHandler = CartCookieHandler.getInstance();
                    order.setOrderItems(cartCookieHandler.parseCookie(cartCookie));
                    for (OrderItem orderItem : order.getOrderItems()) {
                        Optional<Dish> dish = dishService.findDishById(orderItem.getDish().getId());
                        dish.ifPresent(orderItem::setDish);
                    }
                } else {
                    List<OrderItem> orderItems = orderItemService.browseOrderItems(user.getCartOrderId());
                    order.setOrderItems(orderItems);
                }
            }
            request.setAttribute(CommandConstants.ORDER_ITEM_LIST_ATTRIBUTE, order.getOrderItems());
        } catch (ServiceException e) {
            throw new CommandException("Error during loading Cart page", e);
        }
        return commandResult;
    }
}