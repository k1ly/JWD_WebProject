package by.epamtc.lyskovkirill.restaurant.command.impl.action;

import by.epamtc.lyskovkirill.restaurant.bean.*;
import by.epamtc.lyskovkirill.restaurant.command.Command;
import by.epamtc.lyskovkirill.restaurant.command.CommandResult;
import by.epamtc.lyskovkirill.restaurant.bean.dto.UserDTO;
import by.epamtc.lyskovkirill.restaurant.command.constant.CommandConstants;
import by.epamtc.lyskovkirill.restaurant.command.exception.CommandException;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerConstants;
import by.epamtc.lyskovkirill.restaurant.service.OrderItemService;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;
import by.epamtc.lyskovkirill.restaurant.service.ServiceProvider;
import by.epamtc.lyskovkirill.restaurant.util.controllerutil.CartCookieHandler;
import by.epamtc.lyskovkirill.restaurant.util.controllerutil.CookieHandler;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The implementation of the {@link Command} interface that is responsible
 * for updating the {@link OrderItem} in user cart.
 *
 * @author k1ly
 */
public class UpdateCartItemCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult;
        HttpSession session = request.getSession();

        int dishId = Integer.parseInt(request.getParameter(CommandConstants.DISH_ID));
        int quantity = Integer.parseInt(request.getParameter(CommandConstants.QUANTITY));
        boolean isNew = Boolean.parseBoolean(request.getParameter(CommandConstants.IS_NEW));

        try {
            boolean isCartItemUpdated = false;
            Order order = new Order();
            OrderItem orderItem = new OrderItem();
            Object userAttr = session.getAttribute(ControllerConstants.USER);
            if (userAttr != null) {
                UserDTO user = (UserDTO) userAttr;
                if (UserRole.GUEST.equals(user.getRoleName())) {
                    CookieHandler cookieHandler = CookieHandler.getInstance();
                    Cookie cartCookie = cookieHandler.findCookie(request.getCookies(), ControllerConstants.CART);
                    CartCookieHandler cartCookieHandler = CartCookieHandler.getInstance();
                    order.setOrderItems(cartCookieHandler.parseCookie(cartCookie));
                    boolean isSuchProduct = false;
                    for (OrderItem cartItem : order.getOrderItems()) {
                        if (cartItem.getDish().getId() == dishId) {
                            if (quantity > 0)
                                cartItem.setQuantity(isNew ? cartItem.getQuantity() + quantity : quantity);
                            else
                                order.getOrderItems().remove(cartItem);
                            isSuchProduct = true;
                            break;
                        }
                    }
                    orderItem.setDish(new Dish(dishId));
                    orderItem.setQuantity(quantity);
                    if (!isSuchProduct && quantity > 0)
                        order.getOrderItems().add(orderItem);
                    response.addCookie(cartCookieHandler.createCookie(order.getOrderItems()));
                    isCartItemUpdated = true;
                } else {
                    order.setId(user.getCartOrderId());
                    ServiceProvider serviceProvider = ServiceProvider.getInstance();
                    OrderItemService orderItemService = serviceProvider.getOrderItemService();
                    orderItem.setOrder(order);
                    orderItem.setDish(new Dish(dishId));
                    orderItem.setQuantity(quantity);
                    isCartItemUpdated = orderItemService.updateOrderItem(orderItem, isNew);
                    if (!isCartItemUpdated)
                        orderItem.setQuantity(-1);
                }
            }
            Map<String, Object> responseData = new LinkedHashMap<>();
            responseData.put(CommandConstants.ORDER_ITEM, orderItem);
            responseData.put(CommandConstants.IS_UPDATED, isCartItemUpdated);
            commandResult = new CommandResult(responseData);
        } catch (ServiceException e) {
            throw new CommandException("Error during updating cart item", e);
        }
        return commandResult;
    }
}