package by.epamtc.lyskovkirill.restaurant.command.impl.manager;

import by.epamtc.lyskovkirill.restaurant.bean.Order;
import by.epamtc.lyskovkirill.restaurant.bean.OrderStatus;
import by.epamtc.lyskovkirill.restaurant.bean.User;
import by.epamtc.lyskovkirill.restaurant.command.Command;
import by.epamtc.lyskovkirill.restaurant.command.CommandResult;
import by.epamtc.lyskovkirill.restaurant.bean.dto.UserDTO;
import by.epamtc.lyskovkirill.restaurant.command.constant.CommandConstants;
import by.epamtc.lyskovkirill.restaurant.command.exception.CommandException;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerConstants;
import by.epamtc.lyskovkirill.restaurant.service.OrderService;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;
import by.epamtc.lyskovkirill.restaurant.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * The implementation of the {@link Command} interface that is responsible
 * for managing {@link Order}.
 *
 * @author k1ly
 */
public class ManageOrderCommand implements Command {
    private static final String ORDER_STATUS = "ORDER_STATUS_";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult = new CommandResult();
        HttpSession session = request.getSession();

        int orderId = Integer.parseInt(request.getParameter(CommandConstants.ORDER_ID));
        String orderStatus = request.getParameter(CommandConstants.ORDER_STATUS);

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        OrderService orderService = serviceProvider.getOrderService();
        try {
            Order order = new Order();
            order.setId(orderId);
            order.setStatus(new OrderStatus(ORDER_STATUS + orderStatus.toUpperCase()));
            if (OrderStatus.PREPARING.equals(order.getStatus().getName())) {
                Object userAttr = session.getAttribute(ControllerConstants.USER);
                if (userAttr != null) {
                    UserDTO user = (UserDTO) userAttr;
                    order.setManager(new User(user.getId()));
                }
            }
            if (!orderService.updateOrder(order))
                session.setAttribute(ControllerConstants.ERROR_MESSAGE, "error.message.manage_order");
        } catch (ServiceException e) {
            throw new CommandException("Error during managing the order", e);
        }
        return commandResult;
    }
}