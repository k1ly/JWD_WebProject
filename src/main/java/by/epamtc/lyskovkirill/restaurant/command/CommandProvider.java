package by.epamtc.lyskovkirill.restaurant.command;

import by.epamtc.lyskovkirill.restaurant.command.impl.action.*;
import by.epamtc.lyskovkirill.restaurant.command.impl.admin.*;
import by.epamtc.lyskovkirill.restaurant.command.impl.manager.GoToManageOrdersPage;
import by.epamtc.lyskovkirill.restaurant.command.impl.manager.ManageOrderCommand;
import by.epamtc.lyskovkirill.restaurant.command.impl.page.*;
import by.epamtc.lyskovkirill.restaurant.command.impl.user.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class provider for {@link Command} implementations
 * stored in {@link CommandProvider#repository} map by key {@link CommandName}.
 *
 * @author k1ly
 */
public final class CommandProvider {
    private static final Logger logger = LogManager.getLogger();
    private static final ReentrantLock lock = new ReentrantLock();

    private static CommandProvider instance;

    private final Map<CommandName, Command> repository = new HashMap<>();

    private CommandProvider() {
        repository.put(CommandName.GO_TO_WELCOME, new GoToWelcomePage());
        repository.put(CommandName.GO_TO_MENU, new GoToMenuPage());
        repository.put(CommandName.GO_TO_CONTACTS, new GoToContactsPage());
        repository.put(CommandName.GO_TO_ABOUT, new GoToAboutPage());
        repository.put(CommandName.GO_TO_REGISTER, new GoToRegisterPage());
        repository.put(CommandName.GO_TO_SIGN_IN, new GoToSignInPage());
        repository.put(CommandName.GO_TO_RECOVER, new GoToRecoverPage());
        repository.put(CommandName.GO_TO_PENALIZED, new GoToPenalizedPage());

        repository.put(CommandName.GO_TO_CART, new GoToCartPage());
        repository.put(CommandName.GO_TO_ORDER_SUCCESS, new GoToOrderSuccessPage());
        repository.put(CommandName.GO_TO_ACCOUNT, new GoToAccountProfilePage());
        repository.put(CommandName.GO_TO_ACCOUNT_PROFILE, new GoToAccountProfilePage());
        repository.put(CommandName.GO_TO_ACCOUNT_ORDERS, new GoToAccountOrdersPage());
        repository.put(CommandName.GO_TO_SETTINGS, new GoToSettingsPage());
        repository.put(CommandName.GO_TO_MANAGE_ORDERS, new GoToManageOrdersPage());

        repository.put(CommandName.REGISTER_USER, new RegisterUserCommand());
        repository.put(CommandName.SIGN_IN, new SignInCommand());
        repository.put(CommandName.SIGN_OUT, new SignOutCommand());
        repository.put(CommandName.EDIT_SETTINGS, new EditSettingsCommand());
        repository.put(CommandName.RECOVER_PASSWORD, new GoToWelcomePage());

        repository.put(CommandName.UPDATE_CART_ITEM, new UpdateCartItemCommand());
        repository.put(CommandName.CONFIRM_ORDER, new ConfirmOrderCommand());
        repository.put(CommandName.CANCEL_ORDER, new CancelOrderCommand());
        repository.put(CommandName.ADD_REVIEW, new AddReviewCommand());
        repository.put(CommandName.GET_REVIEWS, new GetReviewsCommand());
        repository.put(CommandName.MANAGE_ORDER, new ManageOrderCommand());

        repository.put(CommandName.GO_TO_ADMINISTRATION, new GoToAdministrationPage());
        repository.put(CommandName.FIND_DISH, new FindDishCommand());
        repository.put(CommandName.ADD_DISH, new AddDishCommand());
        repository.put(CommandName.EDIT_DISH, new EditDishCommand());
        repository.put(CommandName.DELETE_DISH, new DeleteDishCommand());
        repository.put(CommandName.FIND_CATEGORY, new FindCategoryCommand());
        repository.put(CommandName.ADD_CATEGORY, new AddCategoryCommand());
        repository.put(CommandName.EDIT_CATEGORY, new EditCategoryCommand());
        repository.put(CommandName.DELETE_CATEGORY, new DeleteCategoryCommand());
        repository.put(CommandName.FIND_USER, new FindUserCommand());
        repository.put(CommandName.EDIT_USER, new EditUserCommand());
    }

    public static CommandProvider getInstance() {
        if (instance == null) {
            try {
                if (lock.tryLock(10, TimeUnit.SECONDS)) {
                    if (instance == null)
                        instance = new CommandProvider();
                    else {
                        logger.warn("CommandProvider instance is been already initializing by another thread");
                    }
                } else {
                    logger.error("Timeout exceeded");
                    throw new RuntimeException("Timeout exceeded");
                }
            } catch (InterruptedException exception) {
                logger.error(exception);
                throw new RuntimeException(exception.getMessage(), exception);
            } finally {
                if (lock.isHeldByCurrentThread())
                    lock.unlock();
            }
        }
        return instance;
    }

    public Command getCommand(String name) {
        CommandName commandName;
        Command command;
        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            command = repository.get(CommandName.GO_TO_WELCOME);
        }
        return command;
    }
}
