package by.epamtc.lyskovkirill.restaurant.command.impl.manager;

import by.epamtc.lyskovkirill.restaurant.bean.Order;
import by.epamtc.lyskovkirill.restaurant.bean.OrderStatus;
import by.epamtc.lyskovkirill.restaurant.command.Command;
import by.epamtc.lyskovkirill.restaurant.command.CommandResult;
import by.epamtc.lyskovkirill.restaurant.command.constant.CommandConstants;
import by.epamtc.lyskovkirill.restaurant.command.exception.CommandException;
import by.epamtc.lyskovkirill.restaurant.controller.constant.Page;
import by.epamtc.lyskovkirill.restaurant.service.OrderService;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;
import by.epamtc.lyskovkirill.restaurant.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * The implementation of the {@link Command} interface that is responsible
 * for redirecting to Manage Orders page.
 *
 * @author k1ly
 */
public class GoToManageOrdersPage implements Command {
    private static final int DEFAULT_COUNT = 15;
    private static final String DEFAULT_TAB = "awaiting";
    private static final String ORDER_STATUS = "ORDER_STATUS_";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult = new CommandResult(Page.MANAGE_ORDERS_PAGE.getPath());

        boolean asc = request.getParameter(CommandConstants.ASC) == null
                || Boolean.parseBoolean(request.getParameter(CommandConstants.ASC));
        int count = request.getParameter(CommandConstants.COUNT) != null ?
                Integer.parseInt(request.getParameter(CommandConstants.COUNT)) : DEFAULT_COUNT;
        int page = request.getParameter(CommandConstants.PAGE) != null ?
                Integer.parseInt(request.getParameter(CommandConstants.PAGE)) : 1;
        String tab = request.getParameter(CommandConstants.TAB) != null ?
                request.getParameter(CommandConstants.TAB) : DEFAULT_TAB;

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        OrderService orderService = serviceProvider.getOrderService();
        try {
            List<Order> orderList;
            OrderStatus orderStatus = new OrderStatus(ORDER_STATUS + tab.toUpperCase());
            orderList = orderService.findOrdersByStatus(orderStatus, asc, count, page);
            int pageCount = (int) Math.ceil((double) orderService.countOrders(orderStatus) / count);
            request.setAttribute(CommandConstants.PAGE, page);
            request.setAttribute(CommandConstants.PAGE_COUNT, pageCount);
            request.setAttribute(CommandConstants.TAB, tab);
            request.setAttribute(CommandConstants.ORDER_LIST_ATTRIBUTE, orderList);
        } catch (ServiceException e) {
            throw new CommandException("Error during loading Manage Orders page", e);
        }
        return commandResult;
    }
}