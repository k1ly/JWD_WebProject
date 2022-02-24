package by.epamtc.lyskovkirill.restaurant.dao;

import by.epamtc.lyskovkirill.restaurant.dao.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class provider for DAO interfaces implementations.
 *
 * @author k1ly
 */
public class DAOProvider {
    private static final Logger logger = LogManager.getLogger();
    private static final ReentrantLock lock = new ReentrantLock();

    private static DAOProvider instance;

    private AboutDAO aboutDAO;
    private ContactDAO contactDAO;
    private DishDAO dishDAO;
    private DishCategoryDAO dishCategoryDAO;
    private OrderDAO orderDAO;
    private OrderItemDAO orderItemDAO;
    private OrderStatusDAO orderStatusDAO;
    private ReviewDAO reviewDAO;
    private UserDAO userDAO;
    private UserRoleDAO userRoleDAO;
    private UserStatusDAO userStatusDAO;

    private DAOProvider() {
        aboutDAO = new TXTAboutDAO();
        contactDAO = new JSONContactDAO();
        dishDAO = new SQLDishDAO();
        dishCategoryDAO = new SQLDishCategoryDAO();
        orderDAO = new SQLOrderDAO();
        orderItemDAO = new SQLOrderItemDAO();
        orderStatusDAO = new SQLOrderStatusDAO();
        reviewDAO = new SQLReviewDAO();
        userDAO = new SQLUserDAO();
        userRoleDAO = new SQLUserRoleDAO();
        userStatusDAO = new SQLUserStatusDAO();
    }

    public static DAOProvider getInstance() {
        if (instance == null) {
            try {
                if (lock.tryLock(10, TimeUnit.SECONDS)) {
                    if (instance == null)
                        instance = new DAOProvider();
                    else {
                        logger.warn("DAOProvider instance is been already initializing by another thread");
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

    public AboutDAO getAboutRepository() {
        return aboutDAO;
    }

    public synchronized void setAboutRepository(AboutDAO aboutDAO) {
        this.aboutDAO = aboutDAO;
    }

    public ContactDAO getContactRepository() {
        return contactDAO;
    }

    public synchronized void setContactRepository(ContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

    public DishCategoryDAO getDishCategoryRepository() {
        return dishCategoryDAO;
    }

    public synchronized void setDishCategoryRepository(DishCategoryDAO dishCategoryDAO) {
        this.dishCategoryDAO = dishCategoryDAO;
    }

    public DishDAO getDishRepository() {
        return dishDAO;
    }

    public synchronized void setDishRepository(DishDAO dishDAO) {
        this.dishDAO = dishDAO;
    }

    public OrderItemDAO getOrderItemRepository() {
        return orderItemDAO;
    }

    public synchronized void setOrderItemRepository(OrderItemDAO orderItemDAO) {
        this.orderItemDAO = orderItemDAO;
    }

    public OrderStatusDAO getOrderStatusRepository() {
        return orderStatusDAO;
    }

    public synchronized void setOrderStatusRepository(OrderStatusDAO orderStatusDAO) {
        this.orderStatusDAO = orderStatusDAO;
    }

    public OrderDAO getOrderRepository() {
        return orderDAO;
    }

    public synchronized void setOrderRepository(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public ReviewDAO getReviewRepository() {
        return reviewDAO;
    }

    public synchronized void setReviewRepository(ReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    public UserRoleDAO getUserRoleRepository() {
        return userRoleDAO;
    }

    public synchronized void setUserRoleRepository(UserRoleDAO userRoleDAO) {
        this.userRoleDAO = userRoleDAO;
    }

    public UserStatusDAO getUserStatusRepository() {
        return userStatusDAO;
    }

    public synchronized void setUserStatusRepository(UserStatusDAO userStatusDAO) {
        this.userStatusDAO = userStatusDAO;
    }

    public UserDAO getUserRepository() {
        return userDAO;
    }

    public synchronized void setUserRepository(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
