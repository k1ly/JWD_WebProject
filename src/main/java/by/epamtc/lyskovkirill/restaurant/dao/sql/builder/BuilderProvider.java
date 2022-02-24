package by.epamtc.lyskovkirill.restaurant.dao.sql.builder;

import by.epamtc.lyskovkirill.restaurant.bean.*;
import by.epamtc.lyskovkirill.restaurant.dao.sql.builder.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class provider for {@link Builder} implementations
 * stored in {@link BuilderProvider#repository} map by key {@link Class} name.
 *
 * @author k1ly
 */
public class BuilderProvider {
    private static final Logger logger = LogManager.getLogger();
    private static final ReentrantLock lock = new ReentrantLock();

    private static BuilderProvider instance;

    private final Map<String, Builder<?>> repository = new HashMap<>();

    private BuilderProvider() {
        addBuilder(Dish.class, new DishBuilder());
        addBuilder(DishCategory.class, new DishCategoryBuilder());
        addBuilder(Order.class, new OrderBuilder());
        addBuilder(OrderItem.class, new OrderItemBuilder());
        addBuilder(OrderStatus.class, new OrderStatusBuilder());
        addBuilder(Review.class, new ReviewBuilder());
        addBuilder(User.class, new UserBuilder());
        addBuilder(UserRole.class, new UserRoleBuilder());
        addBuilder(UserStatus.class, new UserStatusBuilder());
    }

    public static synchronized BuilderProvider getInstance() {
        if (instance == null) {
            try {
                if (lock.tryLock(10, TimeUnit.SECONDS)) {
                    if (instance == null)
                        instance = new BuilderProvider();
                    else {
                        logger.warn("BuilderProvider instance is been already initializing by another thread");
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

    public Builder<?> getBuilder(Class<?> buildClass) {
        return repository.get(buildClass.getSimpleName());
    }

    public synchronized void addBuilder(Class<?> buildClass, Builder<?> builder) {
        repository.put(buildClass.getSimpleName(), builder);
    }
}
