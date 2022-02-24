package by.epamtc.lyskovkirill.restaurant.dao.exception;

/**
 * Class that is an inheritor of the {@link Exception}.
 * Represents exception that can be thrown in DAO layer.
 *
 * @author k1ly
 */
public class DAOException extends Exception {

    public DAOException() {
        super();
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
}
