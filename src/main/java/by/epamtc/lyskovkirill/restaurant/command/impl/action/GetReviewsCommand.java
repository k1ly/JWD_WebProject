package by.epamtc.lyskovkirill.restaurant.command.impl.action;

import by.epamtc.lyskovkirill.restaurant.bean.Review;
import by.epamtc.lyskovkirill.restaurant.command.Command;
import by.epamtc.lyskovkirill.restaurant.command.CommandResult;
import by.epamtc.lyskovkirill.restaurant.command.constant.CommandConstants;
import by.epamtc.lyskovkirill.restaurant.command.exception.CommandException;
import by.epamtc.lyskovkirill.restaurant.service.ReviewService;
import by.epamtc.lyskovkirill.restaurant.service.ServiceProvider;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * The implementation of the {@link Command} interface that is responsible
 * for retrieving {@link Review} list.
 *
 * @author k1ly
 */
public class GetReviewsCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult;

        boolean asc = Boolean.parseBoolean(request.getParameter(CommandConstants.ASC));
        int count = Integer.parseInt(request.getParameter(CommandConstants.COUNT));
        int page = Integer.parseInt(request.getParameter(CommandConstants.PAGE));

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ReviewService reviewService = serviceProvider.getReviewService();
        try {
            List<Review> reviewList = reviewService.browseReviews(asc, count, page);
            int pageCount = (int) Math.ceil((double) reviewService.countReviews() / count);
            Map<String, Object> responseData = new LinkedHashMap<>();
            responseData.put(CommandConstants.PAGE, page);
            responseData.put(CommandConstants.PAGE_COUNT, pageCount);
            responseData.put(CommandConstants.REVIEW_LIST_ATTRIBUTE, reviewList);
            commandResult = new CommandResult(responseData);
        } catch (ServiceException e) {
            throw new CommandException("Error during retrieving reviews", e);
        }
        return commandResult;
    }
}