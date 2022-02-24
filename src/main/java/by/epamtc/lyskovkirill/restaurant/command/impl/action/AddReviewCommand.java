package by.epamtc.lyskovkirill.restaurant.command.impl.action;

import by.epamtc.lyskovkirill.restaurant.bean.*;
import by.epamtc.lyskovkirill.restaurant.command.Command;
import by.epamtc.lyskovkirill.restaurant.command.CommandResult;
import by.epamtc.lyskovkirill.restaurant.bean.dto.UserDTO;
import by.epamtc.lyskovkirill.restaurant.command.constant.CommandConstants;
import by.epamtc.lyskovkirill.restaurant.command.exception.CommandException;
import by.epamtc.lyskovkirill.restaurant.controller.constant.ControllerConstants;
import by.epamtc.lyskovkirill.restaurant.service.ReviewService;
import by.epamtc.lyskovkirill.restaurant.service.exception.ServiceException;
import by.epamtc.lyskovkirill.restaurant.service.ServiceProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The implementation of the {@link Command} interface that is responsible
 * for adding the {@link Review} by user.
 *
 * @author k1ly
 */
public class AddReviewCommand implements Command {


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        CommandResult commandResult;
        HttpSession session = request.getSession();

        String comment = request.getParameter(CommandConstants.COMMENT);
        int grade = Integer.parseInt(request.getParameter(CommandConstants.GRADE));

        ServiceProvider serviceProvider = ServiceProvider.getInstance();
        ReviewService reviewService = serviceProvider.getReviewService();
        try {
            boolean isReviewAdded = false;
            Review review = new Review();
            Object userAttr = session.getAttribute(ControllerConstants.USER);
            if (userAttr != null) {
                UserDTO user = (UserDTO) userAttr;
                review.setComment(comment);
                review.setGrade(grade);
                review.setDate(Timestamp.from(Instant.now()));
                review.setUser(new User(user.getId()));
                isReviewAdded = reviewService.addReview(review);
            }
            Map<String, Object> responseData = new LinkedHashMap<>();
            responseData.put(CommandConstants.IS_ADDED, isReviewAdded);
            commandResult = new CommandResult(responseData);
        } catch (ServiceException e) {
            throw new CommandException("Error during adding the review", e);
        }
        return commandResult;
    }
}