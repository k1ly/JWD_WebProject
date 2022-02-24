package by.epamtc.lyskovkirill.restaurant.command.impl.action;

import by.epamtc.lyskovkirill.restaurant.bean.*;
import by.epamtc.lyskovkirill.restaurant.command.Command;
import by.epamtc.lyskovkirill.restaurant.command.CommandResult;
import by.epamtc.lyskovkirill.restaurant.command.constant.CommandConstants;
import by.epamtc.lyskovkirill.restaurant.command.exception.CommandException;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerConstants;
import by.epamtc.lyskovkirill.restaurant.service.OrderService;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;
import by.epamtc.lyskovkirill.restaurant.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The implementation of the {@link Command} interface that is responsible
 * for canceling the {@link Order} by user.
 *
 * @author k1ly
 */
public class CancelOrderCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult;
        HttpSession session = request.getSession();

        int orderId = Integer.parseInt(request.getParameter(CommandConstants.ORDER_ID));

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        OrderService orderService = serviceProvider.getOrderService();
        try {
            Order order = new Order();
            order.setId(orderId);
            order.setStatus(new OrderStatus(OrderStatus.CANCELED));
            boolean isOrderCanceled = orderService.updateOrder(order);
            Map<String, Object> responseData = new LinkedHashMap<>();
            responseData.put(CommandConstants.ORDER_ID, orderId);
            responseData.put(CommandConstants.IS_CANCELED, isOrderCanceled);
            commandResult = new CommandResult(responseData);
            if (!isOrderCanceled)
                session.setAttribute(ControllerConstants.ERROR_MESSAGE, "error.message.cancel_order");
        } catch (ServiceException e) {
            throw new CommandException("Error during canceling the order", e);
        }
        return commandResult;
    }
}