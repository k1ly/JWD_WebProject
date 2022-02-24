package by.epamtc.lyskovkirill.restaurant.service;

import by.epamtc.lyskovkirill.restaurant.service.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class provider for service interfaces implementations.
 *
 * @author k1ly
 */
public class ServiceProvider {
    private static final Logger logger = LogManager.getLogger();
    private static final ReentrantLock lock = new ReentrantLock();

    private static ServiceProvider instance;

    private final AboutService aboutService;
    private final ContactService contactService;
    private final DishService dishService;
    private final DishCategoryService dishCategoryService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final OrderStatusService orderStatusService;
    private final ReviewService reviewService;
    private final UserService userService;
    private final UserRoleService userRoleService;
    private final UserStatusService userStatusService;

    private ServiceProvider() {
        aboutService = new AboutServiceImpl();
        contactService = new ContactServiceImpl();
        dishService = new DishServiceImpl();
        dishCategoryService = new DishCategoryServiceImpl();
        orderService = new OrderServiceImpl();
        orderItemService = new OrderItemServiceImpl();
        orderStatusService = new OrderStatusServiceImpl();
        reviewService = new ReviewServiceImpl();
        userService = new UserServiceImpl();
        userRoleService = new UserRoleServiceImpl();
        userStatusService = new UserStatusServiceImpl();
    }

    public static ServiceProvider getInstance() {
        if (instance == null) {
            try {
                if (lock.tryLock(10, TimeUnit.SECONDS)) {
                    if (instance == null)
                        instance = new ServiceProvider();
                    else {
                        logger.warn("ServiceFactory instance is been already initializing by another thread");
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

    public AboutService getAboutService() {
        return aboutService;
    }

    public ContactService getContactService() {
        return contactService;
    }

    public DishService getDishService() {
        return dishService;
    }

    public DishCategoryService getDishCategoryService() {
        return dishCategoryService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public OrderItemService getOrderItemService() {
        return orderItemService;
    }

    public OrderStatusService getOrderStatusService() {
        return orderStatusService;
    }

    public ReviewService getReviewService() {
        return reviewService;
    }

    public UserService getUserService() {
        return userService;
    }

    public UserRoleService getUserRoleService() {
        return userRoleService;
    }

    public UserStatusService getUserStatusService() {
        return userStatusService;
    }
}
