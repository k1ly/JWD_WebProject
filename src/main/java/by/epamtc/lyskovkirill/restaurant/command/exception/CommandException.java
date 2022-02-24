package by.epamtc.lyskovkirill.restaurant.command.exception;

/**
 * Class that is an inheritor of the {@link Exception}.
 * Represents exception that can be thrown by Commands.
 *
 * @author k1ly
 */
public class CommandException extends Exception {

    public CommandException() {
        super();
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }
}
