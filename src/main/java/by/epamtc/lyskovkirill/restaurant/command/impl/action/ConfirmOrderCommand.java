package by.epamtc.lyskovkirill.restaurant.command.impl.action;

import by.epamtc.lyskovkirill.restaurant.bean.Order;
import by.epamtc.lyskovkirill.restaurant.bean.OrderStatus;
import by.epamtc.lyskovkirill.restaurant.bean.User;
import by.epamtc.lyskovkirill.restaurant.command.Command;
import by.epamtc.lyskovkirill.restaurant.command.CommandResult;
import by.epamtc.lyskovkirill.restaurant.bean.dto.UserDTO;
import by.epamtc.lyskovkirill.restaurant.command.constant.CommandConstants;
import by.epamtc.lyskovkirill.restaurant.command.exception.CommandException;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerConstants;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerURL;
import by.epamtc.lyskovkirill.restaurant.controller.constant.Page;
import by.epamtc.lyskovkirill.restaurant.service.OrderService;
import by.epamtc.lyskovkirill.restaurant.service.UserService;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;
import by.epamtc.lyskovkirill.restaurant.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * The implementation of the {@link Command} interface that is responsible
 * for confirming {@link Order} by user.
 *
 * @author k1ly
 */
public class ConfirmOrderCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult = new CommandResult(Page.CART_PAGE.getPath());
        HttpSession session = request.getSession();

        String requiredDate = request.getParameter(CommandConstants.REQUIRED_DATE);

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        OrderService orderService = serviceProvider.getOrderService();
        try {
            Object userAttr = session.getAttribute(ControllerConstants.USER);
            if (userAttr != null) {
                UserDTO user = (UserDTO) userAttr;
                Order order = new Order();
                order.setId(user.getCartOrderId());
                order.setOrderDate(Timestamp.from(Instant.now()));
                if (requiredDate != null)
                    order.setRequiredDate(Timestamp.valueOf(requiredDate.replace('T', ' ') + ":00"));
                order.setStatus(new OrderStatus(OrderStatus.AWAITING));
                boolean isOrderConfirmed = orderService.updateOrder(order);
                if (isOrderConfirmed) {
                    Order userCart = new Order();
                    User customer = new User(user.getId());
                    userCart.setCustomer(customer);
                    if (orderService.addOrder(userCart)) {
                        customer.setCart(userCart);
                        UserService userService = serviceProvider.getUserService();
                        int updateStatus = userService.updateUser(customer);
                        if (updateStatus > 0) {
                            user.setCartOrderId(userCart.getId());
                            session.setAttribute(ControllerConstants.USER, user);
                        }
                    }
                    session.setAttribute(ControllerConstants.ACCESS_ALLOWED, true);
                    commandResult.setPath(ControllerURL.ORDER_SUCCESS_URL.getPath());
                } else
                    session.setAttribute(ControllerConstants.ERROR_MESSAGE, "error.message.confirm_order");
            }
        } catch (ServiceException e) {
            throw new CommandException("Error during confirming the order", e);
        }
        return commandResult;
    }
}