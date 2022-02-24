package by.epamtc.lyskovkirill.restaurant.service.exception;

/**
 * Class that is an inheritor of the {@link Exception}.
 * Represents exception that can be thrown in Service layer.
 *
 * @author k1ly
 */
public class ServiceException extends Exception {

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}
