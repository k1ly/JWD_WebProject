package by.epamtc.lyskovkirill.restaurant.command.impl.user;

import by.epamtc.lyskovkirill.restaurant.bean.Order;
import by.epamtc.lyskovkirill.restaurant.bean.OrderStatus;
import by.epamtc.lyskovkirill.restaurant.command.Command;
import by.epamtc.lyskovkirill.restaurant.command.CommandResult;
import by.epamtc.lyskovkirill.restaurant.bean.dto.UserDTO;
import by.epamtc.lyskovkirill.restaurant.command.constant.CommandConstants;
import by.epamtc.lyskovkirill.restaurant.command.exception.CommandException;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerConstants;
import by.epamtc.lyskovkirill.restaurant.controller.constant.Page;
import by.epamtc.lyskovkirill.restaurant.service.OrderService;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;
import by.epamtc.lyskovkirill.restaurant.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;

/**
 * The implementation of the {@link Command} interface that is responsible
 * for redirecting to Account Orders page.
 *
 * @author k1ly
 */
public class GoToAccountOrdersPage implements Command {
    private static final int DEFAULT_COUNT = 10;
    private static final String DEFAULT_TAB = "awaiting";
    private static final String ORDER_STATUS = "ORDER_STATUS_";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult = new CommandResult(Page.ACCOUNT_ORDERS_PAGE.getPath());
        HttpSession session = request.getSession();

        boolean asc = request.getParameter(CommandConstants.ASC) == null
                || Boolean.parseBoolean(request.getParameter(CommandConstants.ASC));
        int page = request.getParameter(CommandConstants.PAGE) != null ?
                Integer.parseInt(request.getParameter(CommandConstants.PAGE)) : 1;
        int count = request.getParameter(CommandConstants.COUNT) != null ?
                Integer.parseInt(request.getParameter(CommandConstants.COUNT)) : DEFAULT_COUNT;
        String tab = request.getParameter(CommandConstants.TAB) != null ?
                request.getParameter(CommandConstants.TAB) : DEFAULT_TAB;

        Object userAttr = session.getAttribute(ControllerConstants.USER);
        if (userAttr != null) {
            ServiceProvider serviceProvider = ServiceProvider.getInstance();
            OrderService orderService = serviceProvider.getOrderService();
            try {
                UserDTO user = (UserDTO) userAttr;
                OrderStatus orderStatus = new OrderStatus(ORDER_STATUS + tab.toUpperCase());
                List<Order> orderList = orderService.findOrdersByCustomer(user.getId(), orderStatus, asc, count, page);
                int pageCount = (int) Math.ceil((double) orderService.countOrders(user.getId(), orderStatus) / count);
                request.setAttribute(CommandConstants.PAGE, page);
                request.setAttribute(CommandConstants.PAGE_COUNT, pageCount);
                request.setAttribute(CommandConstants.TAB, tab);
                request.setAttribute(CommandConstants.ORDER_LIST_ATTRIBUTE, orderList);
            } catch (ServiceException e) {
                throw new CommandException("Error during loading Account Orders page", e);
            }
        }
        return commandResult;
    }
}